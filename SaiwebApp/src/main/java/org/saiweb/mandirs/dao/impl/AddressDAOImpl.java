package org.saiweb.mandirs.dao.impl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.AddressDAO;
import org.saiweb.mandirs.model.Address;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("addressDAO")
public class AddressDAOImpl extends HibernateDaoSupport implements AddressDAO{

	public Address getAddressById(int addressId) {
		Criteria criteria = getSession().createCriteria(Address.class);
	    Criterion eqCode = Restrictions.eq("address_id", addressId);
	    criteria.add(eqCode);        	
        return (Address) criteria.uniqueResult();
	}

	public void addAddress(Address address) {
		getHibernateTemplate().save(address);		
	}

	public void updateAddress(Address address) {
		getHibernateTemplate().update(address);		
		
	}

	public void removeAddress(Address address) {
		getHibernateTemplate().delete(address);		
		
	} 

}
