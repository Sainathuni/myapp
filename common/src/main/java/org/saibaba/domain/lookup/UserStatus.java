package org.saibaba.domain.lookup;

import org.saibaba.fw.domain.Lookup;


public class UserStatus extends Lookup {

	public static String NEW 				= "NEW";
	public static String PENDING 			= "PENDING";
	public static String APPROVED 			= "APPROVED";
	public static String DELETED 			= "DELETED";
	public static String REJECTED 			= "REJECTED";
	public static String LOCKED 			= "LOCKED";
	
	private static final long serialVersionUID = -4058965245640627413L;
	
	public UserStatus() {}
	public UserStatus(String code) {
		this.setCode(code);
	}
	
	public UserStatus(String code, String name) {
		this.setCode(code);
		this.setName(name);
	}

}
