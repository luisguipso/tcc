package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarNivel")
public class validaNivelAcesso implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object o) throws ValidatorException {
		if(!o.toString().equals("funcionario")&&!o.toString().equals("administrador")) {				
				FacesMessage msg = 	new FacesMessage("Nível de Acesso Invalido!");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);		

		}
		
	}

}
