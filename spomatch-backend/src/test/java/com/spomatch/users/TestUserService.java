package com.spomatch.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.spomatch.common.SportsType;
import com.spomatch.groups.GroupType;
import com.spomatch.players.FutsalPlayer;
import com.spomatch.players.Player;
import com.spomatch.players.SoccerPlayer;
import com.spomatch.players.support.PlayerExistWhenCancelMembershipException;
import com.spomatch.players.support.PlayerIsALeaderOfAnyGroupException;
import com.spomatch.players.support.PlayerOfSameSportsTypeAlreadyExistsException;
import com.spomatch.users.support.BadInputException;
import com.spomatch.users.support.UserNotExistException;

import utils.SpringTest;

/**
 * 유저 서비스의 도메인 규칙을 분석하여 서비스의 스펙을 정의하고, 테스트합니다.
 * 
 * @author Seongbin Kim
 */
public class TestUserService extends SpringTest {
	
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

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_짧아서_실패() {
		
		// given
		UserCredentials credentials = createAuthWithCertainLengthOfId(4);
		User user = UserTests.createDummy(credentials);
		
		// when
		userService.register(user);
	}
	
	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_길어서_실패() {
		
		// given
		UserCredentials credentials = createAuthWithCertainLengthOfId(31);
		User user = UserTests.createDummy(credentials);
		
		// when
		userService.register(user);
	}
	
	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_비밀번호가_짧아서_실패() {
		
		// given
		UserCredentials credentials = createAuthWithCertainLengthOfPw(7);
		User user = UserTests.createDummy(credentials);
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_짧아서_실패() {
		
		// given 
		UserInfo userInfo = createInfoWithCertainLengthOfName(1);
		User user = UserTests.createDummy(userInfo);
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_길어서_실패() {
		
		// given 
		UserInfo userInfo = createInfoWithCertainLengthOfName(31);
		User user = UserTests.createDummy(userInfo);
		
		// when
		userService.register(user);
	}
	
	@Test
	public void 회원은_비밀번호를_수정할_수_있다_성공() {
		
		// given
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		UserCredentials credentials = new UserCredentials(idForLogin, pw);
		
		User toRegister = UserTests.createDummy(credentials);
		User registered = userService.register(toRegister);
		Long id = registered.getId();
		
		// when
		String newPassword = RandomStringUtils.randomAlphanumeric(10);
		
		userService.changePassword(id, new PasswordChangeRequest(pw, newPassword));
		
		// then
		UserCredentials credentialsChanged = credentials.changePassword(newPassword);

		User changedUser = userService.login(credentialsChanged);
		assertEquals(changedUser.getUserCredentials(), credentialsChanged);
	}

	// then
	@Test(expected = PasswordNotMatchException.class)
	public void 회원은_비밀번호를_수정할_수_있다_비밀번호가_달라서_실패() {
		
		// given
		String oldPw = RandomStringUtils.randomAlphanumeric(10);
		String newPassword = RandomStringUtils.randomAlphanumeric(10);
		User registered = registerDummy();
		Long id = registered.getId();
		
		// when
		userService.changePassword(id, new PasswordChangeRequest(oldPw, newPassword));
		
	}

	// then
	@Test(expected = UserNotExistException.class)
	public void 회원은_탈퇴할_수_있다_성공() {
		
		// given
		User user = registerDummy();
		
		// when
		userService.cancel(user.getId());
		
		userService.getById(user.getId());
	}

	// then
	@Test(expected = PlayerExistWhenCancelMembershipException.class)
	public void 회원은_탈퇴할_수_있다_플레이어가_있어서_실패() {
		
		// given
		User user = registerDummy();

		String playerName = RandomStringUtils.randomAlphabetic(5);
		Player newPlayer = new SoccerPlayer(playerName);
		
		playerService.addPlayerToUser(user, newPlayer);
		
		// when
		userService.cancel(user.getId());
	}

	@Test
	public void 회원은_동시에_여러_종목에_대한_프로필을_가질_수_있다() {
		
		// given
		User user = registerDummy();
		Long id = user.getId();
		
		// when
		String playerName = RandomStringUtils.randomAlphabetic(5);
		playerService.addPlayerToUser(user, new SoccerPlayer(playerName));
		playerService.addPlayerToUser(user, new FutsalPlayer(playerName));
		
		// then
		user = userService.getById(id);
		
		assertThat(user.getPlayerSize(), is(2));
	}

	// then
	@Test(expected = PlayerOfSameSportsTypeAlreadyExistsException.class)
	public void 회원은_각_종목별로_하나의_플레이어만을_생성할_수_있다() {
		
		// given
		User user = registerDummy();
		
		// when
		String playerName = RandomStringUtils.randomAlphabetic(5);
		
		for (int i = 0; i < 2; i++)
			playerService.addPlayerToUser(user, new SoccerPlayer(playerName));
	}
	
	@Test
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_성공() {
		
		// given
		User user = registerDummy();
		Long id = user.getId();
		
		String playerName = RandomStringUtils.randomAlphabetic(5);
		SoccerPlayer newPlayer = new SoccerPlayer(playerName);
		playerService.addPlayerToUser(user, newPlayer);
		
		// when
		playerService.deletePlayerByUserIdAndSportsType(id, SportsType.SOCCER);
		
		// then
		user = userService.getById(id);
		assertThat(user.getPlayerSize(), is(0));
	}

	// then
	@Test(expected = PlayerIsALeaderOfAnyGroupException.class)
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_그룹의_리더여서_실패() {
		// given
		User user = registerDummy();
		Long id = user.getId();
		
		// 플레이어 생성
		String playerName = RandomStringUtils.randomAlphabetic(5);
		SoccerPlayer newPlayer = new SoccerPlayer(playerName);
		playerService.addPlayerToUser(user, newPlayer);
		newPlayer = (SoccerPlayer) userService.getById(id).getPlayers().iterator().next();
		
		// 그룹 생성
		String groupName = RandomStringUtils.randomAlphabetic(5);
		groupService.createGroup(newPlayer, groupName, GroupType.PARTY_SOCCER);
		
		// when
		playerService.deletePlayerByUserIdAndSportsType(id, SportsType.SOCCER);
	}
	
	private UserCredentials createAuthWithCertainLengthOfId(int length) {
		return new UserCredentials(RandomStringUtils.randomAlphanumeric(length), RandomStringUtils.randomAlphanumeric(10));
	}

	private UserCredentials createAuthWithCertainLengthOfPw(int length) {
		return new UserCredentials(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(length));
	}

	private UserInfo createInfoWithCertainLengthOfName(int length) {
		return new UserInfo(RandomStringUtils.randomAlphanumeric(length), null);
	}
}
