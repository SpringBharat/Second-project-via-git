package com.csipl.insurance.adaptor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.csipl.insurance.dto.UserRegistrationDTO;
import com.csipl.insurance.model.UserRegistration;
@Component
public class UserRegistrationAdaptor implements Adaptor<UserRegistrationDTO, UserRegistration> {
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationAdaptor.class);
	
	public UserRegistration userRegistrationofDtoToDatabaseModel(UserRegistrationDTO userRegistrationDTO, long projectId) {
		logger.info("UserRegistrationAdaptor.UserRegistrationofDtoToDatabaseModel()");		
		UserRegistration userRegistration = new UserRegistration();
		logger.info("UserRegistrationAdaptor.databaseModelToUiDto()");
		BeanUtils.copyProperties(userRegistrationDTO, userRegistration); 
		return userRegistration;
	}
	@Override
	public UserRegistrationDTO databaseModelToUiDto(UserRegistration dbobj) {
		UserRegistrationDTO userRegiDTO = new UserRegistrationDTO();
		logger.info("UserRegistrationAdaptor.databaseModelToUiDto()");
		BeanUtils.copyProperties(dbobj, userRegiDTO); 
		return userRegiDTO;
	}

	
	

	
	
	@Override
	public List<UserRegistration> uiDtoToDatabaseModelList(List<UserRegistrationDTO> uiobj) {
		logger.info("UserRegistrationAdaptor.uiDtoToDatabaseModelList()");// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRegistrationDTO> databaseModelToUiDtoList(List<UserRegistration> dbobj) {
		logger.info("UserRegistrationAdaptor.databaseModelToUiDtoList()");
		
		return null;

	}

	@Override
	public UserRegistration uiDtoToDatabaseModel(UserRegistrationDTO userRegistrationDTO) {
		
		logger.info("UserRegistrationAdaptor.UserRegistrationofDtoToDatabaseModel()");		
		UserRegistration userRegistration = new UserRegistration();
		logger.info("UserRegistrationAdaptor.databaseModelToUiDto()");
		BeanUtils.copyProperties(userRegistrationDTO, userRegistration); 
		return userRegistration;
	
	}

	
}
