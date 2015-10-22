package br.com.softbox.questionarios.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.repository.QuestionnaireRepository;
import br.com.softbox.questionarios.service.QuestionnaireService;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Inject
	private QuestionnaireRepository repo;

	@Override
	public List<Questionnaire> findNotAnswerByEmail(String email) throws BusinessException {
		try {
			return this.repo.findNotAnswerByEmail(email);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
