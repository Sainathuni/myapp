package org.saibaba.common.persistent.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.FileInfoDao;
import org.saibaba.domain.misc.FileInfo;
import org.saibaba.domain.user.User;
import org.saibaba.fw.utils.CollectionUtils;


public class FileInfoDaoImpl extends GenericDaoImpl implements FileInfoDao {

	private static String GET_FILE_INFO_BY_MANDIR_ID = "fileInfoQuery_GetByMandirId";
	private static String GET_FILE_INFO_BY_URL = "fileInfoQuery_GetByURL";

	@Override
	public FileInfo getFileInfoById(Long id) throws DaoException {
		return super.getByKey(FileInfo.class, id);
	}	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<FileInfo> getFileInfoByMandirId(Long mandirId)
			throws DaoException {
		Validate.notNull(mandirId, "mandirId must not be null");
		try {
			List list = findByNamedQueryAndNamedParam(GET_FILE_INFO_BY_MANDIR_ID, "mandirId", mandirId);
				return (list != null && list.size() > 0) ? new ArrayList<FileInfo>(list) : new ArrayList<FileInfo>();
			
		} catch (Exception ex) {
			throw new DaoException("getFileInfoByMandirId() mandirId = " + mandirId,ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<FileInfo> getFileInfoByURL(String url)
			throws DaoException {
		Validate.notNull(url, "url must not be null");
		try {
			List list = findByNamedQueryAndNamedParam(GET_FILE_INFO_BY_URL, "url", url);
				return (list != null && list.size() > 0) ? new ArrayList<FileInfo>(list) : new ArrayList<FileInfo>();
			
		} catch (Exception ex) {
			throw new DaoException("getFileInfoByURL() mandirId = " + url,ex);
		}
	}
	@Override
	public FileInfo addFileInfo(FileInfo fileInfo) throws DaoException {
		saveObject(fileInfo);
		return fileInfo;
	}	

	@Override
	public FileInfo updateFileInfo(FileInfo fileInfo) throws DaoException {
		saveObject(fileInfo);
		return fileInfo;
	}	
	
	@Override

	public void deleteFileInfo(Long fileInfoId) throws DaoException {
		super.removeObject(FileInfo.class, fileInfoId);
	}
}