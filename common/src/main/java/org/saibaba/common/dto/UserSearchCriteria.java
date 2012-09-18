package org.saibaba.common.dto;


public class UserSearchCriteria extends SearchCriteria {

	private static final long serialVersionUID = -9126152151218578055L;
	private String firstName;
	private String lastName;
	private String email;
	private String accountabilityCode;
	private String roleCode;
	private String statusCode;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountabilityCode() {
		return accountabilityCode;
	}

	public void setAccountabilityCode(String accountabilityCode) {
		this.accountabilityCode = accountabilityCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
