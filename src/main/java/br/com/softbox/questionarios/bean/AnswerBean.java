package br.com.softbox.questionarios.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.service.GenericService;

@Named
@Scope("view")
public class AnswerBean implements Serializable {

	private static final long serialVersionUID = -4102839192710961636L;
	public static final String ANSWER_GROUP = "ANSWER_GROUP";

	@Inject
	private GenericService service;

	private AnswerGroup answerGroup;
	private List<AnswerGroup> answerGroups;

	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "O email é inválido")
	private String email;

	/**
	 * Método que recebe um questionário a ser respondido como parâmetro
	 * 
	 * @throws BusinessException
	 */
	@PostConstruct
	protected void init() throws BusinessException {
		answerGroup = (AnswerGroup) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(ANSWER_GROUP);
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(ANSWER_GROUP);
	}

	public AnswerGroup getAnswerGroup() {
		return answerGroup;
	}

	public void setAnswerGroup(AnswerGroup answerGroup) {
		this.answerGroup = answerGroup;
	}

	public List<AnswerGroup> getAnswerGroups() {
		return answerGroups;
	}

	public void setAnswerGroups(List<AnswerGroup> answerGroups) {
		this.answerGroups = answerGroups;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que salva as respostas do questionário String
	 */
	public String save() {
		try {
			getAnswerGroup().setFinished(true);
			this.service.saveOrUpdate(getAnswerGroup());
			this.answerGroup = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Questionário respondido com sucesso.", null));
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao enviar o questionário.", null));
			return null;
		}
	}

}
