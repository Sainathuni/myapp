package org.saibaba.common.persistent.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.saibaba.common.dto.SearchCriteria;
import org.saibaba.common.dto.UserSearchCriteria;
import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.UserManagementDao;
import org.saibaba.domain.user.User;
import org.saibaba.fw.utils.CollectionUtils;


public class UserManagementDaoImpl extends GenericDaoImpl implements UserManagementDao {

	private static String GET_USER_BY_EMAIL = "userQuery_GetByEmail";
	private static final String USER_SEARCH_QUERY = "FROM User usr";
	private static final String USER_SORT_CLAUSE = "ORDER BY usr.lastName, usr.firstName";
	private static String GET_USER_BY_ROLE_STATUS = "userQuery_GetByRoleStatus";
	

	@Override
	public User getUserById(Long id) throws DaoException {
		return super.getByKey(User.class, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User getUserByEmail(String email) throws DaoException {
        Validate.notNull(email, "email must not be null");
        try {
        	List users = findByNamedQueryAndNamedParam(GET_USER_BY_EMAIL, "email", email);
        	return users.size() < 1 ? null : (User)users.get(0);
        } catch(Exception ex) {
        	throw new DaoException((new StringBuilder()).append("getUserByEmail() email = ").append(email).toString(), ex);
        }
    }	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> searchUser(UserSearchCriteria criteria)
			throws DaoException {
		Validate.notNull(criteria, "criteria must not be null");
		try {
			List<String> paramList = new ArrayList<String>();
			List<Object> valueList = new ArrayList<Object>();
			
			//If LOGIN GATE is not set assume AND.
			if(StringUtils.isEmpty(criteria.getLogicGate())) {
				criteria.setLogicGate(SearchCriteria.LOGIC_GATE_AND);
			}

			String whereClause = buildUserSearchParams(criteria, paramList,	valueList);
			if (StringUtils.isNotEmpty(whereClause) && paramList.size() > 0) {
				String hqlQuery = USER_SEARCH_QUERY + whereClause + " " + USER_SORT_CLAUSE;
				List list = findByNamedParam(hqlQuery, 
											 CollectionUtils.toStringArray(paramList), 
											 CollectionUtils.toObjectArray(valueList));
				return (list != null && list.size() > 0) ? new ArrayList<User>(list) : new ArrayList<User>();
			} else {
				return new ArrayList<User>();
			}
		} catch (Exception ex) {
			throw new DaoException("searchUser() criteria = " + criteria,ex);
		}
	}

	@Override
	public User addUser(User user) throws DaoException {
		saveObject(user);
		return user;
	}	

	@Override
	public User updateUser(User user) throws DaoException {
		saveObject(user);
		return user;
	}	
	
	@Override

	public void deleteUser(Long userId) throws DaoException {
		super.removeObject(User.class, userId);
	}

	private String buildUserSearchParams(UserSearchCriteria userCriteria,
			List<String> paramList, List<Object> valueList) {

		List<String> criteriaList = new ArrayList<String>();

		if (StringUtils.isNotEmpty(userCriteria.getFirstName())) {
			criteriaList.add("usr.firstName like :firstName");
			paramList.add("firstName");
			valueList.add(userCriteria.getFirstName().toUpperCase() + "%");
		}

		if (StringUtils.isNotEmpty(userCriteria.getLastName())) {
			criteriaList.add("usr.lastName like :lastName");
			paramList.add("lastName");
			valueList.add(userCriteria.getLastName().toUpperCase() + "%");
		}

		if (StringUtils.isNotEmpty(userCriteria.getEmail())) {
			criteriaList.add("usr.email like :email");
			paramList.add("email");
			valueList.add(userCriteria.getEmail().toLowerCase() + "%");
		}
		
		StringBuffer whereClause = new StringBuffer();
		if (!criteriaList.isEmpty()) {
			whereClause.append(" WHERE ").append(criteriaList.get(0));
			for (int index = 0; index < criteriaList.size(); index++) {
				if (index != 0) {
					whereClause.append(" " + userCriteria.getLogicGate() + " ")
							.append(criteriaList.get(index));
				}
			}
		}
		return whereClause.toString();
	}		

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsersByRoleStatus(String roleCode, String statusCode) throws DaoException {
        Validate.notNull(roleCode, "roleCode must not be null");
        Validate.notNull(statusCode, "statusCode must not be null");
        try {
        	String[] paramNames = { "roleCode", "statusCode" };
			Object[] paramValues = { roleCode, statusCode };
        	List users =  findByNamedQueryAndNamedParam(GET_USER_BY_ROLE_STATUS, paramNames, paramValues);
        	return (users != null && users.size() > 0) ? (List<User>) users : null;
        } catch(Exception ex) {
        	throw new DaoException((new StringBuilder()).append("getUsersByRoleStatus() roleCode = ").append(roleCode)
        			.append(" status code").append(statusCode).toString(), ex);
        }
    }	
}