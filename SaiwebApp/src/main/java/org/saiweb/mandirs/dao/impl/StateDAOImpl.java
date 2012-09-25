package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.StateDAO;
import org.saiweb.mandirs.model.State;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("stateDAO")
public class StateDAOImpl  extends HibernateDaoSupport implements StateDAO {

	public List<State> getStatesByCountryId(int countryId) {
		Criteria criteria = getSession().createCriteria(State.class);
	    Criterion eqCode = Restrictions.eq("countryId", countryId);
	    criteria.add(eqCode);        	
        List<State> states = (List<State>) criteria.list();
        System.out.println("stateslist" + states.size());
		return states;
	}

	public State getStateById(int stateId) {
			Criteria criteria = getSession().createCriteria(State.class);
			Criterion eqCode = Restrictions.eq("stateId", stateId);
			criteria.add(eqCode);
			return (State) criteria.uniqueResult();
	}

	public List<State> getAllStates() {
	    return getHibernateTemplate().loadAll(State.class);
	}

}
