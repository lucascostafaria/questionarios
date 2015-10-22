package br.com.softbox.questionarios.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TALTERNATIVE")
public class Alternative implements Serializable {

	private static final long serialVersionUID = -8111527363902365988L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@NotNull(message = "Campo obrigatório")
	@Column(name = "index", nullable = false)
	private Integer index;

	@NotEmpty(message = "Campo obrigatório")
	@Column(name = "ALTERNATIVE_DESCRIPTION", nullable = false, length = 50)
	private String alternativeDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "QUESTION", referencedColumnName = "ID", nullable = false)
	private Question question;

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

	public String getAlternativeDescription() {
		return alternativeDescription;
	}

	public void setAlternativeDescription(String alternativeDescription) {
		this.alternativeDescription = alternativeDescription;
	}

	public Question getQuestion() {
		if (question == null) {
			question = new Question();
		}
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternativeDescription == null) ? 0 : alternativeDescription.hashCode());
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
		Alternative other = (Alternative) obj;
		if (alternativeDescription == null) {
			if (other.alternativeDescription != null)
				return false;
		} else if (!alternativeDescription.equals(other.alternativeDescription))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
