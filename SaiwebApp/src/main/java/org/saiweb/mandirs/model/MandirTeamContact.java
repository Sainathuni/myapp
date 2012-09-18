package org.saiweb.mandirs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mandir_data_flow")
public class MandirTeamContact {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3166924936864116379L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)    	
	@Column(name="mandir_team_contact_d")
	private int user_id;
	
	@Column(name="name")
	private String name;		
	
	@Column(name="email")
	private String email;	
	
	@Column(name="phonenumber")
	private String phonenumber;		
	
	@Column(name="place")
	private String place;
	
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	
	/**
	 * @param userId the user_id to set
	 */
	public void setUser_id(int userId) {
		user_id = userId;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
}
