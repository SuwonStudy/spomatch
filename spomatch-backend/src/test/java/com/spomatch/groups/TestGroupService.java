package com.spomatch.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spomatch.users.User;
import com.spomatch.users.UserTests;

import utils.SpringTest;

/**
 * 유저 서비스의 도메인 규칙을 분석하여 서비스의 스펙을 정의하고, 테스트합니다.
 * 
 * @author Seongbin Kim
 */
public class TestGroupService extends SpringTest {
	
	@Test
	public void 회원은_가입_할_수_있다() {
		
		// given
		User toRegister = UserTests.createDummy();
		
		// when
		User registered = userService.register(toRegister);
		
		// then
		assertNotNull(registered.getId());
		assertEquals(toRegister.getUserCredentials(), registered.getUserCredentials());
		assertEquals(toRegister.getUserInfo(), registered.getUserInfo());
	}

}
