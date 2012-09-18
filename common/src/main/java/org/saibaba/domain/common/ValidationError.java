package org.saibaba.domain.common;

import org.saibaba.fw.domain.Entity;

public class ValidationError extends Entity{
	
	
	private static final long serialVersionUID = -2549753928549191028L;
	
	private String property;

	private String message;	
	
	private String messageKey;
	
	Object[] args;
	

	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public ValidationError(String property, String message)
	{
		this.property = property;
		this.message = message;
	}
	
	public ValidationError(String property, String message, String messageKey)
	{
		this.property = property;
		this.message = message;
		this.messageKey = messageKey;
	}
	
	public ValidationError(String property, String message, String messageKey, Object [] args)
	{
		this.property = property;
		this.message = message;
		this.messageKey = messageKey;
		this.args = args;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String messsage) {
		this.message = messsage;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
