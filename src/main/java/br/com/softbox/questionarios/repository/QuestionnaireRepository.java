package br.com.softbox.questionarios.repository;

import java.util.List;

import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.PersistenceException;

public interface QuestionnaireRepository extends BasicRepository<Questionnaire, Long> {

	List<Questionnaire> findNotAnswerByEmail(String email) throws PersistenceException;

}
