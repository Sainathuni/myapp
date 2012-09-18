package org.saibaba.common.persistent;

import java.util.List;

import org.saibaba.common.dto.UserSearchCriteria;
import org.saibaba.domain.user.User;


public interface UserManagementDao {

	/**
	 * Gets the user by user id.
	 * @param id the id of the user
	 * @return
	 * @throws DaoException
	 */
    public User getUserById(Long id) throws DaoException;

    /**
     * Gets the user by email address.
     * @param email the user's email address
     * @return
     * @throws DaoException
     */
    public User getUserByEmail(String email) throws DaoException;

     
    /**
     * Searches the user by the given criteria.
     * @param criteria the criteria
     * @return
     * @throws DaoException
     */
    public List<User> searchUser(UserSearchCriteria criteria) throws DaoException;

    /**
     * Adds a new user.
     * @param user the new user data
     * @return
     * @throws DaoException
     */
    public User addUser(User user) throws DaoException;
    
    /**
     * Updates an existing user.
     * @param user the updated user data
     * @return
     * @throws DaoException
     */
    public User updateUser(User user) throws DaoException;
    
    /**
     * Deletes the user.
     * @param userId the user id of the user to be deleted
     * @throws DaoException
     */
    public void deleteUser(Long userId) throws DaoException;
    
    
    /**
     * Retrieves Users by roles and status code
     * @param roleCodes
     * @param statusCode
     * @return
     * @throws DaoException
     */
    public List<User> getUsersByRoleStatus(String roleCode, String statusCode) throws DaoException;
    
        
    
}