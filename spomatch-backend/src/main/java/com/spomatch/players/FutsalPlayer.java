package com.spomatch.players;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.spomatch.common.SportsType;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue(SportsType.Values.FUTSAL)
public class FutsalPlayer extends Player {

	@Column(name = "preferred_position")
	private FutsalPosition preferredPosition;

	//Hibernate
	protected FutsalPlayer() {
		super(null);
	}
	
	public FutsalPlayer(String name) {
		super(name);
	}
	
	public FutsalPlayer(String name, FutsalPosition preferredPosition) {
		this(name);
		this.preferredPosition = preferredPosition;
	}

	@Override
	protected Position initPreferredPosition() {
		return preferredPosition;
	}

	@Transient
	@Override
	protected SportsType initSportsType() {
		return SportsType.FUTSAL;
	}

}
