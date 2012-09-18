package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Mandir;

public interface MandirDAO {

	/**
	 * Gets the Mandir information by mandirId.
	 * 
	 * @param mandirId
	 *            - the mandir id
	 * @return Mandir
	 */
	public Mandir getById(int mandirId);

	/**
	 * Gets the list of Mandirs by location.
	 * 
	 * @param location
	 *            - thelocation
	 * @return List of Mandirs
	 */
	public List<Mandir> getMandirsByLocation(String location);

	/**
	 * Gets the list of all Mandirs.
	 * 
	 * @return List of Mandirs
	 */
	public List<Mandir> getAllMandirs();

	/**
	 * Gets the list of Mandirs by StatusId.
	 * 
	 * @param mandirId
	 *            - the mandir id
	 * @return List of Mandirs
	 */
	public List<Mandir> getMandirsByStatusId(int status);

	/**
	 * Inserts Mandir information into database.
	 * 
	 * @param mandir
	 *            - the mandir information to be saved in database
	 */
	public void save(Mandir mandir);

	/**
	 * Updates Mandir information into database.
	 * 
	 * @param mandir
	 *            - the mandir information to be updated in database
	 */
	public void update(Mandir mandir);

	/**
	 * Delete the Mandir information from database.
	 * 
	 * @param mandir
	 *            - the mandir information to be deleted from database
	 */
	public void delete(Mandir mandir);

	public List<Mandir> getMandirsByLocation(int regionId, int statusId);
}
