package com.spomatch.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService svc;
	
	@ApiOperation(value = "회원 가입", notes = "회원을 생성합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "정상적으로 생성됨")
	})
	@PostMapping
	public User register(@RequestBody User toRegister) {
		return svc.register(toRegister);
	}
	
	@ApiOperation(value = "로그인", notes = "id, pw쌍으로 로그인 가능한 회원을 반환합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "정상적으로 로그인됨")
	})
	@PostMapping("/login")
	public User login(@RequestBody UserCredentials credentials) {
		return svc.login(credentials);
	}
	
	@ApiOperation(value = "회원 탈퇴", notes = "해당 id와 일치하는 회원을 제거합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원이 정상적으로 제거됨")
	})
	@DeleteMapping("/{id}")
	public void deregister(@PathVariable Long id) {
		svc.deregister(id);
	}
}
