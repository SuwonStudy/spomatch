package com.spomatch.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 지역 개념을 나타내는 값 객체
 */
@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String location;
	
	protected Location() {
	}
	
	public Location(String location) {
		this.location = location;
	}
	
	public String getValue() {
		return location;
	}
}
