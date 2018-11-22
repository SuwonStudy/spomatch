package com.spomatch.users;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.spomatch.common.SportsType;
import com.spomatch.players.Player;
import com.spomatch.players.support.PlayerIsALeaderOfAnyGroupException;
import com.spomatch.players.support.PlayerOfSameSportsTypeAlreadyExistsException;
import com.spomatch.players.support.PlayerOfTypeNotExistException;

/**
 * 회원을 정의합니다.
 * 
 * 한 회원은 여러 개의 선수 프로필을 가질 수 있습니다.
 * 
 * @author SeongbinKim
 *
 */
@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class User implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Valid
	@Embedded
	private UserInfo userInfo;

	@Valid
	@Embedded
	private UserCredentials userCredentials;

	/**
	 * 회원은 여러 개의 선수 프로필을 가집니다.
	 */
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Player> players;

	// Hibernate
	protected User() {
	}

	public User(Long id, UserInfo userInfo, UserCredentials userCredentials, Set<Player> players) {
		this.id = id;
		this.userInfo = userInfo;
		this.userCredentials = userCredentials;
		setPlayers(players);
	}

	public void assignId(Long nextId) {
		this.id = nextId;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public int getPlayerSize() {
		return players.size();
	}

	public void updateUserInfo(UserInfo toUpdate) {
		this.userInfo = toUpdate;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void removePlayerOfType(SportsType sportsType) {
		Player playerToRemove = findByType(sportsType);
		
		assertPlayerIsNotALeader(playerToRemove);
		
		players.remove(playerToRemove);
	}

	private void assertPlayerIsNotALeader(Player playerToRemove) {
		if (playerToRemove.isALeaderOfAnyGroup())
			throw new PlayerIsALeaderOfAnyGroupException();
	}

	private Player findByType(SportsType sportsType) {
		if (players.size() == 0)
			throw new PlayerOfTypeNotExistException();
		
		for (Player player : players) {
			if (player.getSportsType().equals(sportsType))
				return player;
		}
		
		throw new PlayerOfTypeNotExistException();
	}

	public void changePassword(PasswordChangeRequest req) {
		boolean correctPw = userCredentials.compareCurrent(req.getOldPassword());

		if (!correctPw)
			throw new PasswordNotMatchException();

		UserCredentials newAuth = userCredentials.changePassword(req.getNewPassword());
		this.userCredentials = newAuth;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.id == null)
			return false;

		if (obj == null)
			return false;

		if (obj instanceof Long)
			return id.equals(obj);

		if (obj instanceof User)
			return getId() == ((User) obj).getId();

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public User clone() {
		return new User(id, userInfo, userCredentials, players);
	}

	private void setPlayers(Set<Player> players) {
		if (players == null)
			players = new HashSet<>();

		this.players = players;
	}

	public void assignOwnerToPlayer(Player newPlayer) {
		if (hasPlayerOfSameType(newPlayer.getSportsType()))
			throw new PlayerOfSameSportsTypeAlreadyExistsException();

		players.add(newPlayer);
		newPlayer.assignOwner(this);
	}

	private boolean hasPlayerOfSameType(SportsType sportsType) {
		return players.stream().anyMatch(player -> player.getSportsType().equals(sportsType));
	}

}
