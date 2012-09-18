package org.saibaba.fw.domain;


public class Entity extends BaseEntity {

	private static final long serialVersionUID = 4696112357407696912L;
	
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
