package base.service;

import javax.inject.Inject;

import base.modelo.DocumentosProtocolos;
import dao.GenericDAO;
import util.Transacional;

public class ItensProtocoloService {
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<DocumentosProtocolos> daoItensProtocolo;
	
	@Transacional
	public void inserirAlterar(DocumentosProtocolos item){
		if(item.getId()==null){
			daoItensProtocolo.inserir(item);
		}else{
			daoItensProtocolo.alterar(item);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoItensProtocolo.update(valor);
	}
}
