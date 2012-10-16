package org.saibaba.common.persistent;

import java.util.List;

import org.saibaba.domain.misc.FileInfo;


public interface FileInfoDao {

	/**
	 * Gets the file info by id.
	 * @param id the id of the file info
	 * @return
	 * @throws DaoException
	 */
    public FileInfo getFileInfoById(Long id) throws DaoException;

   

     
    /**
     * Gets the file info by mandir id.
     * @param mandirID the id of the mandir
     * @return
     * @throws DaoException
     */
    public List<FileInfo> getFileInfoByMandirId(Long mandirID) throws DaoException;

    /**
     * Adds a new file info.
     * @param fileInfo the new file info data
     * @return
     * @throws DaoException
     */
    public FileInfo addFileInfo(FileInfo fileInfo) throws DaoException;
    
    /**
     * Updates an existing fileInfo.
     * @param fileInfo the updated fileInfo data
     * @return
     * @throws DaoException
     */
    public FileInfo updateFileInfo(FileInfo fileInfo) throws DaoException;
    
    /**
     * Deletes the fileInfo.
     * @param fileInfoId the file info id of the file info to be deleted
     * @throws DaoException
     */
    public void deleteFileInfo(Long fileInfoId) throws DaoException;
    
    public List<FileInfo> getFileInfoByURL(String url)  throws DaoException;
      
}