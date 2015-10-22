package br.com.softbox.questionarios.repository;

import java.util.List;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.PersistenceException;

public interface AnswerRepository extends BasicRepository<AnswerGroup, Long> {

	List<AnswerGroup> listByQuestionnaire(Questionnaire questionnaire) throws PersistenceException;
}
