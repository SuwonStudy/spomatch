package com.spomatch.groups.teams;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.groups.Group;
import com.spomatch.groups.GroupType;
import com.spomatch.players.Player;

/**
 * Team의 공통적인 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@Entity
@DiscriminatorValue(GroupType.Values.TEAM)
public abstract class Team<T extends Player> extends Group<T> {

	public Team(String name) {
		super(name);
	}

	public Team(T leader, String name) {
		super(leader, name);
	}
	
	/**
	 * 팀명을 반환한다.
	 * 
	 * @return 팀명
	 */
	public String getTeamName() {
		return name;
	}
}
