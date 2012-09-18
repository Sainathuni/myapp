
package org.saibaba.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * 
 *
 */
@SuppressWarnings("deprecation")
public abstract class AbstractCommonTest extends AbstractDependencyInjectionSpringContextTests {

	
private static final String COMMON_CTX_FILE = "classpath:common-context.xml";
private static final String TEST_BASE_CTX_FILE = "classpath:test-base.xml";
private static final String TEST_COMMON_CTX_FILE = "classpath:test-common.xml";
  
	
/**
   * An instance of a logger
   */
  protected Logger log = LoggerFactory.getLogger(getClass());
  
	/**
	 * An instance of AbstractSpringAwareTest
	 */
	protected AbstractCommonTest(String name) {
    super(name);
    this.setAutowireMode( AUTOWIRE_BY_NAME );
	}

  /**
   * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
   */
  protected String[] getConfigLocations() {
    return new String[] {COMMON_CTX_FILE,TEST_BASE_CTX_FILE, TEST_COMMON_CTX_FILE};
  }
}