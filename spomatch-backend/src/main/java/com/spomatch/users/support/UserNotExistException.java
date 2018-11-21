package com.spomatch.users.support;

/**
 * 회원이 존재하지 않는 경우 던져지는 예외
 * 
 * @author Seongbin Kim
 */
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = 5841789828171070956L;

	public UserNotExistException() {
	}

	public UserNotExistException(String message) {
		super(message);
	}
}
