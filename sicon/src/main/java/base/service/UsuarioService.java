package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Usuario;
import dao.GenericDAO;
import util.Transacional;

public class UsuarioService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Usuario> daoUsuario;
	
	@Transacional
	public void inserirAlterar(Usuario usuario){
		if(usuario.getId()==null){
			daoUsuario.inserir(usuario);
		}else{
			daoUsuario.alterar(usuario);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoUsuario.update(valor);
	}
}
