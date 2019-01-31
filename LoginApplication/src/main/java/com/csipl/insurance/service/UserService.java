package com.csipl.insurance.service;

import com.csipl.insurance.model.UserRegistration;
import com.csipl.insurance.model.Users;

public interface UserService {
	public UserRegistration findUser(String email, String password, boolean isActive);
	
//	public Users findUser(String userId, String password);
}
