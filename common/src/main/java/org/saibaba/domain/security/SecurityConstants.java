package org.saibaba.domain.security;

import org.saibaba.domain.lookup.Privilege;
import org.saibaba.domain.lookup.Role;

public interface SecurityConstants {
	
	public static final String ROLE_SUFFIX = "ROLE_";
	
	public static final String ROLE_USER 	= ROLE_SUFFIX + Role.USER;
	public static final String ROLE_TCO 			= ROLE_SUFFIX + Role.TCO;
	public static final String ROLE_SA 				= ROLE_SUFFIX + Role.SA;
	
	public static final String ROLE_APPROVE_USER 	= ROLE_SUFFIX + Privilege.APPROVE_USER;
	public static final String TEMPLE_ENTRY 		= ROLE_SUFFIX + Privilege.TEMPLE_ENTRY;
	public static final String TEMPLE_APPROVER 		= ROLE_SUFFIX + Privilege.TEMPLE_APPROVER;
	public static final String ROLE_DELETE_USER 	= ROLE_SUFFIX + Privilege.DELETE_USER;
}
