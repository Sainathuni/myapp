package org.saiweb.mandirs.dao.api;

import java.util.List;

import org.saiweb.mandirs.model.Status;

public interface StatusDAO {

	/**
	 * Gets the Status information by statusId.
	 * 
	 * @param statusId
	 *            - the status id
	 * @return Mandir
	 */
	public Status getStatusById(int statusId);
	
	/**
	 * Gets all Status.
	 * 
	 * @return Status list
	 */
	public List<Status> getAllStatus();

	/**
	 * Gets the Status information by code.
	 * 
	 * @param code
	 *            - the code
	 * @return Status
	 */
	public Status getStatusByCode(String code);
	
}

