package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Contact;

public interface ContactDAO {

	
	Contact getContactById(int contactId);
	
	void addContact(Contact contact);
	
	void updateContact(Contact contact);
	
	void removeContact(Contact contact);
	

	/**
	 * Gets the list of all Mandirs.
	 * 
	 * @return List of Mandirs
	 */
	public List<Contact> getAllContacts();
	
}
