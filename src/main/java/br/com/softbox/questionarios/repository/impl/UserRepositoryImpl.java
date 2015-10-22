package br.com.softbox.questionarios.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.repository.UserRepository;

@Repository
public class UserRepositoryImpl extends BasicRepositoryImpl<User, Long> implements UserRepository {

	public User validateUser(String email, String password) throws PersistenceException {
		try {
			Criteria cri = this.getSession().createCriteria(User.class);
			cri.add(Restrictions.eq("email", email));
			cri.add(Restrictions.eq("password", DigestUtils.md5Hex(email + password)));
			return (User) cri.uniqueResult();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}
	
}
