
package com.csipl.insurance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csipl.insurance.adaptor.UserRegistrationAdaptor;
import com.csipl.insurance.commons.AppUtils;
import com.csipl.insurance.commons.OTPService;
import com.csipl.insurance.dto.UserRegistrationDTO;
import com.csipl.insurance.model.UserRegistration;

//@Configuration
//@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/varification")
public class VarificationController {
	@Autowired
	UserRegistrationAdaptor userRegistrationAdaptor;
	@Autowired
	OTPService otpService;

	@RequestMapping(value = "/otp", method = RequestMethod.POST/* , consumes = { "multipart/form-data" } */)
	public UserRegistrationDTO senOTP(@RequestBody UserRegistrationDTO userRegistrationDTO,
			/*
			 * @RequestPart("info") @RequestBody UserRegistrationDTO
			 * userRegistrationDTO,/*@RequestPart("userImage") MultipartFile mFile,
			 */ HttpServletRequest req) throws Exception {

		// String mobile = String.valueOf(userRegistrationDTO.getMobile());
		//
		// int otp = otpService.generateOtp(mobile);
		// new AppUtils().sendOtpBySms("hii-" + otp, mobile);
		//
		// return userRegistrationDTO;

		String mobile = String.valueOf(userRegistrationDTO.getMobile());
		int oldOTP = otpService.getOtp(mobile);
		System.out.println(oldOTP);

		if (oldOTP > 0) {
			new AppUtils().sendOtpBySms("Hi Your Otp Number is - " + oldOTP, mobile);
		} else {
			int otp = otpService.generateOtp(mobile);
			new AppUtils().sendOtpBySms("Hi Your Otp Number is - " + otp, mobile);
		}
		return userRegistrationDTO;
	}

	@RequestMapping(path = "/verifyOtp", method = RequestMethod.POST)
	public Map<String, Object> varifyOtp(
			/* @RequestParam("otp") String otp, */ @RequestBody UserRegistrationDTO userRegistrationDTO,
			HttpServletRequest req) {

		// String username = userDto.getFirstName();
		// logger.info("session otp value: "+username+"" + otpService.getOtp(username));

		Map<String, Object> map = new HashMap<>();
		String mobile = String.valueOf(userRegistrationDTO.getMobile());
		String otp = userRegistrationDTO.getOTP();
		int otps = otpService.getOtp(mobile);
		System.out.println(otps);
		if (otpService.getOtp(mobile) == (Integer.valueOf(otp))) {
			otpService.clearOTP(mobile);
			System.out.println("done");
			// User user = loginRepository.findUserByUserName(username);
			map.put("message", true);
			return map;
		} else {
			System.out.println("wrong otp");
			map.put("message", false);
			return map;
		}

	}

}
