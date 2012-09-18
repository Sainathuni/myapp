package org.saiweb.mandirs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cd_status")
public class Status {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3166924936864116379L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="status_id")	
	private int statusId;
	
	@Column(name="code")	
	private String code;
	
	@Column(name="description")	
	private String description;

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
