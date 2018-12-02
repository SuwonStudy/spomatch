package utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProvider {

	private static ValidatorFactory factory;

	private static Validator validator;
	
	static {
		factory = Validation.buildDefaultValidatorFactory();
		
		validator = factory.getValidator();
	}
	
	public static Validator get() {
		return validator;
	}
}
