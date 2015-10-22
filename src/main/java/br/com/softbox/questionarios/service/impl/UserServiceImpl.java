package br.com.softbox.questionarios.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.repository.UserRepository;
import br.com.softbox.questionarios.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;

	@Override
	public List<User> findByExample(User user) throws BusinessException {
		try {
			return this.repo.findByExample(user);
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public User validateUser(String email, String password) throws BusinessException {
		try {
			return this.repo.validateUser(email, password);
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
	}

}
