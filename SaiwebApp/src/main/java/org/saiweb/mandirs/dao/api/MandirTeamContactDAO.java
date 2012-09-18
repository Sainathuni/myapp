package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.MandirTeamContact;

public interface MandirTeamContactDAO {

	MandirTeamContact getMandirTeamContact(int mandirTeamContactId);

	void addMandirTeamContact(MandirTeamContact mandirTeamContact);

	void updateMandirTeamContact(MandirTeamContact mandirTeamContact);

	void removeMandirTeamContact(int mandirTeamContactId);

	public List<MandirTeamContact> getAllMandirTeamContacts();

}
