package org.saiweb.mandirs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State {

    private static final long serialVersionUID = 3166924936864116379L;

	@Id
	@Column(name="state_id")	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stateId;
	
	@Column(name="country_id", nullable = false)	
	private int countryId;
	
	@Column(name="name", nullable = false)	
	private String name;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
