package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.State;

public interface StateDAO {
	
	public List<State> getStatesByCountryId(int i);
}
