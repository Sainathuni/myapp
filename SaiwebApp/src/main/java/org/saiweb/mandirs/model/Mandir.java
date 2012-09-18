package org.saiweb.mandirs.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mandir")
public class Mandir {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3166924936864116379L;

	/**
	 * Represents Mandir Id.
	 */
	@Id
	@Column(name = "mandir_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mandirId;

	/**
	 * Represents Mandir Code.
	 */
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Represents Mandir Name.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * Represents Mandir description.
	 */
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "info_gathered_on")
	private Date infoGatheredOn;

	/**
	 * Represents Mandir website.
	 */
	@Column(name = "website", nullable = true)
	private String website;

	/**
	 * Represents Mandir meta_data.
	 */
	@Column(name = "meta_data_id", nullable = false)
	private int metaData;

	/**
	 * Represents Mandir status_id.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private Status status;

	/**
	 * Represents Mandir address_id.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	private Address mandirAddress;

	/**
	 * @return the mandirAddress
	 */
	public Address getMandirAddress() {
		return mandirAddress;
	}

	/**
	 * @param mandirAddress
	 *            the mandirAddress to set
	 */
	public void setMandirAddress(Address mandirAddress) {
		this.mandirAddress = mandirAddress;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mandirId
	 */
	public int getMandirId() {
		return mandirId;
	}

	/**
	 * @param mandirId the mandirId to set
	 */
	public void setMandirId(int mandirId) {
		this.mandirId = mandirId;
	}

	/**
	 * @return the infoGatheredOn
	 */
	public Date getInfoGatheredOn() {
		return infoGatheredOn;
	}

	/**
	 * @param infoGatheredOn the infoGatheredOn to set
	 */
	public void setInfoGatheredOn(Date infoGatheredOn) {
		this.infoGatheredOn = infoGatheredOn;
	}

	/**
	 * @return the metaData
	 */
	public int getMetaData() {
		return metaData;
	}

	/**
	 * @param metaData the metaData to set
	 */
	public void setMetaData(int metaData) {
		this.metaData = metaData;
	}
	

}
