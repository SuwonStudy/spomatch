package com.spomatch.groups.teams;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;
import com.spomatch.groups.GroupType;
import com.spomatch.players.SoccerPlayer;

@Entity
@DiscriminatorValue(GroupType.Values.TEAM_SOCCER)
public class SoccerTeam extends Team<SoccerPlayer> {

	// hibernate
	protected SoccerTeam() {
		super(null);
	}
	
	public SoccerTeam(String name) {
		super(name);
	}
	
	public SoccerTeam(SoccerPlayer leader, String name) {
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
		return GroupType.TEAM_SOCCER;
	}
}
