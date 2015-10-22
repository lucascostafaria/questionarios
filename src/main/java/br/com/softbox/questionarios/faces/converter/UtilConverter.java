package br.com.softbox.questionarios.faces.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "utilConverter")
public class UtilConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			Map<String, Object> map = component.getAttributes();
			return map.get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			component.getAttributes().put(value.toString(), value);
			return value.toString();
		}
		return null;
	}
}