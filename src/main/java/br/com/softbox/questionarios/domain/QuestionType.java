package br.com.softbox.questionarios.domain;

public enum QuestionType {
	
	/** Aberta */
	OPENING("Aberta"),
	/** Fechada */
	CLOSED("Fechada");

	private String description;

	private QuestionType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
