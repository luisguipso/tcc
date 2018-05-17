package base.controle;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Despesas;
import base.modelo.DespesasAdicionais;
import base.service.DespesasAdicionaisService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("despesasAdicionaisMB")
public class DespesasAdicionaisMB {
	
	private static final long serialVersionUID = 1L;


	private DespesasAdicionais despesasAdicionais;
	private List<DespesasAdicionais> despesasAdicionaisBusca;
	private List<DespesasAdicionais> listaDespesasAdicionais;
		
	@Inject
	private GenericDAO<DespesasAdicionais> daoDespesasAdicionais; //faz as buscas
	
	@Inject
	private DespesasAdicionaisService despesasAdicionaisService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		despesasAdicionais = new DespesasAdicionais();
	
		listaDespesasAdicionais = new ArrayList<>();
		listaDespesasAdicionais = daoDespesasAdicionais.listaComStatus(Despesas.class);
		despesasAdicionaisBusca = new ArrayList<>();
		
	}

	public void preencherLista(DespesasAdicionais t) {
		this.despesasAdicionais = t;

	}

	public void inativar(Despesas t) {
		despesasAdicionaisService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {

		try {			

				if (despesasAdicionais.getId() == null) {
					despesasAdicionaisService.inserirAlterar(despesasAdicionais);

				} else {
					despesasAdicionaisService.inserirAlterar(despesasAdicionais);
				}

				criarNovoObjeto();
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				FecharDialog.fecharDialogCadastro();
				carregarLista();
			
		} catch (Exception e) {
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			e.printStackTrace();
		}

	}

	public void criarNovoObjeto() {
		despesasAdicionais = new DespesasAdicionais();
	}

	public void carregarLista() {
		listaDespesasAdicionais = daoDespesasAdicionais.listaComStatus(DespesasAdicionais.class);
	}

	
	/*Getters & Setters*/
	
	
	public DespesasAdicionais getDespesasAdicionais() {
		return despesasAdicionais;
	}

	public void setDespesasAdicionais(DespesasAdicionais despesasAdicionais) {
		this.despesasAdicionais = despesasAdicionais;
	}

	public List<DespesasAdicionais> getDespesasAdicionaisBusca() {
		return despesasAdicionaisBusca;
	}

	public void setDespesasAdicionaisBusca(List<DespesasAdicionais> despesasAdicionaisBusca) {
		this.despesasAdicionaisBusca = despesasAdicionaisBusca;
	}

	public List<DespesasAdicionais> getListaDespesasAdicionais() {
		return listaDespesasAdicionais;
	}

	public void setListaDespesasAdicionais(List<DespesasAdicionais> listaDespesasAdicionais) {
		this.listaDespesasAdicionais = listaDespesasAdicionais;
	}

	public GenericDAO<DespesasAdicionais> getDaoDespesasAdicionais() {
		return daoDespesasAdicionais;
	}

	public void setDaoDespesasAdicionais(GenericDAO<DespesasAdicionais> daoDespesasAdicionais) {
		this.daoDespesasAdicionais = daoDespesasAdicionais;
	}

	public DespesasAdicionaisService getDespesasAdicionaisService() {
		return despesasAdicionaisService;
	}

	public void setDespesasAdicionaisService(DespesasAdicionaisService despesasAdicionaisService) {
		this.despesasAdicionaisService = despesasAdicionaisService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	

}