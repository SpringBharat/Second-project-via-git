package com.csipl.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csipl.insurance.model.UserRegistration;
import com.csipl.insurance.model.Users;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, Long> {

	@Query(" from UserRegistration  where emailId=?1 and password=?2 and isActive=?3")
	public UserRegistration findUser(String username, String password, boolean isActive);
}
