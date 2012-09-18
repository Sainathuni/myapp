package org.saibaba.common.service;

import java.util.List;

import org.saibaba.fw.domain.Lookup;
import org.saibaba.fw.exception.ServiceException;

public interface LookupService { 

	@SuppressWarnings("rawtypes") //test
	public List <Lookup> findAll(Class lookup) throws ServiceException;

	public List <Lookup> findAll(String classname) throws ServiceException;

	public Lookup findByCode(String className, String code) throws ServiceException;
	
	@SuppressWarnings("rawtypes")
	public Lookup findByCode(Class class1, String code) throws ServiceException;

}
