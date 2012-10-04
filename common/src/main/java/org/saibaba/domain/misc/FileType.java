package org.saibaba.domain.misc;

public enum FileType {

	PDF("PDF"), 
	IMAGE("Image"), 
	WORD("MS Word"),
	OTHER("Other");

	/**
	 * An instance of an enum description
	 */
	private String description;

	/**
	 * File Type enum
	 * 
	 * @param desc
	 *            an enum shape type description
	 */
	FileType(String desc) {
		this.description = desc;
	}

	/**
	 * An enum description
	 * 
	 * @return an enum description
	 */
	public String description() {
		return this.description;
	}

}
