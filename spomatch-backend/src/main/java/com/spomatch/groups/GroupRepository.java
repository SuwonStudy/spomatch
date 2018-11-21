package com.spomatch.groups;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spomatch.players.Player;

public interface GroupRepository extends JpaRepository<Group<? extends Player>, Long> {

}
