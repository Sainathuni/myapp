package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Region;

public interface RegionDAO {

	
	public List<Region> getRegions();
	
	public String getRegionyById(int i);
}
