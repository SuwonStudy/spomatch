package com.spomatch.common.rating;

/**
 * 점수를 인터페이스를 한 번 더 추상화한 인터페이스이다.
 * 
 * @see com.spomatch.users.common.rating.CreditRating
 * @see #getAsRank() 랭크 타입으로 점수를 반환합니다.
 * @see #getAsInt() 정수 타입으로 점수를 반환합니다.
 * 
 * @author SeongbinKim
 */
public interface Rating<T extends Rank> {

	/**
	 * 정수 타입으로 점수를 반환합니다.
	 */
	int getAsInt();
	
	/**
	 * 랭크 타입으로 점수를 반환합니다.
	 */
	T getAsRank();
}
