package com.spomatch.players.support;

/**
 * 이미 동일한 종목의 플레이어가 존재하는 경우 해당 예외가 던져지게 됨
 * 
 * @author Seongbin Kim
 */
public class PlayerOfSameSportsTypeAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -4188014639442476892L;

	public PlayerOfSameSportsTypeAlreadyExistsException() {
	}
	
	public PlayerOfSameSportsTypeAlreadyExistsException(String message) {
		super(message);
	}
}
