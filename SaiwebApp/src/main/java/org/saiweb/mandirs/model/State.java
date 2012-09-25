package org.saiweb.mandirs.model;

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
@Table(name="states")
public class State {

    private static final long serialVersionUID = 3166924936864116379L;

	@Id
	@Column(name="state_id")	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stateId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@Column(name="name", nullable = false)	
	private String name;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	
	
}
