package br.com.softbox.questionarios.repository;

import java.io.Serializable;
import java.util.List;

import br.com.softbox.questionarios.exception.PersistenceException;

public interface BasicRepository<T, ID extends Serializable> {

	T saveOrUpdate(T object) throws PersistenceException;

	void delete(T object) throws PersistenceException;

	List<T> findByExample(T object) throws PersistenceException;

	T get(Class<?> clazz, ID id, String... loadProperties) throws PersistenceException;

	List<T> findAll(Class<?> clazz) throws PersistenceException;
}
