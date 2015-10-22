package br.com.softbox.questionarios.service;

import java.util.List;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;

public interface AnswerService {

	List<AnswerGroup> listByQuestionnaire(Questionnaire questionnaire) throws BusinessException;
}
