package util;

import org.primefaces.context.RequestContext;

public class FecharDialog {
	
	public static void fecharDialogCadastro() {
		RequestContext.getCurrentInstance().execute("PF('dlgCadastrar').hide();");
	}
	
}
