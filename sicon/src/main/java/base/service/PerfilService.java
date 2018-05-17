package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Perfil;
import dao.GenericDAO;
import util.Transacional;

public class PerfilService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Perfil> daoCliente;
	
	@Transacional
	public void inserirAlterar(Perfil perfil){
		if(perfil.getId()==null){
			daoCliente.inserir(perfil);
		}else{
			daoCliente.alterar(perfil);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoCliente.update(valor);
	}
}
