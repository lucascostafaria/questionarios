package br.com.softbox.questionarios.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TANSWERGROUP")
public class AnswerGroup implements Serializable {

	private static final long serialVersionUID = -5509077386070452044L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "EMAIL", length = 50, nullable = false)
	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "O email é inválido")
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTIONNAIRE", referencedColumnName = "ID", nullable = false)
	private Questionnaire questionnaire;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "answerGroup", orphanRemoval = true)
	private List<QuestionAnswer> answers;

	@Column(name = "FINISHED", nullable = false)
	private boolean finished;

	public AnswerGroup() {
		super();
	}

	public AnswerGroup(String email, Questionnaire questionnaire) {
		super();
		this.email = email;
		this.questionnaire = questionnaire;
		this.answers = new ArrayList<>();
		for (Question question : questionnaire.getQuestions()) {
			switch (question.getQuestionType()) {
			case OPENING:
				answers.add(new QuestionAnswerText(this, question));
				break;
			case CLOSED:
				answers.add(new QuestionAnswerAlternative(this, question));
				break;
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<QuestionAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionAnswer> answers) {
		this.answers = answers;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerGroup other = (AnswerGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
