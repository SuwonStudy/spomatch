package com.spomatch.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 지역 개념을 나타내는 값 객체
 */
@Entity
@ApiModel(description = "지역 개념을 표현하는 도메인 모델이다.")
public class Location {

	@ApiModelProperty("지역의 ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ApiModelProperty("지역의 명칭")
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
