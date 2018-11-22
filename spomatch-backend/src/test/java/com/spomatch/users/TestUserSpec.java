package com.spomatch.users;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.spomatch.common.Location;

/**
 * 유저 엔터티의 도메인 규칙을 분석하여 스펙을 정의하고, 테스트합니다.
 * TDD 및 given-when-then을 따릅니다.
 * 
 * @author Seongbin Kim
 */
public class TestUserSpec {

	@Test
	public void 회원의_종류로는_게스트_일반사용자_운영자_가있다() {
		
		// given & when & then
		assertNotNull(RoleType.GUEST);
		assertNotNull(RoleType.USER);
		assertNotNull(RoleType.ADMIN);
	}

	@Test
	public void 회원가입시에_제공_할_수있는_정보에는_ID_PW_이름_선호지역_나이_가있다() {
		
		// given
		String idForLogin = "아이디는5자이상30자미만이어야합니다.";
		String pw = "비밀번호는8자이상100자미만이어야합니다.";
		
		UserCredentials credentials = new UserCredentials(idForLogin, pw);
		
		String name = "이름은2자이상30자미만이어야합니다.";
		List<Location> preferredLocations = Arrays.asList(
			new Location("서울시 강남구"),
			new Location("수원시 영통구")
		);
		int age = 22;
		
		UserInfo userInfo = new UserInfo(name, age, preferredLocations);

		User user = new User(null, userInfo, credentials, null);
		assertNotNull(user);
	}
}
