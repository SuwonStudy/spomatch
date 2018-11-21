package utils;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spomatch.groups.GroupService;
import com.spomatch.players.PlayerService;
import com.spomatch.users.User;
import com.spomatch.users.UserService;
import com.spomatch.users.UserTests;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class SpringTest {

	protected ObjectMapper mapper = new ObjectMapper()
			.setVisibility(PropertyAccessor.ALL, Visibility.NONE)
			.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected PlayerService playerService;
	
	@Autowired
	protected GroupService groupService;
	
	protected User registerDummy() {
		User toRegister = UserTests.createDummy();
		
		User registered = userService.register(toRegister);
		
		return registered;
	}

}
