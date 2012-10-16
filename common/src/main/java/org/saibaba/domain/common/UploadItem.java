package org.saibaba.domain.common;

import org.saibaba.fw.domain.BaseEntity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

/**
 */
public class UploadItem extends BaseEntity {

	private static final long serialVersionUID = 6658408449656474328L;
	
	@NotBlank
    @Length(min = 1, max = 50)
	private String name;
	
	private String description;	
	
	@NotNull
	private Long mandirId;
	
	@NotBlank
    @Length(min = 1, max = 50)
	private String mandirCode;
	
	@NotNull
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
