package org.saibaba.fw.exception;


public class ServiceException extends BaseException {
   
   /**
	 * An instance of serialVersionUID
	 */
	private static final long serialVersionUID = -5419639461230634303L;

	/**
    * Creates new <code>ServiceException</code> without detail message.
    */
    public ServiceException() {
      super();
    }
   
    /**
     * Creates a new <code>ServiceException</code> with the specific cause
     */
     public ServiceException( Throwable cause ) {
       super(cause);
     }
     
	/**
	 * Constructs an <code>ServiceException</code> with the specified detail
	 * message.
	 * 
	 * @param msg the detail message.
    */
    public ServiceException(String msg) {
      super(msg);
    }
   
    /**
	 * Constructs an <code>ServiceException</code> with the specified detail
	 * message and a caused exception
	 * 
	 * @param msg the detail message.
	 * @param cause the caused exception
	 */
    public ServiceException(String msg, Throwable cause) {
    	super( msg, cause );
    }
}
