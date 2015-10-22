package br.com.softbox.questionarios.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEXT")
public class QuestionAnswerText extends QuestionAnswer {

	private static final long serialVersionUID = -8294389481788848897L;

	@Column(name = "TEXT", length = 200)
	private String text;

	public QuestionAnswerText() {
		super();
	}

	public QuestionAnswerText(AnswerGroup answerGroup, Question question) {
		super(answerGroup, question);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
