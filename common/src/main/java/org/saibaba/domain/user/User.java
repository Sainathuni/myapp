package org.saibaba.domain.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.saibaba.domain.lookup.Privilege;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.security.SecurityConstants;
import org.saibaba.fw.domain.KeyedEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class User extends KeyedEntity implements UserDetails {
	private static final long serialVersionUID = 1779793588084098501L;
	
	@NotBlank
    @Length(min = 1, max = 50)
	private String firstName;
	
	@NotBlank
    @Length(min = 1, max = 50)
	private String lastName;
	
	
	private String phoneNumber;
	
	@NotBlank
    @Length(min = 5)
	private String email;
	private Integer invalidLoginAttempt;
	private String existingPassword;
	

	@NotBlank
    @Length(min = 3)
	private String password;
	
	@NotBlank
    @Length(min = 3)
	private String confirmPassword;
	
	private Timestamp passwordDate;
	
	
	private UserStatus status;

	private Role role;
	
	private Collection<GrantedAuthority> grantedAuthorities;
	
	public String getExistingPassword() {
		return existingPassword;
	}

	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	
		public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getInvalidLoginAttempt() {
		return invalidLoginAttempt;
	}

	public void setInvalidLoginAttempt(Integer invalidLoginAttempt) {
		this.invalidLoginAttempt = invalidLoginAttempt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getPasswordDate() {
		return passwordDate;
	}


	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		GrantedAuthorityImpl ga = null;
		if (grantedAuthorities == null){
			grantedAuthorities = new ArrayList<GrantedAuthority>();
			//GrantedAuthorityImpl ga = new GrantedAuthorityImpl(SecurityConstants.ROLE_USER);
			//grantedAuthorities.add(ga);
			if(role != null) {
				ga = new GrantedAuthorityImpl(SecurityConstants.ROLE_SUFFIX + role.getCode());
				grantedAuthorities.add(ga);
				if(role.getPrivileges() != null) {
					for(Privilege privilege : role.getPrivileges()) {
						ga = new GrantedAuthorityImpl(SecurityConstants.ROLE_SUFFIX + privilege.getCode());
						grantedAuthorities.add(ga);
					}
				}
			}
		}
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return status != null ? UserStatus.APPROVED.equals(status.getCode()): false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return status != null ? UserStatus.APPROVED.equals(status.getCode()): false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return status != null ? UserStatus.APPROVED.equals(status.getCode()): false;
	}

	@Override
	public boolean isEnabled() {
		return status != null ? UserStatus.APPROVED.equals(status.getCode()): false;
	}
}
