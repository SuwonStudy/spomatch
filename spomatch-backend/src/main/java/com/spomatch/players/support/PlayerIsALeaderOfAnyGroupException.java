package com.spomatch.players.support;

public class PlayerIsALeaderOfAnyGroupException extends RuntimeException {

	private static final long serialVersionUID = 938281534630238599L;

	public PlayerIsALeaderOfAnyGroupException() {
	}
	
	public PlayerIsALeaderOfAnyGroupException(String message) {
		super(message);
	}
}
