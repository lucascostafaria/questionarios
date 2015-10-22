package br.com.softbox.questionarios.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.repository.GenericRepository;
import br.com.softbox.questionarios.service.GenericService;

@Service
@Transactional
public class GenericServiceImpl implements GenericService {

	@Inject
	private GenericRepository repo;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T saveOrUpdate(T object) throws BusinessException {
		try {
			return (T) this.repo.saveOrUpdate(object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void delete(T object) throws BusinessException {
		try {
			this.repo.delete(object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByExample(T object) throws BusinessException {
		try {
			return this.repo.findByExample(object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, ID extends Serializable> T get(Class<?> clazz, ID id, String... loadProperties) throws BusinessException {
		try {
			return (T) this.repo.get(clazz, id, loadProperties);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<?> clazz) throws BusinessException {
		try {
			return this.repo.findAll(clazz);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
