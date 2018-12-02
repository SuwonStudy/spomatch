package com.spomatch.players.support;

/**
 * 회원이 존재하지 않는 경우 던져지는 예외
 * 
 * @author Seongbin Kim
 */
public class PlayerOfTypeNotExistException extends RuntimeException {

	private static final long serialVersionUID = 5841789828171070956L;

	public PlayerOfTypeNotExistException() {
	}

	public PlayerOfTypeNotExistException(String message) {
		super(message);
	}
}
