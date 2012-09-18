
package org.saibaba.common.service;

import java.util.List;

import org.saibaba.common.AbstractCommonTest;
import org.saibaba.domain.lookup.Privilege;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.fw.domain.Lookup;

/**
 * 
 */
public class LookupServiceTest extends AbstractCommonTest {

	private LookupService lookupService;

	
	public void setLookupService(LookupService lookupService) {
		this.lookupService = lookupService;
	}

	/**
	 * Instantiates a test case with the specific name
	 * 
	 * @param name
	 *            a test case name
	 */
	public LookupServiceTest(String name) {
		super(name);
	}

	/**
	 * 
	 */
	public void testRole() {
		try {
			
			List<Lookup> list = lookupService.findAll(Role.class);
			log.info("Roles:{}", list);
		} catch (Throwable th) {
			log.error("Failed to get Roles", th);
		}
	}
	
	public void testPrivilege() {
		try {
			
			List<Lookup> list = lookupService.findAll(Privilege.class);
			log.info("Privileges:{}", list);
		} catch (Throwable th) {
			log.error("Failed to get Privilege", th);
		}
	}
	
	public void testUserStatus() {
		try {
			
			List<Lookup> list = lookupService.findAll(UserStatus.class);
			log.info("UserStatus:{}", list);
		} catch (Throwable th) {
			log.error("Failed to get UserStatus", th);
		}
	}
		
}