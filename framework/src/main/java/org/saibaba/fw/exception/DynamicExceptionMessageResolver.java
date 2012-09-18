package org.saibaba.fw.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.context.MessageSourceResolvable;


public class DynamicExceptionMessageResolver implements MessageSourceResolvable {

	/**
	 * An instance of a EXCEPTION_MSG_SEPARATOR
	 */

	protected String messageKey = null;

	/**
	 * An instance of a exception
	 */
	protected Exception exception = null;


	/**
	 * An instance of a messageArguments
	 */
	protected Object[] messageArguments = null;

	/**
	 * A constructor intialized with a caller whose
	 * method triggered an exception specified the 
	 * exception argument. A list of arguments is
	 * used by the MessageSource to format an exception
	 * message.  An exception message is looked up using
	 * a set of codes.  A code is defined in the following
	 * format: {callerName].[callerMethod].[ExceptionName].
	 * 
	 * @param caller the caller that triggers an exception
	 * @param method the name of the method in which the exception is thrown
	 * @param exception the exception 
	 * @param args the arguments to build the exception message
	 */
	public DynamicExceptionMessageResolver( final String messageKey, final Exception exception, final Object[] args) {
		Validate.notNull(messageKey, "An messageKey must not be null");
		this.messageKey = messageKey;
		this.exception = exception;
		this.messageArguments = args;
	}

	/**
	 * @see org.springframework.context.MessageSourceResolvable#getCodes()
	 */
	public String[] getCodes() {
		return buildCodes();
	}

	/**
	 * @see org.springframework.context.MessageSourceResolvable#getArguments()
	 */
	public Object[] getArguments() {
		return messageArguments;
	}

	/**
	 * @see org.springframework.context.MessageSourceResolvable#getDefaultMessage()
	 */
	public String getDefaultMessage() {
		if (exception != null) {
			return exception.getMessage();
		}
		return null;
	}

	protected String[] buildCodes() {

		final List<String> codes = new ArrayList<String>();

		/**Build a list of parent exceptions including the exception itself
		final List<Class<? extends Exception>> exceptions = new ArrayList<Class<? extends Exception>>();
		exceptions.add( exception.getClass() );
		List<Class<? extends Exception>> supers = ClassUtils.getAllSuperclasses( exception.getClass() );
		exceptions.addAll( supers );
		
		// Build a list of codes to look for a string in an application resource file
		for( final Iterator<Class<? extends Exception>> i=exceptions.iterator(); i.hasNext(); ) {
		 final Class exClass = i.next();*/

		final StringBuffer id = new StringBuffer(messageKey);
		codes.add(id.toString());
		return codes.toArray(new String[] {});
	}
}