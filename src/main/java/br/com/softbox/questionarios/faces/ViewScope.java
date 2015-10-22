package br.com.softbox.questionarios.faces;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ViewScope implements Scope {

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		if (viewRoot != null) {
			Map<String, Object> viewMap = viewRoot.getViewMap();
			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			} else {
				Object obj = objectFactory.getObject();
				viewMap.put(name, obj);
				return obj;
			}
		}
		return null;
	}

	@Override
	public Object remove(String name) {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		if (viewRoot != null) {
			return viewRoot.getViewMap().remove(name);
		}
		return viewRoot;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}