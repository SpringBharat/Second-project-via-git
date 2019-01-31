/**
 *  Bharat PatelDec 14, 2018
 */
package com.csipl.insurance.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csipl.insurance.model.UserRegistration;
@Repository
public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Long> {

    @Query(" from UserRegistration where  userId=?1 ")
    public UserRegistration findProject(Long  userId);
    
    @Query(" from UserRegistration where  verificationToken=?1 ")
    public UserRegistration getUserByToken(String  verificationToken);
    
}
