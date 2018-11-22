package com.spomatch.groups.parties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;
import com.spomatch.groups.GroupType;
import com.spomatch.players.SoccerPlayer;

/**
 * 축구 파티를 나타냅니다.
 * 
 * @author SeongbinKim
 */
@Entity
@DiscriminatorValue(GroupType.Values.PARTY_SOCCER)
public class SoccerParty extends Party<SoccerPlayer> {

	// hibernate
	protected SoccerParty() {
		super(null);
	}
	
	public SoccerParty(String name) {
		super(name);
	}
	
	public SoccerParty(SoccerPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType initSportsType() {
		return SportsType.SOCCER;
	}

	@Override
	public void registerNewPlayer(SoccerPlayer newPlayer) {
		players.add(newPlayer);
	}

	@Override
	protected GroupType initGroupType() {
		return GroupType.PARTY_SOCCER;
	}
}
