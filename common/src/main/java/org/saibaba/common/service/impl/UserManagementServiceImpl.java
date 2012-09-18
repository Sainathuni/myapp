package org.saibaba.common.service.impl;

import static org.saibaba.common.service.UserConstants.APPROVAL_ERROR;
import static org.saibaba.common.service.UserConstants.APPROVED_SUCCESSFILLY;
import static org.saibaba.common.service.UserConstants.EMAIL_NOT_VALID;
import static org.saibaba.common.service.UserConstants.INVALID_OLD_PASSWORD;
import static org.saibaba.common.service.UserConstants.INVALID_REQUEST;
import static org.saibaba.common.service.UserConstants.REGISTRATION_SUCCESSFILLY;
import static org.saibaba.common.service.UserConstants.REJECTED_SUCCESSFILLY;
import static org.saibaba.common.service.UserConstants.REJECTION_ERROR;
import static org.saibaba.common.service.UserConstants.USER001;
import static org.saibaba.common.service.UserConstants.USER_NOT_ACTIVE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.saibaba.common.dto.UserSearchCriteria;
import org.saibaba.common.exception.UserException;
import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.UserManagementDao;
import org.saibaba.common.service.UserConstants;
import org.saibaba.common.service.UserManagementService;
import org.saibaba.common.service.UserMergeService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.KeyValue;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.security.SecurityConstants;
import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;
import org.saibaba.fw.utils.CollectionUtils;
import org.springframework.mail.MailException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserManagementServiceImpl extends AbstractCommonServiceImpl
		implements UserManagementService {

	private UserManagementDao userManagementDao;
	private UserMergeService userMergeService;

	public UserMergeService getUserMergeService() {
		return userMergeService;
	}

	public void setUserMergeService(UserMergeService userMergeService) {
		this.userMergeService = userMergeService;
	}

	public void setUserManagementDao(UserManagementDao userManagementDao) {
		this.userManagementDao = userManagementDao;
	}

	public UserManagementDao getUserManagementDao() {
		return userManagementDao;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserById(Long id) throws ServiceException {
		try {
			return getUserManagementDao().getUserById(id);
		} catch (Exception ex) {
			throw new ServiceException((new StringBuilder()).append(
					"Error getting user id = ").append(id).toString(), ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByEmail(String email) throws ServiceException {
		try {
			return this.getUserManagementDao().getUserByEmail(email);
		} catch (DaoException ex) {
			throw new ServiceException("Error getting user", ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User loginUser(String email, String password)
			throws ServiceException, UserException {
		User user = null;
		try {
			user = this.getUserManagementDao().getUserByEmail(email);
			if (user == null) {
				throw new UserException(UserConstants.INVALID_USER_ID,
						"Invalid User Name and Password");
			}
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				throw new UserException(UserConstants.INVALID_PASSWORD,
						"Invalid User Name and Password");
			}
		} catch (DaoException ex) {
			throw new ServiceException("Error getting user", ex);
		}
	}

	@Override
	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> searchUser(UserSearchCriteria criteria)
			throws ServiceException {
		try {
			List<User> users = this.getUserManagementDao().searchUser(criteria);

			// Filter out the user' with superior roles
			List<String> roles = new ArrayList<String>();
			Role currentUserRole = this.getCurrentUser().getRole();

			return users;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public User approveUser(Long userId) throws ServiceException {
		try {
			User user = this.getUserManagementDao().getUserById(userId);

			// User currentUser =
			// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			user.setStatus(new UserStatus(UserStatus.APPROVED,
					UserStatus.APPROVED));

			User updatedUser = this.getUserManagementDao().updateUser(user);
			return updatedUser;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public User rejectUser(Long userId) throws ServiceException {
		try {
			User user = this.getUserManagementDao().getUserById(userId);

			User currentUser = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			user.setStatus(new UserStatus(UserStatus.REJECTED,
					UserStatus.REJECTED));

			User updatedUser = this.getUserManagementDao().updateUser(user);
			return updatedUser;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public User updateUser(User user) throws ServiceException {
		try {
			return this.getUserManagementDao().updateUser(user);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult updateUserProfile(User updatedUser)
			throws ServiceException {
		try {
			InvocationResult result = new InvocationResult();
			List<KeyValue> errors = new ArrayList<KeyValue>();

			// User currentUser =
			// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// Get the existing user
			User existingUser = this.getUserManagementDao().getUserByEmail(
					updatedUser.getEmail());
			if (!updatedUser.getPassword().equals(updatedUser.getConfirmPassword())) {
				errors.add(new KeyValue("confirmPassword",
						UserConstants.PASSWORD_CONFIRM_PASSWORD_MATCH_ERROR, null));
			}
//			 errors = this.validateUserProfile(updatedUser,
//					existingUser);
			if (CollectionUtils.isEmpty(errors)) {
				// Merge updated user data
				this.getUserMergeService().mergeUserProfile(updatedUser,
						existingUser);
				// Save the user
				User updtUser = this.getUserManagementDao().updateUser(
						existingUser);
				result.setStatus(InvocationResult.SUCCESS);
				result.setResult(updtUser);
			} else {
				result.setStatus(InvocationResult.ERROR);
				result.setErrors(errors);
			}
			return result;

		} catch (ServiceException se) {
			throw se;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void deleteUser(String email) throws ServiceException {
		try {
			User user = this.getUserManagementDao().getUserByEmail(email);
			user.setStatus(new UserStatus(UserStatus.DELETED,
					UserStatus.DELETED));
			this.getUserManagementDao().updateUser(user);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public List<String> changePassword(String email, String oldPassword,
			String newPassword) throws ServiceException {

		User user = this.getUserManagementDao().getUserByEmail(email);

		List<String> errorKeys = new ArrayList<String>();
		if (user == null || user.getEmail().equalsIgnoreCase(email) == false) {
			errorKeys.add(INVALID_REQUEST);
			return errorKeys;
		}

		if (UserStatus.APPROVED.equals(user.getStatus().getCode()) == false) {
			errorKeys.add(USER_NOT_ACTIVE);
			return errorKeys;
		}
		if (!user.getPassword().equals(oldPassword)) {
			errorKeys.add(INVALID_OLD_PASSWORD);
			return errorKeys;
		}
		if (errorKeys.isEmpty()) {
			user.setPassword(newPassword);
			user.setInvalidLoginAttempt(new Integer(0));
			this.getUserManagementDao().updateUser(user);
		}

		return errorKeys;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Validate.notNull(userManagementDao,
				"userManagementDao must not be null");
		Validate.notNull(userMergeService, "userMergeService must not be null");
	}

	private List<KeyValue> validateUserProfile(User updatedUser, User existingUser)
			throws ServiceException {

		List<KeyValue> errors = new ArrayList<KeyValue>();

		if (StringUtils.isNotEmpty(updatedUser.getPassword())) {
			if (StringUtils.equals(updatedUser.getPassword(), existingUser
					.getPassword()) == false) {
				errors.add(new KeyValue("password",INVALID_OLD_PASSWORD,null));
				return errors;
			}
		}

		return errors;
	}

	@Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class, readOnly = true)
	public List<User> getUsersByRoleStatus(String roleCode, String statusCode)
			throws ServiceException {
		Validate.notNull(roleCode, "roleCode Cannot be null!");
		Validate.notNull(statusCode, "statusCode Cannot be null!");
		try {
			return getUserManagementDao().getUsersByRoleStatus(roleCode,
					statusCode);
		} catch (Exception ex) {
			throw handleException(USER001, ex, new Object[] { roleCode,
					statusCode });
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult register(User user) throws ServiceException {
		try {
			InvocationResult result = new InvocationResult();
			List<KeyValue> errors = new ArrayList<KeyValue>();
			;
			User existingUser = this.getUserManagementDao().getUserByEmail(
					user.getEmail());
			if (existingUser != null) {
				errors.add(new KeyValue("email", UserConstants.EMAIL_EXISTS, null));

			}
			if (!user.getPassword().equals(user.getConfirmPassword())) {
				errors.add(new KeyValue("confirmPassword",
						UserConstants.PASSWORD_CONFIRM_PASSWORD_MATCH_ERROR, null));
			}

			if (errors.isEmpty()) {

				User updatedUser = existingUser == null ? new User()
						: existingUser;

				this.getUserMergeService().mergeUserRegistration(user,
						updatedUser);

				// Genarete confirmation code
				Timestamp currentTimestamp = getCurrentTimestamp();

				// Update Email
				this.getUserManagementDao().addUser(updatedUser);

				// Send Confirmation message to the user's email
				try {
					result.setStatus(InvocationResult.SUCCESS);
					result.addMessageKey(REGISTRATION_SUCCESSFILLY);
				} catch (Exception ex) {
					result.setStatus(InvocationResult.ERROR);
					result.addErrorKey(EMAIL_NOT_VALID);
					setRollbackOnly();
				}
			} else {
				result.setStatus(InvocationResult.ERROR);
				result.setErrors(errors);
			}
			return result;
		} catch (MailException mailEx) {
			throw mailEx;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public InvocationResult approveUser(String email) throws ServiceException {
		Validate.notNull(email, "email must not be null");

		try {
			InvocationResult result = new InvocationResult();
			User user = this.getUserManagementDao().getUserByEmail(email);
			if (user != null) {
				// User currentUser =
				// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (UserStatus.PENDING.equals(user.getStatus().getCode())) {
					result.setStatus(InvocationResult.SUCCESS);
					user.setStatus(new UserStatus(UserStatus.APPROVED,
							UserStatus.APPROVED));
					this.getUserManagementDao().updateUser(user);
					result.addMessageKey(APPROVED_SUCCESSFILLY);
				} else {
					result.setStatus(InvocationResult.ERROR);
					result.addErrorKey(APPROVAL_ERROR);
				}
			} else {
				result.setStatus(InvocationResult.ERROR);
				result.addErrorKey(APPROVAL_ERROR);
			}
			return result;

		} catch (DaoException ex) {
			throw new ServiceException("Error while approving.", ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult rejectUser(String email) throws ServiceException {
		Validate.notNull(email, "email must not be null");

		try {
			InvocationResult result = new InvocationResult();
			User user = this.getUserManagementDao().getUserByEmail(email);
			if (user != null) {
				result.setStatus(InvocationResult.SUCCESS);
				// User currentUser =
				// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				user.setStatus(new UserStatus(UserStatus.REJECTED,
						UserStatus.REJECTED));

				this.getUserManagementDao().updateUser(user);
				result.addMessageKey(REJECTED_SUCCESSFILLY);
			} else {
				result.setStatus(InvocationResult.ERROR);
				result.addErrorKey(REJECTION_ERROR);
			}
			return result;

		} catch (DaoException ex) {
			throw new ServiceException("Error while rejecting.", ex);
		}
	}

}