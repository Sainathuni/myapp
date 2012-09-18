package org.saibaba.common.persistent;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException {
    
   /**
	 * An instance of serialVersionUID
	 */
	private static final long serialVersionUID = -2049173170261113264L;

	
   
   /**
    * Constructs an <code>ServiceException</code> with the specified detail message.
    * @param msg the detail message.
    */
    public DaoException(String msg) {
      super(msg);
   }
   
   /**
    * Constructs an <code>ServiceException</code> with the specified detail message
    * and a caused exception
    * @param msg the detail message.
    * @param cause the caused exception
    */
    public DaoException(String msg, Throwable cause) {
      super( msg, cause );
   }
}