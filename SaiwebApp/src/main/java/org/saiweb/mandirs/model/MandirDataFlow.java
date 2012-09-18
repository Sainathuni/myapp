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
import javax.persistence.Table;

@Entity
@Table(name="mandir_data_flow")
public class MandirDataFlow {


    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3166924936864116379L;

	@Id
	@Column(name="mandir_data_flow_id")
	@GeneratedValue(strategy = GenerationType.AUTO)    
	private int mandir_data_flow_id;
	
	@Column(name="mandir_id")
	private int mandir_id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mandir_team_contact_id", nullable = false)	
	private MandirTeamContact mandirTeamContact;
	
	
	
	public MandirTeamContact getMandirTeamContact() {
		return mandirTeamContact;
	}

	public void setMandirTeamContact(MandirTeamContact mandirTeamContact) {
		this.mandirTeamContact = mandirTeamContact;
	}

	/**
	 * @return the mandir_data_flow_id
	 */
	public int getMandir_data_flow_id() {
		return mandir_data_flow_id;
	}

	/**
	 * @param mandirDataFlowId the mandir_data_flow_id to set
	 */
	public void setMandir_data_flow_id(int mandirDataFlowId) {
		mandir_data_flow_id = mandirDataFlowId;
	}

	/**
	 * @return the mandir_id
	 */
	public int getMandir_id() {
		return mandir_id;
	}

	/**
	 * @param mandirId the mandir_id to set
	 */
	public void setMandir_id(int mandirId) {
		mandir_id = mandirId;
	}



	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param createdDate the created_date to set
	 */
	public void setCreated_date(Date createdDate) {
		created_date = createdDate;
	}

	@Column(name="created_date")		
	private Date created_date;
	
}
