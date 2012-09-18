package org.saibaba.common.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.saibaba.common.service.UserMergeService;
import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;
import org.saibaba.fw.service.impl.AbstractServiceImpl;


public class UserMergeServiceImpl extends AbstractServiceImpl implements UserMergeService {

	@Override
	public void mergeUserRegistration(User source, User target) throws ServiceException {
		Validate.notNull(source, "User source must not be null.");
		Validate.notNull(target, "User target must not be null.");
		
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setPhoneNumber(source.getPhoneNumber());
		target.setEmail(source.getEmail());
		target.setInvalidLoginAttempt(0);
		target.setStatus(source.getStatus());
		target.setRole(source.getRole());
		target.setPassword(source.getPassword());
	}
	
	@Override
	public void mergeUserProfile(User source, User target) throws ServiceException {
		Validate.notNull(source, "User source must not be null.");
		Validate.notNull(target, "User target must not be null.");
		
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setPhoneNumber(source.getPhoneNumber());
		if(StringUtils.isNotBlank(source.getPassword()) ){
			target.setPassword(source.getPassword());
		}
		target.setRole(source.getRole());
	}

	
}