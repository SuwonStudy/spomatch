package com.spomatch.players;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spomatch.groups.Group;
import com.spomatch.groups.GroupType;

public class TestPlayerSpec {

	@Test
	public void 플레이어는_이름_선호포지션_운동종목을_가진다() {

		// 1. 선호 포지션 받음
		// 2. 이름 받음
		Player p = createPlayer();

		// 3. 운동 종목 있음
		assertNotNull(p.getSportsType());
	}

	@Test
	public void 플레이어는_그룹을_생성할_수_있다() {
		Player p = createPlayer();

		String groupName = "그룹명";
		GroupType groupType = GroupType.PARTY_SOCCER;
		
		Group<? extends Player> group = p.createGroup(groupName, groupType);
		
		assertNotNull(group);
	}

	/**
	 * 플레이어를 생성한다.
	 * 타입이 없는 플레이어는 추상클래스이므로, SoccerPlayer로 대신한다.
	 * 
	 * @return 플레이어 인스턴스
	 */
	private Player createPlayer() {
		return new SoccerPlayer("name", SoccerPosition.FW);
	}
}
