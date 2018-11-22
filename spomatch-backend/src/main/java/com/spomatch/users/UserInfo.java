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

@Embeddable
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class UserInfo {

	@Length(min = 2, max = 30)
	@NotBlank
	private String name;

	private int age;

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
