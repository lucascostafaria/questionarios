package br.com.softbox.questionarios.service;

import java.io.Serializable;
import java.util.List;

import br.com.softbox.questionarios.exception.BusinessException;

public interface GenericService {

	<T> T saveOrUpdate(T object) throws BusinessException;

	<T> void delete(T object) throws BusinessException;

	<T> List<T> findByExample(T object) throws BusinessException;

	<T, ID extends Serializable> T get(Class<?> clazz, ID id, String... loadProperties) throws BusinessException;

	<T> List<T> findAll(Class<?> clazz) throws BusinessException;

}
