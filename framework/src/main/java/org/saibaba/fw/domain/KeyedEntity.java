package org.saibaba.fw.domain;

public class KeyedEntity extends AuditableEntity {

	private static final long serialVersionUID = 316430732043861993L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
