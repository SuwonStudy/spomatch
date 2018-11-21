package com.spomatch.groups;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.spomatch.groups.parties.SoccerParty;
import com.spomatch.players.Player;
import com.spomatch.players.SoccerPlayer;

public class TestGroupSpec {

	@Test
	public void 그룹은_플레이어목록_그룹타입_운동종목_리더를_가진다() {
		Group<? extends Player> group = createGroup();
		
		// 1. 플레이어 목록
		assertNotNull(group.players);
		
		// 2. 그룹타입
		assertNotNull(group.groupType);
		
		// 3. 운동종목
		assertNotNull(group.getSportsType());
		
		// 4. 리더
		assertNotNull(group.leader);
	}


	/**
	 * 플레이어를 생성한다.
	 * 타입이 없는 플레이어는 추상클래스이므로, SoccerPlayer로 대신한다.
	 * 
	 * @return 플레이어 인스턴스
	 */
	private Group<? extends Player> createGroup() {
		
		String playerName = RandomStringUtils.randomAlphanumeric(5);
		String groupName = RandomStringUtils.randomAlphanumeric(5);
		Player leader = new SoccerPlayer(playerName); 
		
		return createGroup(groupName, leader);
	}
	
	private Group<? extends Player> createGroup(String name, Player leader) {
		return new SoccerParty((SoccerPlayer) leader, name);
	}
}
