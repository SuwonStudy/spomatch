package com.spomatch.common.rating;

/**
 * 신용도를 결정하는 기본 구현체입니다.
 * 
 * @author SeongbinKim
 */
public class SimpleCreditRating implements CreditRating {
	
	private int totalGamesPlayed, gamesRunAway;
	
	public SimpleCreditRating(int totalGamesPlayed, int gamesRunAway) {
		this.totalGamesPlayed = totalGamesPlayed;
		this.gamesRunAway = gamesRunAway;
	}

	@Override
	public int getAsInt() {
		return calcRating();
	}

	@Override
	public CreditRatingRank getAsRank() {
		int rating = calcRating();
		
		if (rating == 100)
			return CreditRatingRank.S;
		else if (rating >= 95)
			return CreditRatingRank.A;
		else if (rating >= 90)
			return CreditRatingRank.B;
		else if (rating >= 85)
			return CreditRatingRank.C;
		else 
			return CreditRatingRank.F;
	}
	
	private int calcRating() {
		return ((totalGamesPlayed - gamesRunAway) / totalGamesPlayed) * 100;
	}

}
