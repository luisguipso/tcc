package base.controle;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Despesa;
import base.modelo.DespesasAdicionais;
import base.service.DespesasAdicionaisService;
import base.service.DespesasService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("despesaMB")
public class DespesaMB {
	
	private static final long serialVersionUID = 1L;


	private Despesa despesa;
	
	private List<Despesa> listaDespesas;
	
	@Inject
	private GenericDAO<Despesa> daoDespesas; //faz as buscas
	
	@Inject
	private DespesasService despesasService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		despesa = new Despesa();
		listaDespesas = new ArrayList<>();
		listaDespesas = daoDespesas.listaComStatus(Despesa.class);
				
	}

	public void preencherLista(Despesa t) {
		this.despesa = t;
	}

	public void inativar(Despesa t) {
		despesasService.update(" Despesa set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {

		try {			

				if (despesa.getId() == null) {
					despesa.setStatus(true);
					despesasService.inserirAlterar(despesa);

				} else {
					despesa.setStatus(true);
					despesasService.inserirAlterar(despesa);
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
		despesa = new Despesa();
	}

	public void carregarLista() {
		listaDespesas = daoDespesas.listaComStatus(Despesa.class);
	}

	
	/*Getters & Setters*/
	
	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public GenericDAO<Despesa> getDaoDespesas() {
		return daoDespesas;
	}

	public void setDaoDespesas(GenericDAO<Despesa> daoDespesas) {
		this.daoDespesas = daoDespesas;
	}

	public DespesasService getDespesasService() {
		return despesasService;
	}

	public void setDespesasService(DespesasService despesasService) {
		this.despesasService = despesasService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
