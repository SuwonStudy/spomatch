package com.spomatch.users;

import java.util.List;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.spomatch.common.Location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Embeddable
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@ApiModel(description = "회원 정보를 표현하는 도메인 모델이다. 이름, 나이, 선호지역 등으로 구성된다.")
public class UserInfo {

	@ApiModelProperty("회원의 이름: 최소 2자여야 한다.")
	@Length(min = 2, max = 30)
	@NotBlank
	private String name;

	@ApiModelProperty("회원의 나이: (Optional)")
	private int age;

	@ApiModelProperty("회원의 선호지역 목록")
	@ManyToMany
	private List<Location> preferredLocations;

	// Hibernate
	protected UserInfo() {
	}

	public UserInfo(String name, List<Location> preferredLocations) {
		this.name = name;
		this.preferredLocations = preferredLocations;
		this.age = 0;
	}

	public UserInfo(String name, int age, List<Location> preferredLocations) {
		this.name = name;
		this.age = age;
		this.preferredLocations = preferredLocations;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public List<Location> getPreferredLocations() {
		return preferredLocations;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		UserInfo another = (UserInfo) obj;

		return name.equals(another.name) && age == another.age && eqaulsPreferredLocations(another);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age, preferredLocations);
	}

	private boolean eqaulsPreferredLocations(UserInfo another) {
		if (preferredLocations == null && another.preferredLocations == null)
			return true;
		try {
			return CollectionUtils.isEqualCollection(preferredLocations, another.preferredLocations);
		} catch (NullPointerException e) {
			return false;
		}
	}

}
