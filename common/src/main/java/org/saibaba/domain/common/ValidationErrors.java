package org.saibaba.domain.common;

import java.util.HashMap;
import java.util.Map;

import org.saibaba.fw.domain.Entity;

public class ValidationErrors extends Entity{
	
	
	private static final long serialVersionUID = 5574689578828874412L;
	Map<String,ValidationError> errors= null;

	/**
	 * 
	 * @return
	 */
	public Map<String, ValidationError> getErrors() {
		return errors;
	}

	/**
	 * 
	 * @param property
	 * @param validationError
	 */
	public void add(String property, ValidationError validationError)
	{
		if(errors == null)
		{
			errors = new HashMap<String,ValidationError>();
		}
		errors.put(property,validationError);
	}
		

	public ValidationError get(String property)
	{
		if(errors != null)
		{
			return errors.get(property);
		}
		return null;
	}
}
