package com.csipl.insurance.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

	
	public class UsersDTO {
	    private Long id;
	    private String username;
	    private String password;
	   // private String passwordConfirm;
	 //   private Set<Role> roles;

	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	   /* @Transient
	    public String getPasswordConfirm() {
	        return passwordConfirm;
	    }

	    public void setPasswordConfirm(String passwordConfirm) {
	        this.passwordConfirm = passwordConfirm;
	    }
*/
}
