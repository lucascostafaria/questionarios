package br.com.softbox.questionarios.bean;

public enum StatusCrud {
	/** Resumo */
	RESUME("Resumo"),
	/** Padrão */
	DEFAULT("Padrão"),
	/** Inserção */
	INSERT("Cadastrando"),
	/** Edição */
	EDIT("Editando"),
	/** Pesquisa */
	SEARCH("Pesquisando");

	private String description;

	private StatusCrud(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
