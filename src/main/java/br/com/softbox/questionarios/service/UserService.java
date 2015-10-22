package br.com.softbox.questionarios.service;

import java.util.List;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.exception.BusinessException;

public interface UserService {

	List<User> findByExample(User user) throws BusinessException;

	User validateUser(String email, String password) throws BusinessException;
}
