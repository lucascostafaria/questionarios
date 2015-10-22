package br.com.softbox.questionarios.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.exception.BusinessException;
import br.com.softbox.questionarios.exception.PersistenceException;
import br.com.softbox.questionarios.service.UserService;

@Named
@Scope("view")
public class UserBean extends BasicCrud<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	private User domain;
	private List<User> list;
	private StatusCrud statusCrud;

	public UserBean() {
		setStatusCrud(StatusCrud.SEARCH);
	}

	public User getDomain() {
		if (domain == null) {
			domain = new User();
		}
		return domain;
	}

	public void setDomain(User domain) {
		this.domain = domain;
	}

	public List<User> getList() {
		if (list == null) {
			list = new ArrayList<User>();
		}
		return list;
	}

	public StatusCrud getStatusCrud() {
		return statusCrud;
	}

	public void setStatusCrud(StatusCrud statusCrud) {
		this.statusCrud = statusCrud;
	}

	public void clean() {
		setDomain(null);
	}

	/**
	 * Método que salva um usuário
	 */
	public void save() {
		setStatusCrud(StatusCrud.INSERT);
		super.save();
		getList().add(getDomain());
		clean();
	}

	/**
	 * Método que remove um usuário
	 */
	public void remove(User user) throws PersistenceException, BusinessException {
		super.delete(user);
	}

	/**
	 * Método que edita um usuário
	 */
	public void edit(User user) throws PersistenceException, BusinessException {
		setStatusCrud(StatusCrud.EDIT);
		getDomain().setId(user.getId());
		getDomain().setEmail(user.getEmail());
		getDomain().setPassword(user.getPassword());
	}

	/**
	 * Método que busca um usuário
	 */
	public void search(ActionEvent event) throws PersistenceException, BusinessException {
		try {
			this.list = this.userService.findByExample(getDomain());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao pesquisar usuários.", null));
		}
	}
}
