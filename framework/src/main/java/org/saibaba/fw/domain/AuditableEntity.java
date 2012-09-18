package org.saibaba.fw.domain;

import java.sql.Timestamp;

public class AuditableEntity extends Entity {

	private static final long serialVersionUID = 8117151817393806943L;
	
	private Long createdBy;
	private Timestamp createdDate;
	
	private Long modifiedBy;
	private Timestamp modifiedDate;

	
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
