package com.spomatch.users;

import org.apache.commons.lang3.RandomStringUtils;

public class UserTests {

	public static User createDummy() {
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		String name = RandomStringUtils.randomAlphanumeric(5);

		UserCredentials credentials = new UserCredentials(idForLogin, pw);
		UserInfo userInfo = new UserInfo(name, 22, null);
		User toRegister = new User(null, userInfo, credentials, null);

		return toRegister;
	}

	public static User createDummy(UserCredentials credentials) {
		String name = RandomStringUtils.randomAlphanumeric(5);

		UserInfo userInfo = new UserInfo(name, 22, null);
		
		User toRegister = new User(null, userInfo, credentials, null);

		return toRegister;
	}

	public static User createDummy(UserInfo userInfo) {
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		
		UserCredentials credentials = new UserCredentials(idForLogin, pw);
		
		User toRegister = new User(null, userInfo, credentials, null);

		return toRegister;
	}
}
