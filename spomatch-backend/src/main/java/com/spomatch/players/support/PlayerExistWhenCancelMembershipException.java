package com.spomatch.players.support;

/**
 * 회원 탈퇴 시도 시 플레이어가 존재하는 경우 던져지는 예외
 *  
 * @author Seongbin Kim
 */
public class PlayerExistWhenCancelMembershipException extends RuntimeException {

	private static final long serialVersionUID = 5362410971263147389L;

	public PlayerExistWhenCancelMembershipException() {
	}

	public PlayerExistWhenCancelMembershipException(String message) {
		super(message);
	}
}
