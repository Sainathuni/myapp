package org.saibaba.fw.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Lookup implements Serializable {

	private static final long serialVersionUID = 612852663552062345L;
	
	
	private String code;
	private String name;
	private String codeName;
	
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCodeName() {
		return code + " - " + name;
	}

	public void setCodeName(String codeName) {
		//this.codeName = codeName;
	}

	@Override public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	
	/**
	 * Performs a shallow copy of an object
	 * 
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
