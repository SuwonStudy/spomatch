package com.spomatch.groups;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spomatch.players.Player;

@Service
@Transactional
public class DefaultGroupService implements GroupService {

	private GroupRepository repo;
	
	public DefaultGroupService(GroupRepository repo) {
		this.repo = repo;
	}

	@Override
	public void registerPlayerToGroup(Player newPlayer, Group<Player> belongingGroup) {
		belongingGroup.registerNewPlayer(newPlayer);
	}

	@Override
	public void createGroup(Player leader, String name, GroupType groupType) {
		Group<? extends Player> created = leader.createGroup(name, groupType);

		repo.save(created);
	}

}
