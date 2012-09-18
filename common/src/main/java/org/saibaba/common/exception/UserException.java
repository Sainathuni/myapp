package org.saibaba.common.exception;

import org.saibaba.fw.exception.BaseException;


public class UserException extends BaseException {
   
   /**
	 * An instance of serialVersionUID
	 */
	private static final long serialVersionUID = -5419639461230634303L;
	String messageCode;
	

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
    * Creates new <code>ServiceException</code> without detail message.
    */
    public UserException() {
      super();
    }
   
    /**
     * Creates a new <code>ServiceException</code> with the specific cause
     */
     public UserException( Throwable cause ) {
       super(cause);
     }
     
	/**
	 * Constructs an <code>ServiceException</code> with the specified detail
	 * message.
	 * 
	 * @param msg the detail message.
    */
    public UserException(String msgCode, String message) {
      super(message);
      this.messageCode = msgCode;
    }
   
    /**
	 * Constructs an <code>ServiceException</code> with the specified detail
	 * message and a caused exception
	 * 
	 * @param msg the detail message.
	 * @param cause the caused exception
	 */
    public UserException(String msg, Throwable cause) {
    	super( msg, cause );
    }
}
