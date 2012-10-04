package org.saibaba.domain.misc;

import org.saibaba.fw.domain.KeyedEntity;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class FileInfo extends KeyedEntity  {

	private static final long serialVersionUID = -7367662128828390631L;

	@NotBlank
    @Length(min = 1, max = 50)
	private String fileName;
	
	@NotBlank
    @Length(min = 1, max = 50)
	private String url;	
	
	@NotBlank
	private FileType fileType;
	
	@NotBlank
	private Long mandirId;
	
	private String description;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Long getMandirId() {
		return mandirId;
	}

	public void setMandirId(Long mandirId) {
		this.mandirId = mandirId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
