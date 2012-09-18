package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Country;

public interface CountryDAO {

	
	public List<Country> getCountries();
	
	public String getCountryById(int i);
}
