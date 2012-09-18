package org.saiweb.mandirs.dao.api;

import org.saiweb.mandirs.model.Address;

public interface AddressDAO {

	
	Address getAddressById(int addressId);
	
	void addAddress(Address address);
	
	void updateAddress(Address address);
	
	void removeAddress(Address address);
	
}
