package com.spomatch.users;

import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.spomatch.players.support.PlayerExistWhenCancelMembershipException;
import com.spomatch.users.support.BadInputException;
import com.spomatch.users.support.UserNotExistException;

@Transactional
@Service
public class DefaultUserService implements UserService {

	private Validator validator;
	
	private UserRepository repo;
	
	public DefaultUserService(Validator validator, UserRepository repo) {
		this.validator = validator;
		this.repo = repo;
	}

	@Override
	public User register(User toRegister) {
		
		Set<ConstraintViolation<User>> violations = validator.validate(toRegister);
		
		if (violations.size() > 0)
			throw new BadInputException("회원가입 시에 잘못된 데이터가 있습니다:" + violations);
		
		// Test 코드에서 setter 호출 시 해당 서비스에서 저장한 엔터티까지 바뀌는
		// 문제 때문에 그대로 복제해서 저장 후, 원본엔 Id만 추가하여 반환
		return repo.save(toRegister);
	}

	@Override
	public void updateUserInfo(Long id, UserInfo toUpdate) {

		User user = getById(id);
		
		user.updateUserInfo(toUpdate);
	}

	@Override
	public void changePassword(Long id, PasswordChangeRequest req) {

		User user = getById(id);
		
		user.changePassword(req);
	}

	@Override
	public void cancel(Long idToCancel) {
		
		assertNoPlayersExist(idToCancel);
		
		repo.deleteById(idToCancel);
	}

	private void assertNoPlayersExist(Long idToCancel) {
		User toCancel = getById(idToCancel);
		
		if (toCancel.getPlayerSize() > 0)
			throw new PlayerExistWhenCancelMembershipException();
	}

	@Override
	public User getById(Long id) {
		
		User user = repo.getOne(id);
		
		if (user == null)
			throw new UserNotExistException();

		// proxy는 오나봄?
		try {
			Hibernate.initialize(user);
		} catch (EntityNotFoundException e) {
			throw new UserNotExistException();
		}
		
		return user;
	}

	@Override
	public User login(UserCredentials credentials) {
		return repo.findByUserCredentials(credentials);
	}

	@Override
	public void deregister(Long id) {
		repo.deleteById(id);
	}
}
