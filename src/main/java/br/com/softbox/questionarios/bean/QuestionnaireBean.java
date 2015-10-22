package br.com.softbox.questionarios.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.com.softbox.questionarios.domain.Alternative;
import br.com.softbox.questionarios.domain.AnswerGroup;
import br.com.softbox.questionarios.domain.Question;
import br.com.softbox.questionarios.domain.QuestionType;
import br.com.softbox.questionarios.domain.Questionnaire;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.service.AnswerService;
import br.com.softbox.questionarios.service.GenericService;

@Named
@Scope("session")
public class QuestionnaireBean extends BasicCrud<Questionnaire> implements Serializable {

	private static final long serialVersionUID = -1287050417777282897L;

	@Inject
	private GenericService service;
	@Inject
	private AnswerService answerService;

	private Questionnaire domain;
	private Question question;
	private Alternative alternative;
	private List<Questionnaire> questionnaires;
	private List<Question> questions;
	private List<Alternative> alternatives;
	private List<AnswerGroup> answerGroups;

	public QuestionnaireBean() {
		super();
		setStatusCrud(StatusCrud.DEFAULT);
	}

	public Questionnaire getDomain() {
		if (domain == null) {
			domain = new Questionnaire();
		}
		return domain;
	}

	public void setDomain(Questionnaire domain) {
		this.domain = domain;
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

	public Alternative getAlternative() {
		if (alternative == null) {
			alternative = new Alternative();
		}
		return alternative;
	}

	public void setAlternative(Alternative alternative) {
		this.alternative = alternative;
	}

	public List<Questionnaire> getQuestionnaires() {
		if (questionnaires == null) {
			questionnaires = new ArrayList<Questionnaire>();
		}
		return questionnaires;
	}

	public List<Question> getQuestions() {
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
		return questions;
	}

	public List<Alternative> getAlternatives() {
		if (alternatives == null) {
			alternatives = new ArrayList<Alternative>();
		}
		return alternatives;
	}

	public List<AnswerGroup> getAnswerGroups() {
		if (answerGroups == null) {
			answerGroups = new ArrayList<AnswerGroup>();
		}
		return answerGroups;
	}

	public QuestionType[] getQuestionTypes() {
		return QuestionType.values();
	}

	/**
	 * Método que adiciona uma questão a lista de questões
	 */
	public void addQuestion() {
		getQuestion().setAlternatives(new HashSet<>(getAlternatives()));
		getQuestion().setQuestionnaire(getDomain());
		getQuestion().setIndex(getQuestions().size() + 1);
		getQuestions().add(getQuestion());
		setQuestion(null);
		this.alternatives = null;
	}

	/**
	 * Método que adiciona uma alternativa a lista de alternativas
	 */
	public void addAlternatives() {
		getAlternative().setQuestion(question);
		getAlternative().setIndex(getAlternatives().size() + 1);
		getAlternatives().add(getAlternative());
		setAlternative(null);
	}

	/**
	 * Método que remove uma questão da lista de questões
	 */
	public void removeQuestion(Question question) {
		getQuestions().remove(question);
	}

	/**
	 * Método que remove uma alternativa da lista de alternativas
	 */
	public void removeAlternative(Alternative alternative) throws PersistenceException, BusinessException {
		getAlternatives().remove(alternative);
	}

	/**
	 * Método que remove um questionário do banco de dados
	 */
	public void removeQuestionnaire(Questionnaire questionnaire) throws PersistenceException, BusinessException {
		super.delete(questionnaire);
	}

	/**
	 * Método para editar um questionário
	 */
	public void editQuestionnaire(Questionnaire questionnaire) throws PersistenceException, BusinessException {
		try {
			setStatusCrud(StatusCrud.EDIT);
			this.setDomain((Questionnaire) this.service.get(Questionnaire.class, questionnaire.getId(), "questions",
					"questions.alternatives"));
			this.questions = new ArrayList<>(this.getDomain().getQuestions());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao editar questionário.", null));
		}
	}

	/**
	 * Método que limpa listas e objetos
	 */
	public void clean() {
		this.questions = null;
		this.alternatives = null;
		setQuestion(null);
		setDomain(null);
	}

	/**
	 * Método para settar o satus do crud como RESUME
	 */
	public void resume() {
		setStatusCrud(StatusCrud.RESUME);
	}

	/**
	 * Método para salvar um questionário
	 */
	public void save() {
		try {
			setStatusCrud(StatusCrud.INSERT);
			getDomain().setQuestions(new HashSet<>(this.getQuestions()));
			super.save();
			if (!getList().contains(getDomain())) {
				getList().add(getDomain());
			}
			clean();
			setStatusCrud(StatusCrud.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar questionário.", null));
		}
	}

	/**
	 * Método para cancelar a operação de cadastro e limpar os campos
	 */
	public String cancel() {
		setStatusCrud(StatusCrud.DEFAULT);
		clean();
		return "questionnaire";
	}

	/**
	 * Método para settar o satus do crud como INSERT
	 */
	public void setStatusInsert() {
		setStatusCrud(StatusCrud.INSERT);
	}

	/**
	 * Método para settar o satus do crud como EDIT
	 */
	public void setStatusEdit() {
		setStatusCrud(StatusCrud.EDIT);
	}

	/**
	 * Método para settar o satus do crud como SEARCH
	 */
	public void setStatusSearch(ActionEvent event) {
		try {
			setStatusCrud(StatusCrud.SEARCH);
			search(event);
		} catch (PersistenceException | BusinessException e) {
			e.printStackTrace();
		}
		setStatusCrud(StatusCrud.SEARCH);
	}

	/**
	 * Método que busca um questionário
	 */
	public void search(ActionEvent event) throws PersistenceException, BusinessException {
		try {
			this.questionnaires = service.findByExample(getDomain());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar questionário.", null));
		}
	}

	/**
	 * Método que busca um conjunto de respostas através do questionário
	 */
	public String listAnswers(Questionnaire questionnaire) {
		try {
			this.answerGroups = this.answerService.listByQuestionnaire(questionnaire);
			return "answerView";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar respostas.", null));
			return null;
		}
	}
}
