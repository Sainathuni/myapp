package org.saibaba.fw.exception;


public class BaseException extends Exception {

	/**
     * Creates new <code>BaseException</code> without detail message.
     */
	public BaseException() {
      super();
	}
   
    /**
     * Creates a new <code>BaseException</code> with the specific cause
     */
     public BaseException( Throwable cause ) {
       super(cause);
     }
     
     /**
	  * Constructs an <code>BaseException</code> with the specified detail
	  * message.
	  * 
	  * @param msg the detail message.
      */
     public BaseException(String msg) {
    	 super(msg);
     }
   
     /**
	  * Constructs an <code>ServiceException</code> with the specified detail
	  * message and a caused exception
	  * 
	  * @param msg the detail message.
	  * @param cause the caused exception
	  */
     public BaseException(String msg, Throwable cause) {
    	 super( msg, cause );
     }
	    
     public String getSimpleMessage() {
    	 return super.getMessage();
     }   
    
     @Override
     public String getMessage() {
    	 String message = super.getMessage();
    	// message = message != null ? message + getDetailedMessage() : getDetailedMessage();
    	 return message;
     }
    
     public String getDetailedMessage() {
    	 StackTraceElement[] trace = getStackTrace();
    	 StringBuilder stackTrace = new StringBuilder();
    	 if(stackTrace != null) {
    		 for(int i=0; i<trace.length; i++) {
    			 if(trace[i] != null) {
    				stackTrace.append(trace[i].toString());
    				stackTrace.append("\n");
    			}
    		}
    	}
    	
    	Throwable ourCause = getCause(); 
    	while(ourCause != null) {
    		if (ourCause != null) {
    			stackTrace.append(ourCause.getMessage() + "\n");
    			ourCause = ourCause.getCause();
    		}
    	}
    	return stackTrace.toString();
     }
}
