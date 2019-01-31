package com.csipl.insurance.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;


public class UserRegistrationDTO {
	

	private Long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Long mobile;
	private String country;
	private String state;
	private String city;
	private Long pinCode;
	private MultipartFile userImage;
	private String address;
	private String emailId;
	private String category;
	private String nameOfOrgination;
	private String membershipLevel;
	private String courtsOfPractice;
	private String areaOfPractice;
	private String goPremium;
	private boolean locationSharing;
	private String prefferedContactMode;
	private String socialMediaLink;
	private String documentFileLocation;
	private String documentName;
	
	private String OTP;
	 

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentFileLocation() {
		return documentFileLocation;
	}

	public void setDocumentFileLocation(String documentFileLocation) {
		this.documentFileLocation = documentFileLocation;
	}
	
	//private MultipartFile documentFile;
	
	
	//private String password;
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}

//public MultipartFile getDocumentFile() {
//		return documentFile;
//	}
//	public void setDocumentFile(MultipartFile documentFile) {
//		this.documentFile = documentFile;
//	}
	//	private Boolean isActive;
//    private String verificationToken;
//	
//	public Boolean getIsActive() {
//		return isActive;
//	}
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//	public String getVerificationToken() {
//		return verificationToken;
//	}
//	public void setVerificationToken(String verificationToken) {
//		this.verificationToken = verificationToken;
//	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getPinCode() {
		return pinCode;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}
	 
	public MultipartFile getUserImage() {
		return userImage;
	}
	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNameOfOrgination() {
		return nameOfOrgination;
	}
	public void setNameOfOrgination(String nameOfOrgination) {
		this.nameOfOrgination = nameOfOrgination;
	}
	public String getMembershipLevel() {
		return membershipLevel;
	}
	public void setMembershipLevel(String membershipLevel) {
		this.membershipLevel = membershipLevel;
	}
	public String getCourtsOfPractice() {
		return courtsOfPractice;
	}
	public void setCourtsOfPractice(String courtsOfPractice) {
		this.courtsOfPractice = courtsOfPractice;
	}
	public String getAreaOfPractice() {
		return areaOfPractice;
	}
	public void setAreaOfPractice(String areaOfPractice) {
		this.areaOfPractice = areaOfPractice;
	}
	public String getGoPremium() {
		return goPremium;
	}
	public void setGoPremium(String goPremium) {
		this.goPremium = goPremium;
	}
	public boolean isLocationSharing() {
		return locationSharing;
	}
	public void setLocationSharing(boolean locationSharing) {
		this.locationSharing = locationSharing;
	}
	public String getPrefferedContactMode() {
		return prefferedContactMode;
	}
	public void setPrefferedContactMode(String prefferedContactMode) {
		this.prefferedContactMode = prefferedContactMode;
	}
	public String getSocialMediaLink() {
		return socialMediaLink;
	}
	public void setSocialMediaLink(String socialMediaLink) {
		this.socialMediaLink = socialMediaLink;
	}
	
	@Override
	public String toString() {
		return "UserRegistration [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", mobile=" + mobile + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", pinCode=" + pinCode + ", userImage=" + userImage + ", address=" + address
				+ ", emailId=" + emailId + ", category=" + category + ", nameOfOrgination=" + nameOfOrgination
				+ ", membershipLevel=" + membershipLevel + ", courtsOfPractice=" + courtsOfPractice
				+ ", areaOfPractice=" + areaOfPractice + ", goPremium=" + goPremium + ", locationSharing="
				+ locationSharing + ", prefferedContactMode=" + prefferedContactMode + ", socialMediaLink="
				+ socialMediaLink + "]";
	}

}