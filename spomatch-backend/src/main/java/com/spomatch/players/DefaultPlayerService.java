package com.spomatch.players;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spomatch.common.SportsType;
import com.spomatch.users.User;
import com.spomatch.users.UserService;

@Transactional
@Service
public class DefaultPlayerService implements PlayerService {

	private PlayerRepository repo;

	private UserService userSvc;

	public DefaultPlayerService(PlayerRepository repo, UserService userSvc) {
		this.repo = repo;
		this.userSvc = userSvc;
	}

	@Override
	public void addPlayerToUser(User owner, Player newPlayer) {
		owner.assignOwnerToPlayer(newPlayer);
		
		repo.save(newPlayer);
	}

	@Override
	public void deletePlayerByUsersSportsType(User owner, SportsType sportsType) {
		owner.removePlayerOfType(sportsType);
	}

	@Override
	public void deletePlayerByUserIdAndSportsType(Long ownerId, SportsType sportsType) {

		User owner = userSvc.getById(ownerId);

		deletePlayerByUsersSportsType(owner, sportsType);
	}

}
