
package org.saibaba.fw.utils;

public class ReflectionException extends Exception {

	private static final long serialVersionUID = 7368842797985369893L;

	/**
   * An instance of ReflectionException
   */
  public ReflectionException() {
    super();
  }

  /**
   * An instance of ReflectionException with the 
   * specific exception message
   * @param message An exception message
   */
  public ReflectionException(final String message) {
    super(message);
  }

  /**
   * An instance of ReflectionException with the specific
   * exception message, and the exception cause.
   * @param message An exception message
   * @param cause An exception cause
   */
  public ReflectionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * An instance of ReflectionException with the specific exception cause
   * @param cause An exception cause
   */
  public ReflectionException(final Throwable cause) {
    super(cause);
  }
}