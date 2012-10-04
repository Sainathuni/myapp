package org.saibaba.common.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.FileInfoDao;
import org.saibaba.common.service.MiscService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.KeyValue;
import org.saibaba.domain.common.UploadItem;
import org.saibaba.domain.misc.FileInfo;
import org.saibaba.domain.user.User;
import org.saibaba.fw.exception.ServiceException;
import org.saibaba.fw.utils.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MiscServiceImpl extends AbstractCommonServiceImpl implements
		MiscService {

	private FileInfoDao fileInfoDao;

	public static final String FORWARD_SLASH = "/";

	private String contentRootPath;
	
	private String contentRootFilesLocation;

	public FileInfoDao getFileInfoDao() {
		return fileInfoDao;
	}

	public void setFileInfoDao(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

	public String getContentRootPath() {
		return contentRootPath;
	}

	public void setContentRootPath(String contentRootPath) {
		this.contentRootPath = contentRootPath;
	}

	public String getContentRootFilesLocation() {
		return contentRootFilesLocation;
	}

	public void setContentRootFilesLocation(String contentRootFilesLocation) {
		this.contentRootFilesLocation = contentRootFilesLocation;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FileInfo getFileInfoById(Long id) throws ServiceException {
		try {
			return getFileInfoDao().getFileInfoById(id);
		} catch (Exception ex) {
			throw new ServiceException((new StringBuilder()).append(
					"Error getting file info id = ").append(id).toString(), ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FileInfo> getFileInfoByMandirId(Long mandirId)
			throws ServiceException {
		try {
			return this.getFileInfoDao().getFileInfoByMandirId(mandirId);
		} catch (DaoException ex) {
			throw new ServiceException("Error getting file info by mandir Id:"
					+ mandirId, ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult updateFileInfo(FileInfo fileInfo)
			throws ServiceException {
		try {
			InvocationResult result = new InvocationResult();
			List<KeyValue> errors = new ArrayList<KeyValue>();

			FileInfo upadteFileInfo = this.getFileInfoDao().updateFileInfo(
					fileInfo);
			result.setStatus(InvocationResult.SUCCESS);
			result.setResult(upadteFileInfo);
			return result;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult addFileInfo(FileInfo fileInfo)
			throws ServiceException {
		try {
			InvocationResult result = new InvocationResult();
			List<KeyValue> errors = new ArrayList<KeyValue>();

			FileInfo upadteFileInfo = this.getFileInfoDao().addFileInfo(
					fileInfo);
			result.setStatus(InvocationResult.SUCCESS);
			result.setResult(upadteFileInfo);
			return result;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	// @Secured( { SecurityConstants.ROLE_SA })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public InvocationResult deleteFileInfo(Long fileInfoId)
			throws ServiceException {
		try {
			InvocationResult result = new InvocationResult();
			this.getFileInfoDao().deleteFileInfo(fileInfoId);
			result.setStatus(InvocationResult.SUCCESS);
			return result;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Validate.notNull(fileInfoDao, "fileInfoDao must not be null");
		Validate.notNull(contentRootPath, "contentRootPath must not be null");
	}

	public InvocationResult addFileItem(UploadItem uploadItem, User user)
			throws ServiceException {
		FileInfo info = buildFileInfo(uploadItem, user);
		writeFile(uploadItem, info.getUrl());
		return addFileInfo(info);
	}

	private FileInfo buildFileInfo(UploadItem item, User user) {
		FileInfo info = new FileInfo();
		info.setMandirId(item.getMandirId());
		info.setFileName(item.getName());
		info.setUrl(getURL(item));
		info.setDescription(item.getDescription());
		if(user != null){
			info.setCreatedBy(user.getCreatedBy());
			info.setCreatedDate(DateUtils.getCurrentTimestamp());
		}
		return info;
	}

	private String getURL(UploadItem item) {
		StringBuffer buff = new StringBuffer();
		buff.append(this.getContentRootPath()).append(FORWARD_SLASH).append(
				item.getMandirCode()).append(FORWARD_SLASH).append(
				item.getFileData().getOriginalFilename());
		return buff.toString();
	}
	
	private String getContentRootFilesLocationWithFileLocation(UploadItem item) {
		StringBuffer buff = new StringBuffer();
		String pathSep = System.getProperty("file.separator");
		buff.append(this.contentRootFilesLocation).append(pathSep).append(
				item.getMandirCode()).append(pathSep).append(
				item.getFileData().getOriginalFilename());
		System.out.println("File Path:"+buff.toString());
		return buff.toString();
	}
	
	private void writeFile(UploadItem uploadItem, String path) throws ServiceException {
		CommonsMultipartFile file = uploadItem.getFileData();
		try{
			//File writableFile = new File(path);
			////FileWriter fw = new FileWriter(writableFile.getAbsoluteFile());
			//BufferedWriter bw = new BufferedWriter(fw.w);
			//bw.write(file.getBytes());
			//bw.close();
			//ClassLoader.getSystemClassLoader()./
			File writableFile = new File(getContentRootFilesLocationWithFileLocation(uploadItem));
			File parentDir = writableFile.getParentFile();
			  if(! parentDir.exists()) {
			      parentDir.mkdirs();
			  }
			file.transferTo(writableFile);			
			//FileOutputStream fos = new FileOutputStream(path);
			//FileUtils.writeByteArrayToFile(file, file.getBytes());
			
//			FileOutputStream fos = new FileOutputStream(getContentRootFilesLocationWithFileLocation(uploadItem));
//	         try 
//	         {
//	            fos.write(file.getBytes());
//	         } 
//	         catch (IllegalStateException e) 
//	         {
//	            System.out.println(e);
//
//	         }
//	         finally   
//	         {
//	             fos.close();
//	         }

		}catch (Throwable th)
		{
			throw new ServiceException ("Unable save file:"+file.getOriginalFilename(), th);
		}
	}
}