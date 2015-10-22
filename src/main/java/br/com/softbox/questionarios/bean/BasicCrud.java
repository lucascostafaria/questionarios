package br.com.softbox.questionarios.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.softbox.questionarios.service.GenericService;

public abstract class BasicCrud<T extends Serializable> {

	@Inject
	private GenericService genericService;

	private StatusCrud statusCrud;

	public abstract T getDomain();

	public abstract void setDomain(T domain);
	
	private List<T> list;


	protected final GenericService getGenericService() {
		return genericService;
	}

	public StatusCrud getStatusCrud() {
		return statusCrud;
	}

	public void setStatusCrud(StatusCrud statusCrud) {
		this.statusCrud = statusCrud;
	}
	
	public List<T> getList() {
		if (list == null) {
			list = new ArrayList<T>();
		}
		return list;
	}

	/**
	 * Método que salva um objeto
	 */
	public void save() {
		try {
			this.getGenericService().saveOrUpdate(this.getDomain());
			
			if (getStatusCrud() == StatusCrud.EDIT) {
				int index = getList().indexOf(getDomain());
				getList().remove(index);
				getList().add(index, getDomain());
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o registro.", null));
		}
	}
	
	/**
	 * Método que deleta um objeto
	 * @param object
	 */
	public void delete(Object object){
		try {
			this.getGenericService().delete(object);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro removido com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao remover o registro.", null));
		}
	}
}
