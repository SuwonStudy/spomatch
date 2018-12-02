package com.spomatch.players;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;

/**
 * 축구 선수를 나타내는 엔터티입니다.
 * 
 * @author SeongbinKim
 */
@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue(SportsType.Values.SOCCER)
public class SoccerPlayer extends Player {

	@Column(name = "preferred_position")
	private SoccerPosition preferredPosition;

	// Hibernate
	protected SoccerPlayer() {
		super(null);
	}

	public SoccerPlayer(String name) {
		super(name);
	}

	public SoccerPlayer(String name, SoccerPosition preferredPosition) {
		this(name);
		this.preferredPosition = preferredPosition;
	}

	@Override
	protected Position initPreferredPosition() {
		return preferredPosition;
	}

	@Override
	protected SportsType initSportsType() {
		return SportsType.SOCCER;
	}

}
