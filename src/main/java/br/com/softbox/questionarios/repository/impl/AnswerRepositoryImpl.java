package br.com.softbox.questionarios.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.repository.AnswerRepository;

@Repository
public class AnswerRepositoryImpl extends BasicRepositoryImpl<AnswerGroup, Long> implements AnswerRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<AnswerGroup> listByQuestionnaire(Questionnaire questionnaire) throws PersistenceException {
		try {
			Criteria cri = getSession().createCriteria(AnswerGroup.class, "ag");
			cri.createAlias("questionnaire", "qt", JoinType.INNER_JOIN);
			cri.createAlias("answers", "asw", JoinType.LEFT_OUTER_JOIN);
			cri.createAlias("asw.question", "q", JoinType.LEFT_OUTER_JOIN);
			cri.createAlias("asw.alternative", "a", JoinType.LEFT_OUTER_JOIN);

			cri.add(Restrictions.eq("finished", true));
			cri.add(Restrictions.eq("questionnaire", questionnaire));

			cri.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

			cri.addOrder(Order.asc("email"));
			cri.addOrder(Order.asc("qt.name"));
			return cri.list();
		} catch (HibernateException e) {
			throw new PersistenceException(e);
		}
	}

}
