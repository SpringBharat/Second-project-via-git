package com.csipl.insurance.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csipl.insurance.model.UserRegistration;

@Service
public interface UserRegistrationService {
	
	public UserRegistration save(UserRegistration userRegistration, MultipartFile mFile)throws Exception;
	//public List<UserRegistration> findAllCandidateIdProofs(Long candidateId);
	public UserRegistration getUserByToken(String token);
}
