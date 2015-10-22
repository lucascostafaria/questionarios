package br.com.softbox.questionarios.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ALTERNATIVE")
public class QuestionAnswerAlternative extends QuestionAnswer {

	private static final long serialVersionUID = -8294389481788848897L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALTERNATIVE", referencedColumnName = "ID")
	private Alternative alternative;

	public QuestionAnswerAlternative() {
		super();
	}

	public QuestionAnswerAlternative(AnswerGroup answerGroup, Question question) {
		super(answerGroup, question);
	}

	public Alternative getAlternative() {
		return alternative;
	}

	public void setAlternative(Alternative alternative) {
		this.alternative = alternative;
	}

}
