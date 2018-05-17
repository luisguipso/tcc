package base.service;

import javax.inject.Inject;

import base.modelo.Despesas;
import dao.GenericDAO;
import util.Transacional;

public class DespesasService {
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Despesas> daoDespesas;
	
	@Transacional
	public void inserirAlterar(Despesas despesas){
		if(despesas.getId()==null){
			daoDespesas.inserir(despesas);
		}else{
			daoDespesas.alterar(despesas);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoDespesas.update(valor);
	}
}
