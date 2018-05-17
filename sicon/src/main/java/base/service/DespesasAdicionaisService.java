package base.service;

import javax.inject.Inject;
import base.modelo.DespesasAdicionais;
import dao.GenericDAO;
import util.Transacional;

public class DespesasAdicionaisService {
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<DespesasAdicionais> daoDespesasAdicionais;
	
	@Transacional
	public void inserirAlterar(DespesasAdicionais despesasAdicionais){
		if(despesasAdicionais.getId()==null){
			daoDespesasAdicionais.inserir(despesasAdicionais);
		}else{
			daoDespesasAdicionais.alterar(despesasAdicionais);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoDespesasAdicionais.update(valor);
	}
}
