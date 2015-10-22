package br.com.softbox.questionarios.auth;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.softbox.questionarios.domain.User;
import br.com.softbox.questionarios.service.UserService;

@Named
@Scope("request")
public class Authenticator implements AuthenticationProvider, Serializable {

	private static final long serialVersionUID = -1363856223149352822L;

	@Inject
	private UserService userService;

	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "O email é inválido")
	private String email;

	@NotEmpty(message = "Campo obrigatório")
	@Size(min = 4)
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		try {
			User user = userService.validateUser(email, password);

			if (user == null) {
				email = null;
				password = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou senha inválidos", null));
			} else {
				loginSpringSecurity(user);
				return "index";
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao efetuar login", null));
		}
		return "";
	}

	private void loginSpringSecurity(User user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, null);
		token.setDetails(user);

		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}
}
