package org.saibaba.fw.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseEntity implements Serializable {


	private static final long serialVersionUID = -7220618633551209321L;

	
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
	
	/**
	 * Performs a deep copy of an object
	 * 
	 * @throws CloneNotSupportedException
	 */
	public Object deepClone() throws CloneNotSupportedException {
		try {
			// Serialize the object
			ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(this);
			byte[] objectData = baos.toByteArray();

			// Deserialize the object
			ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
			ObjectInputStream in = new ObjectInputStream(bais);
			return in.readObject();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
