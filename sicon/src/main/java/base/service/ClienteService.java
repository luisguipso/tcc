package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Cliente;
import dao.GenericDAO;
import util.Transacional;

public class ClienteService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Cliente> daoCliente;
	
	@Transacional
	public void inserirAlterar(Cliente Cliente){
		if(Cliente.getId()==null){
			daoCliente.inserir(Cliente);
		}else{
			daoCliente.alterar(Cliente);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoCliente.update(valor);
	}
}
