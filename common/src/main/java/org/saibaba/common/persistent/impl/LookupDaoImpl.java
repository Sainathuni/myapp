package org.saibaba.common.persistent.impl;

import java.util.List;

import org.saibaba.common.persistent.DaoException;
import org.saibaba.common.persistent.LookupDao;
import org.saibaba.fw.domain.Lookup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class LookupDaoImpl extends GenericDaoImpl implements LookupDao {

	private static final long serialVersionUID = 5008499086991399575L;

	private static final String GET_ALL = "_GetAll";
	private static final String GET_BY_LOOKUP_CODE = "_GetByCode";
	private static final String GET_BY_LOOKUP_NAME = "_GetByName";

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Lookup> findAll(Class<Lookup> lookup) throws DaoException {
		try {
			String findAll = lookup.getSimpleName() + GET_ALL;
			@SuppressWarnings("unchecked")
			List<Lookup> lookups = this.findByNamedQuery(findAll);
			return (List<Lookup>) (lookups.size() >= 1 ? lookups : null);
		} catch (Exception ex) {
			throw new DaoException("findAll() lookups", ex);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Lookup findByCode(Class<Lookup> lookup, String code) throws DaoException {
		try {
			String findByCodeQuery = lookup.getSimpleName() + GET_BY_LOOKUP_CODE;
			@SuppressWarnings("unchecked")
			List<Lookup> lookups = this.findByNamedQuery(findByCodeQuery, code);
			return (lookups.size() > 0 ? lookups.get(0) : null);
		} catch (Exception ex) {
			throw new DaoException("findByCode() lookups  = " + code, ex);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Lookup findByName(Class<Lookup> lookup, String name) throws DaoException {
		try {
			String findByNameQuery = lookup.getSimpleName() + GET_BY_LOOKUP_NAME;
			@SuppressWarnings("unchecked")
			List<Lookup> lookups = this.findByNamedQuery(findByNameQuery, name);
			return (lookups.size() > 0 ? lookups.get(0) : null);
		} catch (Exception ex) {
			throw new DaoException("findByName() reference  = " + name, ex);
		}
	}
}