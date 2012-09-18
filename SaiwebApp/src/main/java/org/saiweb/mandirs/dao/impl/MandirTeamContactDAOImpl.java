package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.MandirTeamContactDAO;
import org.saiweb.mandirs.model.Mandir;
import org.saiweb.mandirs.model.MandirTeamContact;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MandirTeamContactDAOImpl extends HibernateDaoSupport implements MandirTeamContactDAO{

	public MandirTeamContact getMandirTeamContact(int mandirTeamContactId) {
		Criteria criteria = getSession().createCriteria(MandirTeamContact.class);
	    Criterion eqCode = Restrictions.eq("mandir_team_contact_d", mandirTeamContactId);
	    criteria.add(eqCode);        	
        return (MandirTeamContact) criteria.uniqueResult();
	}

	public void addMandirTeamContact(MandirTeamContact mandirTeamContact) {
		getHibernateTemplate().save(mandirTeamContact);		

		
	}

	public void updateMandirTeamContact(MandirTeamContact mandirTeamContact) {
		getHibernateTemplate().saveOrUpdate(mandirTeamContact);		

		
	}

	public void removeMandirTeamContact(int mandirTeamContactId) {
		getHibernateTemplate().delete(mandirTeamContactId);		
		
	}

	public List<MandirTeamContact> getAllMandirTeamContacts() {

        return getHibernateTemplate().loadAll(MandirTeamContact.class);
	}

}
