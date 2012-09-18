package org.saibaba.domain.lookup;

import java.util.Set;

import org.saibaba.fw.domain.Lookup;

public class Role extends Lookup {


	private static final long serialVersionUID = -2154881365837808767L;
	public static final String TCO 				= "TCO";
	public static final String USER 			= "USER";
	public static final String SA 				= "SA";

	private Set<Privilege> privileges;

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
}
