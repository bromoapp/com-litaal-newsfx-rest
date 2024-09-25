package com.litaal.newsfx.model;

import com.litaal.newsfx.constant.ERole;

public class UserInfo {
	public static String TBLNAME = "user_info";

	private String uuid;
	private String firstName;
	private String lastName;
	private String gender;
	private String salutation;
	private Long birthYear;
	private String jobTitle;
	private String country;
	private ERole role;

	public UserInfo() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Long getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Long birthYear) {
		this.birthYear = birthYear;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

}
