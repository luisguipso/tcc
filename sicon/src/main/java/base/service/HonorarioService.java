package base.service;

import java.io.Serializable;
import javax.inject.Inject;
import base.modelo.Honorario;
import dao.GenericDAO;
import util.Transacional;

public class HonorarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Honorario> dao_honorario;
	
	@Transacional
	public void inserirAlterar(Honorario honorario){
		if(honorario.getId()==null){
			dao_honorario.inserir(honorario);
		}else{
			dao_honorario.alterar(honorario);
		}
	}
	
	@Transacional
	public void update(String valor){
		dao_honorario.update(valor);
	}
}
