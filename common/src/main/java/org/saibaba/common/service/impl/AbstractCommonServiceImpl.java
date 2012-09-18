package org.saibaba.common.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.Validate;
import org.saibaba.common.persistent.impl.GenericDaoImpl;
import org.saibaba.common.service.LookupService;
import org.saibaba.domain.user.User;
import org.saibaba.fw.service.impl.AbstractServiceImpl;
import org.saibaba.fw.utils.DateUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.interceptor.TransactionInterceptor;

public class AbstractCommonServiceImpl extends AbstractServiceImpl {

	private GenericDaoImpl genericDao;
	private LookupService lookupService;
	protected AbstractCommonServiceImpl() {
		super();
	}

	protected User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	protected Timestamp getCurrentTimestamp() {
		return DateUtils.getCurrentTimestamp();
	}
	
	protected Date getCurrentDate() {
		return DateUtils.getCurrentDate();
	}


	protected void setRollbackOnly() {
		TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
	}

	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Validate.notNull(genericDao, "genericDao must not be null");
		Validate.notNull(lookupService, "lookupService must not be null");
			
	}

	public GenericDaoImpl getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDaoImpl genericDao) {
		this.genericDao = genericDao;
	}

	public LookupService getLookupService() {
		return lookupService;
	}

	public void setLookupService(LookupService lookupService) {
		this.lookupService = lookupService;
	}	
}
