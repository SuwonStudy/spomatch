package com.spomatch.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.spomatch.common.SportsDomainEntity;
import com.spomatch.common.SportsType;
import com.spomatch.groups.Group;
import com.spomatch.groups.GroupType;
import com.spomatch.users.User;

/**
 * 선수의 공통 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@Entity
@Table(name = "players")
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sports_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Player extends SportsDomainEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User user;
	
	// DiscriminatorValue로만 지정가능하여 insert/update 불가
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "sports_type", insertable = false, updatable = false) 
	protected SportsType sportsType;

	@NotNull
	@OneToMany(targetEntity = Group.class)
	@JoinTable(
		name = "groups_players",
		joinColumns = @JoinColumn(name = "player_id"),
		inverseJoinColumns = @JoinColumn(name = "group_id")
	)
	protected List<Group<? extends Player>> belongingGroups = new ArrayList<>();
	
	public Player(String name) {
		super(name);
		this.sportsType = initSportsType();
	}
	
	public Long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public void assignOwner(User owner) {
		this.user = owner;
	}

	@Override
	public SportsType getSportsType() {
		return sportsType;
	}
	
	/**
	 * 선호하는 포지션을 반환합니다.
	 */
	protected abstract Position initPreferredPosition();

	/**
	 * 그룹타입에 맞는 그룹을 생성한다.
	 * 
	 * @param name 그룹명
	 * @param groupType 그룹타입
	 */
	public Group<? extends Player> createGroup(String name, GroupType groupType) {
		return groupType.createGroup(this, name);
	}

	public boolean isALeaderOfAnyGroup() {
		return belongingGroups.stream().anyMatch(group -> group.compareLeader(this));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.id == null)
			return false;
		
		if (obj == null)
			return false;

		if (obj instanceof Player)
			return id == ((Player) obj).getId();
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
