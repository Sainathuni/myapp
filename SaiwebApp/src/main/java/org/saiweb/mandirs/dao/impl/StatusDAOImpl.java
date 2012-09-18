package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.StatusDAO;
import org.saiweb.mandirs.model.Status;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("statusDAO")
public class StatusDAOImpl  extends HibernateDaoSupport implements StatusDAO {

	public Status getStatusById(int statusId) {
		Criteria criteria = getSession().createCriteria(Status.class);
	    Criterion eqCode = Restrictions.eq("statusId", statusId);
	    criteria.add(eqCode);        	
        return (Status) criteria.uniqueResult();
	}

	public List<Status> getAllStatus() {
        return getHibernateTemplate().loadAll(Status.class);

	}

	public Status getStatusByCode(String code) {
		Criteria criteria = getSession().createCriteria(Status.class);
	    Criterion eqCode = Restrictions.eq("code", code);
	    criteria.add(eqCode);        	
        return (Status) criteria.uniqueResult();
	}

}
