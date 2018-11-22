package com.spomatch.groups.parties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.groups.Group;
import com.spomatch.groups.GroupType;
import com.spomatch.players.Player;

/**
 * Party의 공통적인 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@Entity
@DiscriminatorValue(GroupType.Values.PARTY)
public abstract class Party<T extends Player> extends Group<T> {

	public Party(String name) {
		super(name);
	}
	
	public Party(T leader, String name) {
		super(leader, name);
	}
	
	/**
	 * 파티명을 반환한다.
	 * 
	 * @return 파티명
	 */
	public String getPartyName() {
		return name;
	}
}
