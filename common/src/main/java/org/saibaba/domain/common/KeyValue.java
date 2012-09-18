package org.saibaba.domain.common;

import org.saibaba.fw.domain.BaseEntity;

public class KeyValue extends BaseEntity {

	private static final long serialVersionUID = -2755547537004728331L;
	private String field;
	private String key;
	private String value;	
	private Object[] args;
	

	public KeyValue() {}
	
	public KeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyValue(String field, String key, String value) {
		this.field = field;
		this.key = key;
		this.value = value;
	}

	public KeyValue(String field, String key, String value, Object [] args) {
		this(field, key, value );
		this.args = args;
	}
	
	
	
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
