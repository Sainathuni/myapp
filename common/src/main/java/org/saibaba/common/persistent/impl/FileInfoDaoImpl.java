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
			List<String> paramList = new ArrayList<String>();
			List<Object> valueList = new ArrayList<Object>();
			paramList.add("mandirId");
			valueList.add(mandirId);
			List list = findByNamedQueryAndNamedParam(GET_FILE_INFO_BY_MANDIR_ID, "mandirId", mandirId);
				return (list != null && list.size() > 0) ? new ArrayList<FileInfo>(list) : new ArrayList<FileInfo>();
			
		} catch (Exception ex) {
			throw new DaoException("getFileInfoByMandirId() mandirId = " + mandirId,ex);
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