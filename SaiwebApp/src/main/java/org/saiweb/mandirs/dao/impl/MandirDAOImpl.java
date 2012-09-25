package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.MandirDAO;
import org.saiweb.mandirs.model.Mandir;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("mandirDAO")
public class MandirDAOImpl extends HibernateDaoSupport implements MandirDAO {

	public void save(Mandir mandir) {
		getHibernateTemplate().saveOrUpdate(mandir);
	}

	public void update(Mandir mandir) {
		getHibernateTemplate().saveOrUpdate(mandir);
	}

	public void delete(Mandir mandir) {
		getHibernateTemplate().delete(mandir);
	}

	public List<Mandir> getAllMandirs() {
		return getHibernateTemplate().loadAll(Mandir.class);
	}

	public Mandir getById(int mandirId) {
		Criteria criteria = getSession().createCriteria(Mandir.class);
		Criterion eqCode = Restrictions.eq("mandirId", mandirId);
		criteria.add(eqCode);
		return (Mandir) criteria.uniqueResult();
	}

	public List<Mandir> getMandirsByLocation(String location) {
		Criteria criteria = getSession().createCriteria(Mandir.class);
		Criterion eqLocation = Restrictions.eq("mandirAddress.city", location);
		criteria.add(eqLocation);
		List<Mandir> list = (List<Mandir>) criteria.list();
		return list;
	}

	public List<Mandir> getMandirsByStatusId(int status) {
		Criteria criteria = getSession().createCriteria(Mandir.class);
		Criterion eqLocation = Restrictions.eq("status.statusId", status);
		criteria.add(eqLocation);
		List<Mandir> list = (List<Mandir>) criteria.list();
		return list;
	}

	public List<Mandir> getMandirsByLocation(int regionId, int statusId) {
		Criteria criteria = getSession().createCriteria(Mandir.class);
		criteria.add(Restrictions.eq("status", statusId));
		criteria.add(Restrictions.eq("mandirAddress.region.regionId", regionId));
		List<Mandir> list = (List<Mandir>) criteria.list();
		return list;
	}

}
