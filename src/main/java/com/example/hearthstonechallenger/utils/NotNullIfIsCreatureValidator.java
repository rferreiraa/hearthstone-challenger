package com.example.hearthstonechallenger.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;


public class NotNullIfIsCreatureValidator implements ConstraintValidator<NotNullIfIsCreature, Object> {

	private String fieldName;
	private String expectedFieldValue;
	private String dependFieldName;

	@Override
	public void initialize(NotNullIfIsCreature annotation) {
		fieldName = annotation.fieldName();
		expectedFieldValue = annotation.fieldValue();
		dependFieldName = annotation.dependFieldName();
	}

	@Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }
        
        try {
        	BeanWrapperImpl wrapper = new BeanWrapperImpl(value);
    		
    		Object fieldValue       = wrapper.getPropertyValue(fieldName);
    		Object dependFieldValue =  wrapper.getPropertyValue(dependFieldName);
    			
    		if (expectedFieldValue.equals(fieldValue) && dependFieldValue == null) {
    		    ctx.disableDefaultConstraintViolation();
    		    ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())		    
    		        .addPropertyNode(dependFieldName)
    		        .addConstraintViolation();
    		        return false;
    		}
        }catch(Exception ex) {
        	throw new RuntimeException(ex);
        }
        

        return true;
    }

}
