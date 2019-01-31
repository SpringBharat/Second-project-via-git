package com.csipl.insurance.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.csipl.insurance.adaptor.UserRegistrationAdaptor;
import com.csipl.insurance.dto.UserRegistrationDTO;
import com.csipl.insurance.service.UserRegistrationService;

@Configuration
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	@Autowired
	UserRegistrationService userRegistrationService;

	@Autowired
	UserRegistrationAdaptor userRegistrationAdaptor;

	@RequestMapping(value = "/save", method = RequestMethod.POST/*, consumes = { "multipart/form-data" }*/)
	public UserRegistrationDTO saveUserRegistration( /*@RequestPart("info")*/ @RequestBody UserRegistrationDTO userRegistrationDTO,/*@RequestPart("userImage") MultipartFile mFile,*/ HttpServletRequest req )
			throws Exception {
		logger.info("saveUserRegistration is calling : " + " : UserRegistrationDTO " + userRegistrationDTO.toString());
		
		
 		MultipartFile mFile=null;
//		if(userRegistrationDTO.getUserImage()!=null) {
//			  mFile =  userRegistrationDTO.getUserImage();
//		}
		
		 
		return userRegistrationAdaptor.databaseModelToUiDto(
				userRegistrationService.save(userRegistrationAdaptor.uiDtoToDatabaseModel(userRegistrationDTO),  mFile));
	}

	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token,
			@RequestParam("date") String date) {
		System.out.println("Singya................." + date);
		if (date != null && date != "" && token != null && token != "") {
			LocalDateTime end1 = LocalDateTime.parse(date);
			System.out.println("End............" + end1);
			LocalDateTime now = LocalDateTime.now();
			System.out.println(now);
			Duration duration = Duration.between(now, end1);
			long diff = Math.abs(duration.toHours());
			System.out.println("Differance=============" + diff);
			if (diff <= 24) {
				// if (token != null && token != "") {
				System.out.println("userId    ==============" + token);
				if (userRegistrationService.getUserByToken(token) != null) {

					userRegistrationAdaptor.databaseModelToUiDto(userRegistrationService.getUserByToken(token));

					return " <h1 style=color:green ; text-align: center>Verified</h1> ";
				}
				// }
			}

		}

		// if (token != null && token != "") {
		// System.out.println("userId ==============" + token);
		// if (userRegistrationService.getUserByToken(token) != null) {
		// return
		// userRegistrationAdaptor.databaseModelToUiDto(userRegistrationService.getUserByToken(token));
		//
		// }
		// }
		return " <h1 style=color:#D35400>Link Expired</h1> ";
	}
}
