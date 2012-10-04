
package org.saibaba.common.service;

import java.util.List;

import org.saibaba.common.AbstractCommonTest;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.user.User;

/**
 * 
 */
public class UserManagementTest extends AbstractCommonTest {

	private UserManagementService userManagementService;

	

	public UserManagementService getUserManagementService() {
		return userManagementService;
	}

	public void setUserManagementService(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	/**
	 * Instantiates a test case with the specific name
	 * 
	 * @param name
	 *            a test case name
	 */
	public UserManagementTest(String name) {
		super(name);
	}

	/**
	 * 
	 */
	public void testgetUserById() {
		try {
			
			User user = userManagementService.getUserById(new Long(1));
			log.info("User:{}", user);
		} catch (Throwable th) {
			log.error("Failed to get User", th);
		}
	}
	
	public void testgetUserByEmail() {
		try {
			
			User user = userManagementService.getUserByEmail("venkypk@saimail.com");
			log.info("User:{}", user);
		} catch (Throwable th) {
			log.error("Failed to get User", th);
		}
	}
	
	public void testregisterUser() {
		try {
			
			InvocationResult result = userManagementService.register(buildUser());
			log.info("Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	
	public void testapproveUser() {
		try {
			
			User user = userManagementService.approveUser(new Long(1));
			log.info("User:{}", user);
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	
	public void testapproveUserByEmail() {
		try {
			
			InvocationResult result = userManagementService.approveUser("sai@saimail.com");
			log.info("Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	public void testrejectUserByEmail() {
		try {
			
			InvocationResult result = userManagementService.rejectUser("sai@saimail.com");
			log.info("Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	public void testdeleteUserByEmail() {
		try {
			
			userManagementService.deleteUser("sai@saimail.com");
			log.info("Deleted:");
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	public void testchangePassword() {
		try {
			
			List<String> errors = userManagementService.changePassword("sai@saimail.com", "sai","saisai");
			log.info("Password Change errors:{}", errors);
		} catch (Throwable th) {
			log.error("Failed to register User", th);
		}
	}
	private User buildUser(){
		User user = new User();
		user.setEmail("sai@saimail.com");
		user.setFirstName("Sai-1");
		user.setLastName("Sai");
		user.setInvalidLoginAttempt(0);
		user.setPassword("sai");
		user.setPhoneNumber("111-222-4444");
		Role role = new Role();
		role.setCode(Role.USER);
		user.setRole(role);
		UserStatus status = new UserStatus();
		status.setCode(UserStatus.PENDING);
		user.setStatus(status);
		return user;
		
	}
		
}