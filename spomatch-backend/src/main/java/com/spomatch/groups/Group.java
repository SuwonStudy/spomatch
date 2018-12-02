package com.spomatch.groups;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.spomatch.common.SportsDomainEntity;
import com.spomatch.common.SportsType;
import com.spomatch.players.Player;

/**
 * Player 집단에서 사용되는 속성을 추상화한 클래스입니다.
 * 
 * @author SeongbinKim
 */
@Entity
@Table(name = "groups")
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "group_type", discriminatorType = DiscriminatorType.STRING)
@AttributeOverride(
   column = @Column(name = "sports_type", insertable = false, updatable = false),
   name = "sports_type"
)
public abstract class Group<T extends Player> extends SportsDomainEntity {

	@NotNull
	@OneToOne(targetEntity = Player.class)
	protected T leader;

	@NotNull
	@OneToMany(targetEntity = Player.class, orphanRemoval = true)
	@JoinTable(
		name = "groups_players",
		joinColumns = @JoinColumn(name = "group_id"),
		inverseJoinColumns = @JoinColumn(name = "player_id")
	)
	protected List<T> players;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "group_type", insertable = false, updatable = false)
	protected GroupType groupType;

	public Group(String name) {
		super(name);
		this.groupType = initGroupType();
	}

	public Group(T leader, String name) {
		this(name);
		this.leader = leader;
		this.players = new ArrayList<>();
		this.players.add(leader);
	}

	@Override
	public SportsType getSportsType() {
		return groupType.getSportsType();
	}
	
	/**
	 * 플레이어를 그룹에 가입시킨다.
	 * 
	 * @param newPlayer 새로 들어올 플레이어
	 */
	public abstract void registerNewPlayer(T newPlayer);
	
	protected abstract GroupType initGroupType();

	public boolean compareLeader(Player player) {
		return leader.equals(player);
	}
}
