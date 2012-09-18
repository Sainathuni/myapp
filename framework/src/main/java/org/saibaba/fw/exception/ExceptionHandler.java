
package org.saibaba.fw.exception;

import java.io.Serializable;

public interface ExceptionHandler extends Serializable {

   
  /**
   * Handles an exception. A typical usage would be to 
   * log an exception to a log file and to wrap an exception
   * into another specific application exception to return
   * @param caller a caller that caused an exception
   * @param messageKey a message key for the exception message.
   * @param exception an exception to handle
   * @param exceptionClass, an instance of exception class to be created.
   * @param args a list of arguments to build an exception message
   * 
   * @return a handled exception
   */
  public <T> T handleException(final Object caller, final String messageKey, 
	      final Exception exception, Class<T> exceptionClass, final Object... args );
  
  /**
   * Handles an exception with the specific message. A typical 
   * usage would be to log an exception to a log file and to wrap 
   * an exception into another specific application exception.
   * @param caller a caller that caused an exception
   * @param exception an exception to handle
   * @param exceptionClass, an instance of exception class to be created.
   * @param mesage exception message
   * 
   * @return a handled exception
   */

  public <T> T handleException(final Object caller, final Exception exception, Class<T> exceptionClass,
			String message);
}