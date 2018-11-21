package com.spomatch.groups.teams;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;
import com.spomatch.groups.GroupType;
import com.spomatch.players.FutsalPlayer;

@Entity
@DiscriminatorValue(GroupType.Values.TEAM_FUTSAL)
public class FutsalTeam extends Team<FutsalPlayer> {

	// hibernate
	protected FutsalTeam() {
		super(null);
	}
	
	public FutsalTeam(String name) {
		super(name);
	}
	
	public FutsalTeam(FutsalPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType initSportsType() {
		return SportsType.FUTSAL;
	}

	@Override
	public void registerNewPlayer(FutsalPlayer newPlayer) {
		players.add(newPlayer);
	}

	@Override
	protected GroupType initGroupType() {
		return GroupType.TEAM_FUTSAL;
	}
}
