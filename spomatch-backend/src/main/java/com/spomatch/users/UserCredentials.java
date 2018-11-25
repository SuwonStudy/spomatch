package com.spomatch.users;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Embeddable
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@ApiModel(description = "회원의 인증 정보를 표현하는 도메인 모델이다. id와 pw가 포함된다.")
public class UserCredentials {

	@ApiModelProperty(value = "회원의 ID: 최소 5자여야 한다.", example = "testuser")
	@Length(min = 5, max = 30)
	@NotBlank
	private String idForLogin;

	@ApiModelProperty(value = "회원의 비밀번호: 최소 8자여야 한다.", example = "password")
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
