package br.com.softbox.questionarios.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.service.QuestionnaireService;

@Named
@Scope("view")
public class IndexBean implements Serializable {

	private static final long serialVersionUID = -1287050417777282897L;

	@Inject
	private QuestionnaireService questionnaireService;

	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "O email é inválido")
	private String email;

	private List<Questionnaire> questionnaires;

	public List<Questionnaire> getQuestionnaires() {
		if (questionnaires == null) {
			questionnaires = new ArrayList<Questionnaire>();
		}
		return questionnaires;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que identifica o email do usuário e busca os questionários ainda não respondidos por ele
	 * @param event
	 */
	public void identificate(ActionEvent event) {
		try {
			this.questionnaires = this.questionnaireService.findNotAnswerByEmail(this.getEmail());
			if (this.questionnaires.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Não existem questionários para serem respondidos.", null));
				this.setEmail(null);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar questionários.", null));
		}
	}

	/**
	 * Método que envia por parâmetro o questionário a ser respondido
	 * @param questionnaire
	 */
	public void answer(Questionnaire questionnaire) {
		try {
			AnswerGroup group = new AnswerGroup(this.getEmail(), questionnaire);

			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.getSessionMap().put(AnswerBean.ANSWER_GROUP, group);
			ec.redirect("/answerQuestionnaire.jsf");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar questionário.", null));
		}
	}
}
