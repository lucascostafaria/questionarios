package br.com.softbox.questionarios.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.repository.QuestionnaireRepository;

@Repository
public class QuestionnaireRepositoryImpl extends BasicRepositoryImpl<Questionnaire, Long> implements QuestionnaireRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<Questionnaire> findNotAnswerByEmail(String email) throws PersistenceException {
		try {
			Criteria cri = getSession().createCriteria(Questionnaire.class, "qt");
			cri.setFetchMode("questions", FetchMode.JOIN);
			cri.setFetchMode("questions.alternatives", FetchMode.JOIN);

			DetachedCriteria dtc = DetachedCriteria.forClass(AnswerGroup.class, "ag");
			dtc.setProjection(Projections.property("ag.id"));
			dtc.createAlias("ag.questionnaire", "sqt", JoinType.INNER_JOIN);
			dtc.add(Restrictions.eq("finished", true));
			dtc.add(Restrictions.eq("email", email));
			dtc.add(Restrictions.eqProperty("sqt.id", "qt.id"));

			cri.add(Subqueries.notExists(dtc));

			cri.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

			return cri.list();
		} catch (HibernateException e) {
			throw new PersistenceException(e);
		}
	}

}
