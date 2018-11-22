package com.spomatch.groups;

import com.spomatch.players.Player;

public interface GroupService {

	void registerPlayerToGroup(Player newPlayer, Group<Player> belongingGroup);

	void createGroup(Player leader, String name, GroupType groupType);
}
