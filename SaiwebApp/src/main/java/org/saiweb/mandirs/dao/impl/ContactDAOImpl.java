package org.saiweb.mandirs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.saiweb.mandirs.dao.api.ContactDAO;
import org.saiweb.mandirs.model.Contact;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("contactDAO")
public class ContactDAOImpl extends HibernateDaoSupport implements ContactDAO {

	public Contact getContactById(int contactId) {
		Criteria criteria = getSession().createCriteria(Contact.class);
		Criterion eqCode = Restrictions.eq("contactId", contactId);
		criteria.add(eqCode);
		return (Contact) criteria.uniqueResult();
	}

	public void addContact(Contact contact) {
		getHibernateTemplate().save(contact);
	}

	public void updateContact(Contact contact) {
		getHibernateTemplate().update(contact);
	}

	public void removeContact(Contact contact) {
		getHibernateTemplate().delete(contact);
	}

	public List<Contact> getAllContacts() {
		return getHibernateTemplate().loadAll(Contact.class);

	}

}
