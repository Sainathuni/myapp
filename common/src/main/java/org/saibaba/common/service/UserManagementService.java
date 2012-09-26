package org.saibaba.common.service;

import java.util.List;

import org.saibaba.common.dto.UserSearchCriteria;
import org.saibaba.common.exception.UserException;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;

public interface UserManagementService {

	public User getUserById(Long id) throws ServiceException;

	public User getUserByEmail(String email) throws ServiceException;
	
	public User validateUserCredentials(String email, String password) throws ServiceException, UserException;

	public InvocationResult updateUserProfile(User user)
			throws ServiceException;

	public List<User> searchUser(UserSearchCriteria criteria)
			throws ServiceException;

	public User approveUser(Long userId) throws ServiceException;

	public User rejectUser(Long userId) throws ServiceException;

	public User updateUser(User user) throws ServiceException;

	public void deleteUser(String email) throws ServiceException;

	public List<String> changePassword(String email, String oldPassword,
			String newPassword) throws ServiceException;

	public InvocationResult register(User user)
			throws ServiceException;

	public InvocationResult approveUser(String email)
			throws ServiceException;

	public InvocationResult rejectUser(String email)
			throws ServiceException;
	
	public void forgotPassword(String email) throws ServiceException,
	UserException ;

}
