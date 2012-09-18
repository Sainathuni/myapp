package org.saibaba.common.service;

import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;
import org.springframework.security.access.annotation.Secured;

public interface UserMergeService {

	public void mergeUserRegistration(User source, User target) throws ServiceException;
	
	@Secured("ROLE_USER")
	public void mergeUserProfile(User source, User target) throws ServiceException;

}
