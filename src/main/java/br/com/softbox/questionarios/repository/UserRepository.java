package br.com.softbox.questionarios.repository;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.exception.PersistenceException;

public interface UserRepository extends BasicRepository<User, Long> {

	User validateUser(String email, String password) throws PersistenceException;
}
