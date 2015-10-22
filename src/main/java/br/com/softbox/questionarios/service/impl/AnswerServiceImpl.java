package br.com.softbox.questionarios.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.repository.AnswerRepository;
import br.com.softbox.questionarios.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Inject
	private AnswerRepository repo;

	@Override
	public List<AnswerGroup> listByQuestionnaire(Questionnaire questionnaire) throws BusinessException {
		try {
			return this.repo.listByQuestionnaire(questionnaire);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
