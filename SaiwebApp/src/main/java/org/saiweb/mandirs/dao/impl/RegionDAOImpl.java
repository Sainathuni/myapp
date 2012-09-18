package org.saiweb.mandirs.dao.impl;

import java.util.List;


import org.saiweb.mandirs.dao.api.RegionDAO;
import org.saiweb.mandirs.model.Country;
import org.saiweb.mandirs.model.Mandir;
import org.saiweb.mandirs.model.Region;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("regionDAO")
public class RegionDAOImpl extends HibernateDaoSupport implements RegionDAO{

	public List<Region> getRegions() {
        return getHibernateTemplate().loadAll(Region.class);
	}

	public String getRegionyById(int i) {
		// TODO Auto-generated method stub
		return null;
	}



}
