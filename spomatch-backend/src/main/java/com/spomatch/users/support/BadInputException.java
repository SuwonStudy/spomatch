package com.spomatch.users.support;

/**
 * 클라이언트로부터 잘못된 입력을 받은 경우 던져지는 예외이다.
 * 
 * @author Seongbin Kim
 */
public class BadInputException extends RuntimeException {

	private static final long serialVersionUID = -2628755348866708030L;

	public BadInputException() {
	}

	public BadInputException(String message) {
		super(message);
	}
	
}
