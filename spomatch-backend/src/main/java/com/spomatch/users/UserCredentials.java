package com.spomatch.users;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Embeddable
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class UserCredentials {

	@Length(min = 5, max = 30)
	@NotBlank
	private String idForLogin;
	
	@Length(min = 8)
	@NotBlank
	private String pw;

	// Hibernate
	protected UserCredentials() {
	}
	
	public UserCredentials(String idForLogin, String pw) {
		this.idForLogin = idForLogin;
		this.pw = pw;
	}

	public boolean compareCurrent(String oldPassword) {
		return pw.equals(oldPassword);
	}

	public UserCredentials changePassword(String newPassword) {
		return new UserCredentials(idForLogin, newPassword);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		UserCredentials another = (UserCredentials) obj;
		
		return idForLogin.equals(another.idForLogin) && pw.equals(another.pw);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idForLogin, pw);
	}

}
