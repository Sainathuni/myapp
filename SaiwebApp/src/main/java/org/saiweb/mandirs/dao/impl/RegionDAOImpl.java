package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.RegionDAO;
import org.saiweb.mandirs.model.Region;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("regionDAO")
public class RegionDAOImpl extends HibernateDaoSupport implements RegionDAO{

	public List<Region> getRegions() {
        return getHibernateTemplate().loadAll(Region.class);
	}

	public Region getRegionyById(int regionId) {
		Criteria criteria = getSession().createCriteria(Region.class);
		Criterion eqCode = Restrictions.eq("regionId", regionId);
		criteria.add(eqCode);
		return (Region) criteria.uniqueResult();
	}



}
