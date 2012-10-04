
package org.saibaba.common.service;

import java.util.List;

import org.saibaba.common.AbstractCommonTest;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.lookup.Privilege;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.misc.FileInfo;
import org.saibaba.domain.misc.FileType;
import org.saibaba.fw.domain.Lookup;

/**
 * 
 */
public class MiscServiceTest extends AbstractCommonTest {

	private MiscService miscService;

	

	public void setMiscService(MiscService miscService) {
		this.miscService = miscService;
	}

	/**
	 * Instantiates a test case with the specific name
	 * 
	 * @param name
	 *            a test case name
	 */
	public MiscServiceTest(String name) {
		super(name);
	}

	/**
	 * 
	 */
	public void testAddFileInfo() {
		try {
			FileInfo info = new FileInfo();
			info.setFileName("baba_picture.jpeg");
			info.setFileType(FileType.IMAGE);
			info.setMandirId(new Long(1));
			info.setUrl("/hyd001/baba_picture.jpeg");
			info.setDescription("Baba Picture");
			InvocationResult result  = miscService.addFileInfo(info);
			log.info("Invocation Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to create file ino", th);
		}
	}
	
	public void testAddFileInfo1() {
		try {
			FileInfo info = new FileInfo();
			info.setFileName("temple_details.doc");
			info.setFileType(FileType.WORD);
			info.setMandirId(new Long(1));
			info.setUrl("/hyd001/temple_details.doc");
			info.setDescription("Baba Temple Details");
			InvocationResult result  = miscService.addFileInfo(info);
			log.info("Invocation Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to create file ino", th);
		}
	}
	public void testModifyFileInfo() {
		try {
			FileInfo info = miscService.getFileInfoById(new Long(1));
			info.setFileName("baba_picture1.jpeg");
			info.setFileType(FileType.IMAGE);
			info.setMandirId(new Long(1));
			info.setUrl("/hyd001/baba_picture1.jpeg");
			info.setDescription("Baba Picture1");
			InvocationResult result  = miscService.updateFileInfo(info);
			log.info("Invocation Result:{}", result);
		} catch (Throwable th) {
			log.error("Failed to update file ino", th);
		}
	}
		
	public void testgetFileInfoByMandirId() {
		try {
			List<FileInfo>  infos= miscService.getFileInfoByMandirId(new Long(1));
			
			log.info("File Infos:{}", infos);
		} catch (Throwable th) {
			log.error("Failed to create file ino", th);
		}
	}
}