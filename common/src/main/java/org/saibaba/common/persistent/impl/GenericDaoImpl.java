package org.saibaba.common.persistent.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.EntityKey;
import org.saibaba.fw.domain.KeyedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("rawtypes")
public class GenericDaoImpl extends HibernateDaoSupport implements BeanNameAware, ApplicationContextAware {

	protected Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * An instance of serialVersionUID
	 */
	private static final long serialVersionUID = -126845924476530922L;

	/**
	 * An instance of name
	 */
	private String name = null;

	/**
	 * An instance of context
	 */
	private ApplicationContext context;

	/**
	 * A default constructor
	 */
	public GenericDaoImpl() {
		super();
	}

	/**
	 * Generic method to return a persistent instance of a given entity class
	 * and the given identifier, or null if not found
	 * 
	 * @param clazz
	 *            the entity class
	 * @param id
	 *            the entity's id
	 * @return a populated object (or null if id doesn't exist)
	 * @throws DaoException
	 *             Thrown if failed to query an object
	 */
	public <T> T getByKey(Class<T> clazz, Long id) throws DaoException {

		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			throw new DaoException("exception loading", e);
		}		
	}

	/**
	 * Generic method to return a persistent instance of a given entity class
	 * and the given identifier, or null if not found
	 * 
	 * @param clazz
	 *            the entity class
	 * @param id
	 *            the entity's id
	 * @return a populated object (or null if id doesn't exist)
	 * @throws DaoException
	 *             Thrown if failed to query an object
	 */
	public <T> T getByKey(Class<T> clazz, String code) throws DaoException {

		try {
			return getHibernateTemplate().get(clazz, code);
		} catch (DataAccessException e) {
			throw new DaoException("exception loading", e);
		}		
	}
	
	/**
	 * Convenience method to get a representative entity from the table for the
	 * given class if one exists.
	 * 
	 * @param clazz
	 *            the Class for which an entity will be fetched
	 * @return a representative entity (generally the first) from the table for
	 *         the class, or null if none exists
	 * @throws DaoException
	 */
	public Object getRepresentativeEntityByClass(Class<?> clazz) throws DaoException {
		Object value = null;
		try {
			List values = getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createCriteria(clazz).list();
			if (!values.isEmpty()) {
				value = values.get(0);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Exception finding an entity for the given class: " + clazz, e);
		}
		return value;
	}

	/**
	 * Generic method to return a persistent instance of a given key
	 * 
	 * @param key
	 *            the entity's key
	 * @return a populated object (or null if id doesn't exist)
	 * @throws DaoException
	 *             Thrown if failed to query an object
	 */
	public Object getByKey(EntityKey key) throws DaoException {

		Object value = null;
		try {
			value = getHibernateTemplate().get(key.getClass(), key.getId());
		} catch (DataAccessException e) {
			throw new DaoException("exception loading", e);
		}
		return value;
	}

	public Query getNamedQuery(String queryName) throws DaoException {
		Query q;
		try {
			q = getHibernateTemplate().getSessionFactory().getCurrentSession().getNamedQuery(queryName);
		} catch (HibernateException e) {
			throw new DaoException("Failed to get getNamedQuery ", e);
		}
		return q;
	}
	
	/**
	 * Saves an object to a database using Hibernate technology
	 */
	public void saveObject(Object obj) throws DaoException {
		try {
			getHibernateTemplate().saveOrUpdate(obj);
		} catch (DataAccessException e) {
			throw new DaoException("Failed to persist an entity " + obj, e);
		}
	}

	/**
	 * Saves an object to a database using Hibernate technology
	 */
	public void mergeObject(Object obj) throws DaoException {
		try {
			getHibernateTemplate().merge(obj);
		} catch (DataAccessException e) {
			throw new DaoException("Failed to persist an entity " + obj, e);
		}
	}
	/**
	 * Saves a detached object to a database using hibernate even if detached
	 * objects rows are missing from the database. Note that the method is setup
	 * to use the latest version of a row if the row already exists.
	 */
	public void replicateObject(Object obj) throws DaoException {
		try {
			getHibernateTemplate().replicate(obj, ReplicationMode.LATEST_VERSION);
		} catch (DataAccessException e) {
			throw new DaoException("Failed to persist an entity " + obj, e);
		}
	}

	/**
	 * Remove an object from a database using Hibernate technology
	 * 
	 * 
	 */
	public void removeObject(KeyedEntity entity) throws DaoException {
		Validate.notNull(entity, "entity must not be null");

		Object value = getByKey(entity.getClass(), entity.getId());
		if (value != null) {
			try {
				getHibernateTemplate().delete(value);
			} catch (DataAccessException e) {
				throw new DaoException("Failed to remove an entity with id: " + entity.getId(), e);
			}
		}
	}

	/**
	 * Remove an object from a database using Hibernate technology
	 * 
	 * @param clazz
	 *            class of the type to be deleted
	 * @param id
	 *            primary key of the entity to be deleted
	 * @throws DaoException
	 */
	public void removeObject(Class<?> clazz, Long id) throws DaoException {

		Object value = getByKey(clazz, id);
		if (value != null) {
			try {
				getHibernateTemplate().delete(value);
			} catch (DataAccessException e) {
				throw new DaoException("Failed to remove an entity with id: " + id, e);
			}
		}
	}
	
	/**
	 * Remove an object from a database using Hibernate technology
	 * 
	 * @param clazz
	 *            class of the type to be deleted
	 * @param id
	 *            primary key of the entity to be deleted
	 * @throws DaoException
	 */
	public void removeObject(Class<?> clazz, String code) throws DaoException {

		Object value = getByKey(clazz, code);
		if (value != null) {
			try {
				getHibernateTemplate().delete(value);
			} catch (DataAccessException e) {
				throw new DaoException("Failed to remove an entity with id: " + code, e);
			}
		}
	}

	/**
	 * Generic method to execute a simple query
	 * 
	 * @param query
	 *            A named query to execute. Implemented as the key for a named
	 *            query.
	 * @throws DaoException
	 *             Thrown if failed to remove an object
	 */
	public List findByNamedQuery(String query) throws DaoException {
		List list = null;
		if (query != null)
			try {
				list = getHibernateTemplate().findByNamedQuery(query);
			} catch (DataAccessException e) {
				throw new DaoException("Failed to get an entity by HSQL:" + query, e);
			}
		return list;
	}

	/**
	 * @param queryName
	 * @param paramName
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedParam(String queryName, String paramName, Object value)
			throws DaoException {
		try {
			Validate.notNull(queryName);
			Validate.notNull(paramName);
			return this.getHibernateTemplate().findByNamedParam(queryName, paramName, value);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named query: ").append(queryName)
					.append(" param Name: ").append(paramName).append(" value: ")
					.append(ToStringBuilder.reflectionToString(value));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * Execute a query for persistent instances, binding an array of values to
	 * "?" parameters in the query string
	 * 
	 * @param queryString
	 *            A query expression
	 * @param values
	 *            An array of parameter values
	 * @return A result entity
	 * @throws DaoException
	 *             in case of Hibernate errors
	 */
	public List find(String queryString, Object[] values) throws DaoException {
		try {
			Validate.notNull(queryString);
			return this.getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query: ").append(queryString);
			info.append(" values: ").append(ToStringBuilder.reflectionToString(values));
			throw new DaoException(info.toString(), e);
		}
	}
	
	public List find(String queryString) throws DataAccessException {
		try {
			Validate.notNull(queryString);
			return this.getHibernateTemplate().find(queryString);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query: ").append(queryString);
			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedParam(String queryString, String[] paramNames, Object[] values)
			throws DaoException {
		try {
			Validate.notNull(queryString, "A qyery string must not be null");
			return this.getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named param: ").append(queryString)
					.append(" param Names: ")
					.append(ToStringBuilder.reflectionToString(paramNames)).append(" values: ")
					.append(ToStringBuilder.reflectionToString(values));

			throw new DaoException(info.toString(), e);
		}
	}
	
	/**
	 * @param queryName
	 * @param value
	 * @param startIndex
	 *            Record number representing the first returned entry (1 is the
	 *            first record)
	 * @param itemsPerPage
	 *            The number of results to be returned per page
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Integer startIndex, Integer itemsPerPage)
			throws DaoException {
		Object[] values = new Object[] {};
		return findByNamedQuery(queryName, values, startIndex, itemsPerPage);
	}	

	/**
	 * @param queryName
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object value) throws DaoException {

		try {
			Validate.notNull(queryName, "A qyery string's name must not be null");
			return this.getHibernateTemplate().findByNamedQuery(queryName, value);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named param: ").append(queryName)
					.append(" values: ").append(ToStringBuilder.reflectionToString(value));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @param queryName
	 * @param value
	 * @param startIndex Record number representing the first returned entry (1 is the first record)
	 * @param itemsPerPage The number of results to be returned per page
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object value, Integer startIndex,
			Integer itemsPerPage) throws DaoException {
		Object[] values = new Object[] {value};
		return findByNamedQuery(queryName, values, startIndex, itemsPerPage);
	}

	/**
	 * @param queryName
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object[] values) throws DaoException {
		try {
			Validate.notNull(queryName, "A qyery string's name must not be null");
			return this.getHibernateTemplate().findByNamedQuery(queryName, values);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named param: ").append(queryName)
					.append(" valuess: ").append(ToStringBuilder.reflectionToString(values));

			throw new DaoException(info.toString(), e);
		}
	}
	
	/**
	 * @param queryName
	 * @param values
	 * @param startIndex Record number representing the first returned entry (1 is the first record)
	 * @param itemsPerPage The number of results to be returned per page
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object[] values, Integer startIndex,
			Integer itemsPerPage) throws DaoException {
		try {
			Validate.notNull(queryName, "A query string's name must not be null");
			HibernateCallback hibernateCallback = new GenericDaoHibernateCallback(queryName, values, startIndex, itemsPerPage);
			List list = getHibernateTemplate().executeFind(hibernateCallback);			
			return list;
			
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named param: ").append(queryName)
					.append(" valuess: ").append(ToStringBuilder.reflectionToString(values));

			throw new DaoException(info.toString(), e);
		}
	}	

	/**
	 * @param queryName
	 * @param paramName
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DaoException {
		try {
			Validate.notNull(queryName, "A qyery string's name must not be null");
			return getHibernateTemplate()
					.findByNamedQueryAndNamedParam(queryName, paramName, value);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named query: ").append(queryName)
					.append(" named param: ").append(paramName).append(" value: ")
					.append(ToStringBuilder.reflectionToString(value));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * Execute a named query for persistent instances, binding a number of
	 * values to ":" named parameters in the query string. A named query is
	 * defined in a Hibernate mapping file
	 * 
	 * @param queryName
	 *            the name of a Hibernate query in a mapping file
	 * @param paramNames
	 *            the names of the parameters
	 * @param values
	 *            the values of the parameters
	 * @return a List containing 0 or more persistent instances
	 * @throws DaoException
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
			throws DaoException {
		// try {
		Validate.notNull(queryName, "A qyery string's name must not be null");
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames,
				values);
		// } catch (RuntimeException e) {
		// StringBuffer info = new StringBuffer();
		// info.append("Failed to execute a query by named query: ").append(
		// queryName).append(" named params: ").append(
		// ToStringBuilder.reflectionToString(paramNames)).append(
		// " value: ").append(
		// ToStringBuilder.reflectionToString(values));
		//
		// throw new DaoException(info.toString(), e);
		// }
	}

	/**
	 * @param queryName
	 * @param valueBean
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQueryAndValueBean(String queryName, Object valueBean)
			throws DaoException {
		try {
			Validate.notNull(queryName, "A query string's name must not be null");
			Validate.notNull(valueBean, "A value bean must not be null");

			return this.getHibernateTemplate().findByNamedQueryAndValueBean(queryName, valueBean);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by named query: ").append(queryName)
					.append(" value bean: ").append(ToStringBuilder.reflectionToString(valueBean));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @param queryString
	 * @param valueBean
	 * @return
	 * @throws DaoException
	 */
	public List findByValueBean(String queryString, Object valueBean) throws DaoException {

		try {
			Validate.notNull(queryString, "A query string must not be null");
			Validate.notNull(valueBean, "A value bean must not be null");

			return this.getHibernateTemplate().findByValueBean(queryString, valueBean);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to execute a query by query string: ").append(queryString)
					.append(" value: ").append(ToStringBuilder.reflectionToString(valueBean));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @throws DaoException
	 */
	public void flush() throws DaoException {
		// try {
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		// TODO RW put back catch when we can roll back on DaoExceptions
		/*
		 * } catch (RuntimeException e) { StringBuffer info = new
		 * StringBuffer(); info.append("Failed to flush a session "); throw new
		 * DaoException(info.toString(), e); }
		 */
	}

	/**
	 * @param entityName
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Object load(String entityName, Serializable id) throws DaoException {
		try {
			Validate.notNull(entityName, "An entity's name must not be null");
			Validate.notNull(id, "An entity ID must not be null");

			return this.getHibernateTemplate().load(entityName, id);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to load from a session an entity named: ").append(entityName)
					.append(" with id: ").append(ToStringBuilder.reflectionToString(id));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @param entityClass
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> loadAll(Class<T> entityClass) throws DaoException {
		try {
			Validate.notNull(entityClass, "An entity's class must not be null");

			return this.getHibernateTemplate().loadAll(entityClass);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to load all instances of this class: ").append(
					ToStringBuilder.reflectionToString(entityClass));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * @param entityName
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public <T> T merge(String entityName, T entity) throws DaoException {
		try {
			Validate.notNull(entityName, "An entity's name must not be null");
			Validate.notNull(entity, "An entity must not be null");

			return this.getHibernateTemplate().merge(entityName, entity);
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Failed to merge an entity named: ").append(entityName)
					.append(" to the persistent one: ")
					.append(ToStringBuilder.reflectionToString(entity));

			throw new DaoException(info.toString(), e);
		}
	}

	public <T> T merge(T entity) throws DaoException {
		return getHibernateTemplate().merge(entity);
	}

	/**
	 * @param entity
	 * @throws DaoException
	 */
	public void refresh(Object entity) throws DaoException {
		try {
			if (this.getHibernateTemplate().contains(entity)) {
				this.getHibernateTemplate().refresh(entity);
			}
		} catch (RuntimeException e) {
			StringBuffer info = new StringBuffer();
			info.append("Refresh this entity with data from a database ").append(
					ToStringBuilder.reflectionToString(entity));

			throw new DaoException(info.toString(), e);
		}
	}

	/**
	 * Remove the given object from the org.hibernate.Session cache.
	 * 
	 * @param entity
	 *            the entity to remove
	 * @throws DaoException
	 */
	public void evict(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().evict(entity);
		} catch (DataAccessException e) {
			throw new DaoException("Failed to evict this entity: " + entity, e);
		}

	}

	/**
	 * Sets a component name to identify this component in a container
	 * 
	 * @param name
	 *            A component name
	 */
	public void setBeanName(String name) {
		this.name = name;
	}

	/**
	 * Returns a name identifying this component in a container
	 * 
	 * @return A bean's name.
	 */
	public String getBeanName() {
		return name;
	}

	/**
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.context = applicationContext;
	}

	/**
	 * @see org.springframework.dao.support.DaoSupport#initDao()
	 */
	protected void initDao() throws Exception {
		super.initDao();
	}

	class GenericDaoHibernateCallback implements HibernateCallback {				
		
		public GenericDaoHibernateCallback(String queryName, Object[] values, Integer startIndex,
			Integer itemsPerPage) {
			this.queryName = queryName;
			this.values = values;
			this.startIndex = startIndex;
			this.itemsPerPage = itemsPerPage;
		}
		
		private String queryName;
		private Object[] values;
		private Integer startIndex;
		private Integer itemsPerPage;
		
        public Object doInHibernate(Session session) {
        	Integer modifieditemsPerPage = (itemsPerPage != null && itemsPerPage > 0) ? itemsPerPage : null;		
			Integer modifiedStartIndex = (startIndex != null && startIndex > 0)	? startIndex : null;
        	
        	Query query = session.getNamedQuery(queryName);
			if (modifiedStartIndex != null) {
				query.setFirstResult(modifiedStartIndex.intValue());
			}
			if (modifieditemsPerPage != null) {
				query.setMaxResults(modifieditemsPerPage.intValue());
			}
			if (values != null) {
				int length = values.length;
				for (int i = 0; i < length; i++) {
					query.setParameter(i, values[i]);					
				}
			}
			
			List results = query.list();
			
			return results;
        	
        }
	}
	
}