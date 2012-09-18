package org.saibaba.fw.exception;

import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class DynamicExceptionHandler extends AbstractExceptionHandler implements BeanPostProcessor {

	/**
	 * An instance of serialVersionUID
	 */
	private static final long serialVersionUID = -7708407439436553928L;

	/**
	* A default instance
	*/
	public DynamicExceptionHandler() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}

	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		if (bean instanceof ExceptionHandlerAware) {
			((ExceptionHandlerAware) bean).setExceptionHandler(this);
			final String name = ClassUtils.getShortClassName(getClass());
			log.info("Inject {} to bean [{}]", name, beanName);
		}
		return bean;
	}	
	@SuppressWarnings("unchecked")
	 public <T> T handleException(final Object caller, final String messageKey, 
	      final Exception exception, Class<T> exceptionClass, final Object... args ) {

		Exception handled = null;
		handled = instantiate(caller, messageKey, exception, exceptionClass, args);
		logException(handled, caller);		
		return (T) handled;
	}

	
	@SuppressWarnings("unchecked")
	public <T> T handleException(final Object caller, final Exception exception, Class<T> exceptionClass,
			String message) {

		Exception handled = null;
		handled = instantiate(caller, exception, exceptionClass, message);
		logException(handled, caller);
		return (T)handled;
	}

	/**
	 * This method logs exception message to log 4j log file.
	 * @param exception
	 * @param caller
	 */
	private void logException(Exception exception, Object caller) {
		final Logger exceptionLogger = caller != null ? LoggerFactory.getLogger(caller.getClass()) : log;
		exceptionLogger.error("Exception:", exception);
	}
}