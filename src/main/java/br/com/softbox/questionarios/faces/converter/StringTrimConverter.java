package br.com.softbox.questionarios.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

@FacesConverter(value = "stringTrimConverter")
public class StringTrimConverter implements javax.faces.convert.Converter {

	public Object getAsObject(FacesContext context, UIComponent cmp, String value) {
		return StringUtils.trimToNull(value);
	}

	public String getAsString(FacesContext context, UIComponent cmp, Object value) {
		if (value != null) {
			return value.toString().trim();
		}
		return null;
	}
}