package org.saibaba.fw.service.impl;

import org.apache.commons.lang.Validate;
import org.saibaba.fw.exception.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ApplicationObjectSupport;
import org.saibaba.fw.exception.ServiceException;


public class AbstractServiceImpl extends ApplicationObjectSupport
        implements BeanNameAware, InitializingBean {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

    
    /** A unique name identifying this component its life cycle */
    private String name = null;

    private ExceptionHandler exceptionHandler = null;
	private MessageSource messageSource = null;
	
    /** Creates a new instance of AbstractComponent */
    protected AbstractServiceImpl () {
        super ();
    }   

    public void setBeanName (String name) {
        this.name = name;
    }

    public String getBeanName () {
        return name;
    }

    /**
     * Returns a generic component managed by an application context
     *
     * @param name - A component name
     * @return A generic component managed by an application context
     */
    public Object getComponent (String name) throws ServiceException {

        Object component = null;
        try {
            ApplicationContext context = getApplicationContext ();
            component = context.getBean (name);
        } catch (BeansException e) {
            // Failed to get a component
            throw new ServiceException ("Failed to get a component by name " + name, e);
        }
        return component;
    }

    /**
     * Returns a specific component managed by an application context
     *
     * @param name - A component name
     * @param type - A component name's type
     * @return A specific component managed by an application context
     */
    public <T> T getComponent (String name, Class<T> type) throws ServiceException {
      
        try {
            ApplicationContext context = getApplicationContext ();
           return context.getBean (name, type);
        } catch (BeansException e) {
            // Failed to get a component
            throw new ServiceException ("Failed to get a component by name " + name + " and type " + type, e);
        }       
    }

    /**
     * Handles an exception. A typical usage would be to 
     * log an exception to a log file and to wrap an exception
     * into another specific application exception to return
     * @param messageKey an exception message key in property file.
     * @param exception an exception to handle
     * @param exceptionClass, an instance of exception class to be created.
     * @param args a list of arguments to build an exception message
     * 
     * @return a handled exception
     */
    protected <T> T handleException(final String messageKey, final Exception exception, Class<T> exceptionClass,
			final Object... args) {
		return exceptionHandler.handleException(this, messageKey, exception, exceptionClass, args);
	}

    /**
     * Handles an exception. A typical usage would be to 
     * log an exception to a log file and to wrap an exception
     * into another specific application exception to return
     * @param messageKey an exception message key in property file.
     * @param exception an exception to handle
     * @param exceptionClass, an instance of exception class to be created.
     * 
     * @return a handled exception
     */
    protected <T> T handleException(final String messageKey, final Exception exception, Class<T> exceptionClass	) {
		return exceptionHandler.handleException(this, messageKey, exception, exceptionClass, (Object []) null);
	}
    
    /**
     * Handles an exception. A typical usage would be to 
     * log an exception to a log file and to wrap an exception
     * into another specific application exception to return
     * Note: In this case this method creates ServiceException
     * @param messageKey an exception message key in property file
     * @param exception an exception to handle
     * @param args a list of arguments to build an exception message
     * 
     * @return a handled exception, in this case it is a ServiceException.
     */
	protected ServiceException handleException(final String messageKey, final Exception exception, final Object... args) {
		return exceptionHandler.handleException(this, messageKey, exception, ServiceException.class, args);
	}

	/**
     * Handles an exception. A typical usage would be to 
     * log an exception to a log file and to wrap an exception
     * into another specific application exception to return
     * Note: In this case this method creates ServiceException
     * @param messageKey an exception message key in property file
     * @param exception an exception to handle
     * 
     * @return a handled exception, in this case it is a ServiceException.
     */
	protected ServiceException handleException(final String messageKey, final Exception exception) {
		return exceptionHandler.handleException(this, messageKey, exception, ServiceException.class, (Object []) null);
	}
	 /**
	   * Handles an exception. A typical usage would be to 
	   * log an exception to a log file and to wrap an exception
	   * into another specific application exception to return
	   * @param exception an exception to handle
	   * @param exceptionClass, an instance of exception class to be created.
	   * @param message an exception message
	   * 
	   * @return a handled exception
	   */
	public <T> T handleException( final Exception exception, Class<T> exceptionClass,
			String message)
	{
		return exceptionHandler.handleException(this, exception, exceptionClass, message);
	}
	
	/**
	   * Handles an exception. A typical usage would be to 
	   * log an exception to a log file and to wrap an exception
	   * into another specific application exception to return
	   * Note: In this case this method creates ServiceException
	   * @param exception an exception to handle
	   * @param message an exception message
	   * 
	   * @return a handled exception, in this case it is a ServiceException.
	   */
	
	public ServiceException handleException( final Exception exception, String message)
	{
		return exceptionHandler.handleException(this, exception, ServiceException.class, message);
	}
	
    /**
     * Deallocate and dereference resources
     */
    protected void finalize () throws Throwable {
        name = null;
    }

    public void afterPropertiesSet () throws Exception {
    	Validate.notNull(exceptionHandler, "exceptionHandler must not be null");
		Validate.notNull(messageSource, "messageSource must not be null");
    }
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
