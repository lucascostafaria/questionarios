package br.com.softbox.questionarios.service;

import java.util.List;

import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;

public interface QuestionnaireService {

	List<Questionnaire> findNotAnswerByEmail(String email) throws BusinessException;
}
