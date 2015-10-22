package br.com.softbox.questionarios.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TQUESTION")
public class Question implements Serializable {

	private static final long serialVersionUID = 6938957504170924982L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@NotNull(message = "Campo obrigatório")
	@Column(name = "index", nullable = false)
	private Integer index;

	@NotNull(message = "Campo obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "QUESTION_TYPE")
	private QuestionType questionType;

	@NotEmpty(message = "Campo obrigatório")
	@Column(name = "QUESTION_DESCRIPTION", nullable = false, length = 200)
	private String questionDescription;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
	@OrderBy("index")
	private Set<Alternative> alternatives;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "QUESTIONNAIRE", referencedColumnName = "ID", nullable = false)
	private Questionnaire questionnaire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public Set<Alternative> getAlternatives() {
		if (alternatives == null) {
			alternatives = new HashSet<Alternative>();
		}
		return alternatives;
	}

	public void setAlternatives(Set<Alternative> alternatives) {
		this.alternatives = alternatives;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questionDescription == null) ? 0 : questionDescription.hashCode());
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
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questionDescription == null) {
			if (other.questionDescription != null)
				return false;
		} else if (!questionDescription.equals(other.questionDescription))
			return false;
		return true;
	}

}
