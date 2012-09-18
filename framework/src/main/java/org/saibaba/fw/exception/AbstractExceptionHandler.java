package org.saibaba.fw.exception;

import java.util.Locale;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.saibaba.fw.utils.ReflectionException;
import org.saibaba.fw.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;


public abstract class AbstractExceptionHandler implements ExceptionHandler, MessageSourceAware, InitializingBean {

	private static final long serialVersionUID = 3316394238593300758L;
	
	public static final String COLON =":";
	/**
	   * An instance of a messageSource
	   */
	private MessageSource messageSource = null;

	
	/**
	 * An instance of a logger
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * A default instance
	 */
	protected AbstractExceptionHandler() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public final void afterPropertiesSet() throws Exception {
		Validate.notNull(messageSource, "A message source must be set");
		initService();
	}

	/**
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.springframework.context.MessageSource)
	 */
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Returns a string representation of the object in multiple line.
	 * @return A contextual string representation of the object
	 */
	public final String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		buildToString(builder);
		return builder.toString();
	}

	/**
	 * Overriden by subclasses to customize the string representation of the object.
	 * @param builder a builder to provide the string representation.
	 */
	protected void buildToString(final ToStringBuilder builder) {
		builder.append("A log4j logger", log);
		builder.append("A message source", messageSource);
	}

	/**
	 * Returns an exception message defined in a resource bundle in
	 * the following format:
	 * <br>
	 * [ClassName].[methodName].[ExceptionName]=[Message]
	 * </br>
	 * 
	 * <br>
	 * Message arguments can also be used in a message in the following
	 * format: [Mesage '{0}'].  For instance, an exception message with 
	 * an argument of 'ID' for a ServiceConfigurationException thrown 
	 * in a service method of a com.abc.SimpleService class is defined 
	 * as follow:
	 * 
	 * com.abc.SimpleService.service.ServiceConfiguration=An '{0}' is required 
	 * </br>
	 * 
	 * @param caller A caller that triggers an exception
	 * @param messageKey An exception message key in the property file.
	 * @param exception An exception
	 * @param exceptionClass an exception class to be created.
	 * @param args A list of an exception message
	 * @return An exception message
	 */
	@SuppressWarnings("rawtypes")
	protected String getExceptionMessage( final String messageKey, final Exception exception,
			 final Object... args) {

		final DynamicExceptionMessageResolver resolver = new DynamicExceptionMessageResolver( messageKey, exception, args);
		StringBuffer messageBuff = new StringBuffer(messageKey).append(COLON)
		.append(messageSource.getMessage(resolver, Locale.getDefault()));
		return messageBuff.toString();
	}

	/**
	 * Instantiates a wrapper exception
	 * @param caller a caller that triggers an exception
	 * @param messageKey an exception message key in a property file.
	 * @param exception the thrown exception
	 * @param args a list of arguments used to build a message text
	 * @return the instantiated exception wrapping the original exception
	 */
	@SuppressWarnings("rawtypes")
	protected Exception instantiate(final Object caller, final String messageKey, final Exception exception,
			Class exceptionClass, final Object... args) {

		Exception registeredException = exception;
		// final Class registeredExceptionClass = getRegisteredException(caller);
		if (exceptionClass != null) {
			try {
				if (exception != null) {
					registeredException = (Exception) ReflectionUtils.instantiate(exceptionClass, new Object[] {
							getExceptionMessage( messageKey, exception, args), exception });
				} else {
					registeredException = (Exception) ReflectionUtils.instantiate(exceptionClass,
							new Object[] { getExceptionMessage( messageKey, exception,  args) });
				}
			} catch (final ReflectionException e) {
				log.debug("Failed to instantiate exception class {} {} ", exceptionClass, e);
				// Failed to instantiate a wrapper exception so return the original exception
				registeredException = exception;
			}
		}
		return registeredException;
	}

	@SuppressWarnings("rawtypes")
	protected Exception instantiate(final Object caller, final Exception exception, Class exceptionClass, String message) {
		Exception registeredException = exception;
		if (exceptionClass != null) {
			try {
				registeredException = (Exception) ReflectionUtils.instantiate(exceptionClass, new Object[] { message,
						exception });
			} catch (final ReflectionException e) {
				log.debug("Failed to instantiate exception class {} {}", exceptionClass, e);
				// Failed to instantiate a wrapper exception so return the original exception
				registeredException = exception;
			}
		}
		return registeredException;
	}

	/**
	 * Calls from afterProperties method to perform 
	 * custom initialization in the derived class.
	 * @throws Exception thrown if failed to initialize
	 */
	protected void initService() throws Exception {
		// Allow a derived class to provide its own init
	}
}