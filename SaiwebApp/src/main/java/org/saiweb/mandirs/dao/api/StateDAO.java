package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Country;
import org.saiweb.mandirs.model.State;

public interface StateDAO {
	public List<State> getAllStates();	
	public List<State> getStatesByCountryId(int i);
	public State getStateById(int stateId);
}
