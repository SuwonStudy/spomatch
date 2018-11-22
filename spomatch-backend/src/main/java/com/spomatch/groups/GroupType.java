package com.spomatch.groups;

import com.spomatch.common.SportsType;
import com.spomatch.groups.parties.FutsalParty;
import com.spomatch.groups.parties.SoccerParty;
import com.spomatch.groups.teams.FutsalTeam;
import com.spomatch.groups.teams.SoccerTeam;
import com.spomatch.players.FutsalPlayer;
import com.spomatch.players.Player;
import com.spomatch.players.SoccerPlayer;

public enum GroupType {

	PARTY {
		public Group<? extends Player> createGroup(Player leader, String name) {
			throw new UnsupportedOperationException("[GroupType] 추상 클래스인 Party는 인스턴스로 생성할 수 없습니다.");
		}

		@Override
		public SportsType getSportsType() {
			throw new UnsupportedOperationException("[GroupType] 추상 클래스인 Party는 SportsType이 없습니다.");
		}
	},
	TEAM {
		public Group<? extends Player> createGroup(Player leader, String name) {
			throw new UnsupportedOperationException("[GroupType] 추상 클래스인 Team은 인스턴스로 생성할 수 없습니다.");
		}

		@Override
		public SportsType getSportsType() {
			throw new UnsupportedOperationException("[GroupType] 추상 클래스인 Team은 SportsType이 없습니다.");
		}
	},
	PARTY_SOCCER {
		public Group<? extends Player> createGroup(Player leader, String name) {
			SoccerPlayer soccerLeader = (SoccerPlayer) leader;
			return new SoccerParty(soccerLeader, name);
		}

		@Override
		public SportsType getSportsType() {
			return SportsType.SOCCER;
		}
	},
	PARTY_FUTSAL {
		public Group<? extends Player> createGroup(Player leader, String name) {
			FutsalPlayer futsalLeader = (FutsalPlayer) leader;
			return new FutsalParty(futsalLeader, name);
		}

		@Override
		public SportsType getSportsType() {
			return SportsType.FUTSAL;
		}
	},
	TEAM_SOCCER {
		public Group<? extends Player> createGroup(Player leader, String name) {
			SoccerPlayer soccerLeader = (SoccerPlayer) leader;
			return new SoccerTeam(soccerLeader, name);
		}

		@Override
		public SportsType getSportsType() {
			return SportsType.SOCCER;
		}
	},
	TEAM_FUTSAL {
		public Group<? extends Player> createGroup(Player leader, String name) {
			FutsalPlayer futsalLeader = (FutsalPlayer) leader;
			return new FutsalTeam(futsalLeader, name);
		}

		@Override
		public SportsType getSportsType() {
			return SportsType.FUTSAL;
		}
	};

	public static class Values {

		public static final String PARTY = "PARTY";
		public static final String TEAM = "TEAM";

		public static final String PARTY_SOCCER = "PARTY_SOCCER";
		public static final String TEAM_SOCCER = "TEAM_SOCCER";

		public static final String PARTY_FUTSAL = "PARTY_FUTSAL";
		public static final String TEAM_FUTSAL = "TEAM_FUTSAL";
	}

	public abstract Group<? extends Player> createGroup(Player leader, String name);

	public abstract SportsType getSportsType();
}
