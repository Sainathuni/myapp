package org.saibaba.fw.exception;

import java.io.Serializable;


public interface ExceptionHandlerAware extends Serializable {

  /**
   * Sets an instance of an exception handler
   * 
   * @param handler An exception handler instance
   */
  public void setExceptionHandler(ExceptionHandler handler);
}
