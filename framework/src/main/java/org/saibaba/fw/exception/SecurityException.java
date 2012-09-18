package org.saibaba.fw.exception;


public class SecurityException extends BaseException {
	
	private static final long serialVersionUID = -5449682493012212226L;

	public SecurityException() {
        super();
	}
     
    /**
     * Creates a new <code>SecurityException</code> with the specific cause
     */
	public SecurityException( Throwable cause ) {
		super(cause);
	}
       
    /**
  	 * Constructs an <code>SecurityException</code> with the specified detail
  	 * message.
  	 * 
  	 * @param msg the detail message.
     */
     public SecurityException(String msg) {
        super(msg);
     }
     
     /**
  	  * Constructs an <code>SecurityException</code> with the specified detail
  	  * message and a caused exception
  	  * 
  	  * @param msg the detail message.
  	  * @param cause the caused exception
      */
      public SecurityException(String msg, Throwable cause) {
        super( msg, cause );
      }
}
