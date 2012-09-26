package org.saibaba.domain.common;

import java.util.ArrayList;
import java.util.List;

import org.saibaba.fw.domain.BaseEntity;
import org.saibaba.fw.domain.Entity;

public class InvocationResult extends BaseEntity{

	private static final long serialVersionUID = 1899088966574462701L;
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	private String status;
	private String statusMessage;
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	private Entity result;
	
	private List<KeyValue> messages;
	private List<KeyValue> errors;

	public InvocationResult() {}
	
	public InvocationResult(String status) {
		this.status = status;
	}
	
	public InvocationResult(String status, Entity result) {
		this.status = status;
		this.result = result;
	}
	
	public InvocationResult(String status, List<KeyValue> messages, List<KeyValue> errors) {
		this.status = status;
		this.messages = messages;
		this.errors = errors;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Entity getResult() {
		return result;
	}

	public void setResult(Entity result) {
		this.result = result;
	}

	public void addMessage(String key, String message, Object [] args) {		
		if(messages == null) {
			messages = new ArrayList<KeyValue>();
		}
		messages.add(new KeyValue(null, key,message, args));
	}
	public void addMessage(String key, String message) {		
		this.addMessage(key,message, null);
	}
	public void addMessage(String key, Object [] args) {		
		this.addMessage(key,null, args);
	}
	public void addMessage(String message) {
		this.addMessage(null,message, null);
	}
	
	public void addMessageKey(String key) {
		this.addMessage(key,null, null);
	}
	
	public List<KeyValue> getMessages() {
		return messages;
	}
	
	public void setMessages(List<KeyValue> messages) {
		this.messages = messages;
	}
	
	public void addError(String key, String error) {		
		if(errors == null) {
			errors = new ArrayList<KeyValue>();
		}
		errors.add(new KeyValue(key,error));
	}
	
	public void addError(String field, String key, String error) {		
		if(errors == null) {
			errors = new ArrayList<KeyValue>();
		}
		errors.add(new KeyValue(field,key,error));
	}
	
	public void addError(String error) {
		this.addError(null,error);
	}
	
	public void addErrorForField(String field, String error) {
		this.addError(field,null,error);
	}
	
	public void addErrorKey(String key) {
		this.addError(key,null);
	}
	
	public void addErrorKeys(List<String> errorKeys) {
		if(errorKeys != null) {
			for(String key : errorKeys) {
				this.addError(key,null);
			}
		}
	}
	
	public void addErrorKey(String field, String key) {
		this.addError(field,key,null);
	}
	
	public List<KeyValue> getErrors() {
		return errors;
	}
	
	public void setErrors(List<KeyValue> errors) {
		this.errors = errors;
	}
	
	public String getErrorsAsString() {
		if(errors != null && errors.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for(KeyValue keyValue : errors) {
				if(keyValue != null) {
					if(keyValue.getKey() != null && keyValue.getValue() != null) {
						sb.append(keyValue.getKey() + " : " + keyValue.getValue());
						sb.append(";"); 
					} else {
						if(keyValue.getKey() != null) {
							sb.append(keyValue.getKey());
							sb.append(";");
						}
						if(keyValue.getValue() != null) {
							sb.append(keyValue.getValue());
							sb.append(";");
						}
					}
				}
			}
			return sb.toString();
		}
		return null;
	}
}
