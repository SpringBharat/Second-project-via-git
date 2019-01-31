package com.csipl.insurance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csipl.insurance.model.UserRegistration;
import com.csipl.insurance.model.Users;
import com.csipl.insurance.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserRegistration findUser(String email, String password , boolean isActive) {
		
		return userRepository.findUser(email, password, isActive );

	}

//	@Override
//	public Users findUser(String userName, String password) {
//		
//		return userRepository.findUser(userName, password);
//
//	}

}