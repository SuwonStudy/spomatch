package com.spomatch.groups.parties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;
import com.spomatch.groups.GroupType;
import com.spomatch.players.FutsalPlayer;

/**
 * 풋살 파티를 나타냅니다.
 * 
 * @author Seongbin Kim
 *
 */
@Entity
@DiscriminatorValue(GroupType.Values.PARTY_FUTSAL)
public class FutsalParty extends Party<FutsalPlayer> {

	// hibernate
	protected FutsalParty() {
		super(null);
	}
	
	public FutsalParty(String name) {
		super(name);
	}
	
	public FutsalParty(FutsalPlayer leader, String name) {
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
		return GroupType.PARTY_FUTSAL;
	}
}
