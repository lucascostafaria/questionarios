package br.com.softbox.questionarios.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.repository.BasicRepository;

public abstract class BasicRepositoryImpl<T, ID extends Serializable> implements BasicRepository<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T saveOrUpdate(T object) throws PersistenceException {
		try {
			getSession().saveOrUpdate(object);
			return object;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public void delete(T object) throws PersistenceException {
		try {
			getSession().delete(object);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T object) throws PersistenceException {
		try {
			Criteria cri = getSession().createCriteria(object.getClass());
			Example example = Example.create(object);
			example.excludeNone();
			example.excludeZeroes();
			example.ignoreCase();
			example.enableLike(MatchMode.START);

			cri.add(example);
			return cri.list();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T get(Class<?> clazz, ID id, String... loadProperties) throws PersistenceException {
		try {
			Criteria cri = getSession().createCriteria(clazz);
			this.addFetchJoins(cri, loadProperties);
			cri.add(Restrictions.idEq(id));
			return (T) cri.uniqueResult();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> clazz) throws PersistenceException {
		try {
			Criteria cri = getSession().createCriteria(clazz);
			return cri.list();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	protected void addFetchJoins(Criteria criteria, String... loadProperties) {
		if (loadProperties != null) {
			for (String join : loadProperties) {
				criteria.setFetchMode(join, FetchMode.JOIN);
			}
		}
	}
}
