package com.spomatch.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService svc;
	
	@PostMapping
	public User register(@RequestBody User toRegister) {
		return svc.register(toRegister);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody UserCredentials credentials) {
		return svc.login(credentials);
	}
	
	@DeleteMapping("/{id}")
	public void deregister(@PathVariable Long id) {
		svc.deregister(id);
	}
}
