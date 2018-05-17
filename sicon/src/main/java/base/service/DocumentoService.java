package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Documento;
import dao.GenericDAO;
import util.Transacional;

public class DocumentoService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Documento> daoDocumento;
	
	@Transacional
	public void inserirAlterar(Documento documento){
		if(documento.getId()==null){
			daoDocumento.inserir(documento);
		}else{
			daoDocumento.alterar(documento);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoDocumento.update(valor);
	}
}
