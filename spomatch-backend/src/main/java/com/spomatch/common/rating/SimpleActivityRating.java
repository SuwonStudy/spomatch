package com.spomatch.common.rating;

/**
 * 활동도를 결정하는 기본 구현체입니다.
 * 
 * 전체 게임 수에 의존한다.
 * 
 * @author SeongbinKim
 */
public class SimpleActivityRating implements ActivityRating {

	private int totalGamesPlayed;
	
	public SimpleActivityRating(int totalGamesPlayed) {
		this.totalGamesPlayed = totalGamesPlayed;
	}
	
	@Override
	public int getAsInt() {
		return totalGamesPlayed;
	}

	@Override
	public ActivityRatingRank getAsRank() {
		if (totalGamesPlayed <= 1)
			return ActivityRatingRank.LOW;
		else if (totalGamesPlayed <= 5)
			return ActivityRatingRank.MID;
		else
			return ActivityRatingRank.HIGH;
	}
}
