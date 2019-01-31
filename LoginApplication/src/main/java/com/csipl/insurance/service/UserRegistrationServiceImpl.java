package com.csipl.insurance.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;

import com.csipl.insurance.commons.AppUtils;
import com.csipl.insurance.commons.OTPService;
import com.csipl.insurance.commons.PasswordGenerator;
import com.csipl.insurance.model.MasterBook;
import com.csipl.insurance.model.UserRegistration;
import com.csipl.insurance.repo.MasterBookRepository;
import com.csipl.insurance.repo.UserRegistrationRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import javax.transaction.Transactional;

@Transactional
@Service

public class UserRegistrationServiceImpl implements UserRegistrationService {

	/**
	 * Logger declaration for knowing the flow of execution for debugging
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	@Autowired
	StorageService storageService;
	
	@Autowired
	private MasterBookRepository masterBookRepository;
	
	@Autowired
	OTPService otpService;
	
	 @Autowired
	 private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserRegistration save(UserRegistration userRegistration, MultipartFile mFile) throws Exception {
		logger.info("UserRegistrationServiceImpl.save()");
		// userRegistrationRepository.save(userRegistration);
		
		   //VelocityEngine velocityEngine = new VelocityEngine();
		
//		   VelocityEngine velocityEngine = new VelocityEngine();
//		   velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//		   velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//		   velocityEngine.init();
		   
		String bookCode = "EMPNO";  
		int len = 10;
		String password = PasswordGenerator.generatePassword(len, ALPHA_CAPS + ALPHA);
		String token = UUID.randomUUID().toString();
		userRegistration.setVerificationToken(token);
		userRegistration.setIsActive(false);
		userRegistration.setPassword(password);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		System.out.println("Mime      " + mimeMessage);
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		// String emailLink = AppUtils.url + userRegistration.getUserId();
		
		LocalDateTime now = LocalDateTime.now();
		
		
		
		
		String emailLink = AppUtils.url + token + "&date=" +now;
		
		
		//String emailLink = AppUtils.url + token;
		Map model = new HashMap();
		model.put("firstName", userRegistration.getFirstName());
		model.put("lastName", userRegistration.getLastName());
		model.put("password", password);
		model.put("link", emailLink);

	// String text = model.toString();

		
		
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"templates/InviteTemplate.vm", "UTF-8", model);
		
		 
		mimeMessageHelper.setFrom("singyabhalse79@gmail.com");
		mimeMessageHelper.setSubject("Email Verification Mail");
		mimeMessageHelper.setTo(userRegistration.getEmailId());
		mimeMessageHelper.setText(text, true);
		mailSender.send(mimeMessageHelper.getMimeMessage());
		
		
		// mobile otp
		String mobile = String.valueOf(userRegistration.getMobile());
		int otp = otpService.generateOtp(userRegistration.getFirstName());
		new AppUtils().sendOtpBySms("hii"+otp,mobile);
		
		
		
//int otp = otpService.getOtp(userRegistration .getFirstName());
//		
//		if (otp > 0) {
//			Integer value = otpService.getOtp(userRegistration.getFirstName());
//			 
//			if (value > 0) {
//				String mobile = String.valueOf(userRegistration.getMobile());
//				//int values = otpService.generateOtp(user.getFirstName());
//				
//				System.out.println("Otp--"+otp);
//				new AppUtils().sendOtpBySms("Hi Your Otp Number is :- "+value,mobile);
//				 
//			}
//		} else {
//			//otpService.clearOTP(user.getFirstName());
//				Integer value = otpService.generateOtp(userRegistration.getFirstName());
//				String mobile = String.valueOf(userRegistration.getMobile());
//				if (value > 0) {
//					System.out.println("sajhjhsaj");
//					new AppUtils().sendOtpBySms("Hi Your Otp Number is :- "+value,mobile);
//						 
//					
//					
//				}
//
//			}
//		 
//		
//		
//		
		
		
		
		if(mFile!=null) {
		
//	 	MultipartFile mFile =  userRegistration.getUserImage() ;
		MasterBook masterBook = masterBookRepository.findMasterBook(1l, bookCode);
		BigDecimal lastNumberValue;
		lastNumberValue = masterBook.getLastNo();
		long longValue;
		longValue = lastNumberValue.longValue() + 1;
		BigDecimal newDecimalValue = new BigDecimal(longValue);
		String fileName=masterBook.getPrefixBook() + newDecimalValue;
		String extension = FilenameUtils.getExtension(mFile.getOriginalFilename());
		System.out.println(extension);
		fileName = fileName + "." + "jpg";
		//logger.info(" File with extension " + fileName);
		
		userRegistration.setDocumentName(fileName);
		
		String path = File.separator + "assets" + File.separator + "ProjectDoc";
		userRegistration.setDocumentFileLocation(path);
		
		String dbPath = path + File.separator + fileName;
	   storageService.store(mFile, path, fileName);
	   masterBook.setLastNo(newDecimalValue);
	  masterBookRepository.save(masterBook);
		
	  userRegistration.setDocumentFileLocation(dbPath	);
		}	
		return userRegistrationRepository.save(userRegistration);
	}

	public UserRegistration getUserByToken(String token) {

		UserRegistration userRegistration = userRegistrationRepository.getUserByToken(token);

		if (userRegistration != null) {
			userRegistration.setIsActive(true);
			userRegistrationRepository.save(userRegistration);
			return userRegistration;
		}
		return null;

	}

	
	public String store(MultipartFile file, String filePath, String fileName) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
					rootPath = rootPath + File.separator + "webapps" + File.separator + filePath;
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();


				return serverFile.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
				return "You failed to upload " + fileName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + fileName + " because the file was empty.";
		}
	
	
		}
	
}
