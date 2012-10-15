package org.saibaba.common.service;

import java.util.List;

import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.UploadItem;
import org.saibaba.domain.misc.FileInfo;
import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;

public interface MiscService {

	public FileInfo getFileInfoById(Long id) throws ServiceException;

	public List<FileInfo> getFileInfoByMandirId(Long mandirId) throws ServiceException;
	
	public InvocationResult addFileInfo(FileInfo fileInfo) throws ServiceException;
	
	public InvocationResult addFileItem(UploadItem uploadItem, User user) throws ServiceException;
	
	public InvocationResult updateFileInfo(FileInfo fileInfo) throws ServiceException;
	
	public InvocationResult deleteFileInfo(Long fileInfoId) throws ServiceException;
	public InvocationResult deleteFileItem(Long id, String mandirCode) throws ServiceException;

}
