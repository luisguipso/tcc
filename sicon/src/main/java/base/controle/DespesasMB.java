package base.controle;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Despesas;
import base.service.DespesasService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("despesasMB")
public class DespesasMB {
	
	private static final long serialVersionUID = 1L;


	private Despesas despesas;
	private List<Despesas> despesasBusca;
	private List<Despesas> listaDespesas;
		
	@Inject
	private GenericDAO<Despesas> daoDespesas; //faz as buscas
	
	@Inject
	private DespesasService despesasService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		despesas = new Despesas();
	
		listaDespesas = new ArrayList<>();
		listaDespesas = daoDespesas.listaComStatus(Despesas.class);
		despesasBusca = new ArrayList<>();
		
	}

	public void preencherLista(Despesas t) {
		this.despesas = t;

	}

	public void inativar(Despesas t) {
		despesasService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {

		try {			

				if (despesas.getId() == null) {
					despesas.setStatus(true);
					despesasService.inserirAlterar(despesas);

				} else {
					despesas.setStatus(true);
					despesasService.inserirAlterar(despesas);
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
		despesas = new Despesas();
	}

	public void carregarLista() {
		listaDespesas = daoDespesas.listaComStatus(Despesas.class);
	}

	/*Getters & Setters*/
	
	public Despesas getDespesas() {
		return despesas;
	}

	public void setDespesas(Despesas despesas) {
		this.despesas = despesas;
	}

	public List<Despesas> getDespesasBusca() {
		return despesasBusca;
	}

	public void setDespesasBusca(List<Despesas> despesasBusca) {
		this.despesasBusca = despesasBusca;
	}

	public List<Despesas> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesas> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public GenericDAO<Despesas> getDaoDespesas() {
		return daoDespesas;
	}

	public void setDaoDespesas(GenericDAO<Despesas> daoDespesas) {
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
