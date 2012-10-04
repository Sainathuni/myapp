package org.saibaba.domain.common;

import org.saibaba.fw.domain.BaseEntity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 */
public class UploadItem extends BaseEntity {

	private static final long serialVersionUID = 6658408449656474328L;
	private String name;
	private String description;
	private Long mandirId;
	private String mandirCode;
	private CommonsMultipartFile fileData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getMandirCode() {
		return mandirCode;
	}

	public void setMandirCode(String mandirCode) {
		this.mandirCode = mandirCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMandirId() {
		return mandirId;
	}

	public void setMandirId(Long mandirId) {
		this.mandirId = mandirId;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}
