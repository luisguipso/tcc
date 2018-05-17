package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Protocolo;
import dao.GenericDAO;
import util.Transacional;

public class ProtocoloService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Protocolo> daoProtocolo;
	
	@Transacional
	public void inserirAlterar(Protocolo protocolo){
		if(protocolo.getId()==null){
			daoProtocolo.inserir(protocolo);
		}else{
			daoProtocolo.alterar(protocolo);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoProtocolo.update(valor);
	}
}