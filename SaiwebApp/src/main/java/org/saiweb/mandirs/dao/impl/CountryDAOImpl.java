package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.saiweb.mandirs.dao.api.CountryDAO;
import org.saiweb.mandirs.model.Country;
import org.saiweb.mandirs.model.Mandir;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("countryDAO")
public class CountryDAOImpl extends HibernateDaoSupport implements CountryDAO{

	public List<Country> getCountries() {
        return getHibernateTemplate().loadAll(Country.class);
	}

	public String getCountryById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
