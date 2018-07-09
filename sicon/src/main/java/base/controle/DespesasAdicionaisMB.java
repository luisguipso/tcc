package base.controle;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Despesa;
import base.modelo.DespesasAdicionais;
import base.modelo.Honorario;
import base.service.DespesasAdicionaisService;
import base.service.DespesasService;
import base.service.HonorarioService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("despesasAdicionaisMB")
public class DespesasAdicionaisMB {

	private static final long serialVersionUID = 1L;

	private Despesa despesa;
	private Honorario honorario;
	private DespesasAdicionais despesasAdicinais;

	private List<Despesa> listaDespesas;
	private List<Honorario> listaHonorario;
	private List<Honorario> listaHonorarioBusca;
	private List<DespesasAdicionais> listaDespesasAdicionais;

	@Inject
	private GenericDAO<Despesa> daoDespesas; // faz as buscas

	@Inject
	private GenericDAO<Honorario> daoHonorarios; // faz as buscas

	@Inject
	private GenericDAO<DespesasAdicionais> daoDespesasAdicionais; // faz as buscas

	@Inject
	private HonorarioService honorarioService;

	@Inject
	private DespesasAdicionaisService despesasAdicionaisService; // inserir no banco

	@PostConstruct
	public void inicializar() {

		despesa = new Despesa();
		honorario = new Honorario();
		despesasAdicinais = new DespesasAdicionais();
		listaDespesas = new ArrayList<>();
		listaDespesas = daoDespesas.listaComStatus(Despesa.class);
		listaHonorario = new ArrayList<>();
		listaHonorarioBusca = new ArrayList<>();
		listaDespesasAdicionais = new ArrayList<>();
		listaDespesasAdicionais = daoDespesasAdicionais.lista(DespesasAdicionais.class);

	}

	public void preencherLista(DespesasAdicionais t) {
		this.despesasAdicinais = t;

	}

	public void salvar() {

		try {

			if (despesasAdicinais.getId() == null) {
				despesasAdicinais
						.setValorTotal(despesasAdicinais.getQuantidade() * despesasAdicinais.getDespesa().getValor());

				// preenche com todos os honorarios para o cliente
				listaHonorario = preencheListaHonorario();

				// se nao houver honorario para o cliente,
				// cria um com mesma competencia da despesa.
				if (listaHonorario.isEmpty() || listaHonorario.size() == 0) {
					System.out.println("if = lista vazia\n");
					System.out.println(String.valueOf("Tamanho da lista antes de criar: " + listaHonorario.size()));
					criarHonorario();
					listaHonorario = preencheListaHonorario();
				} else {
					System.out.println("else = lista honorario cheia");
				}

				System.out.println(String.valueOf("Tamanho da lista apos criar: " + listaHonorario.size()));

				compararCompetencias();
				// se existir honorario nesse ponto ele estará
				// preenchido para o cliente e competencia
				if (existeHonorario == false) {
					criarHonorario();
				}

				despesasAdicinais.setHonorario(honorario);
				honorario.setValor(honorario.getValor() + despesasAdicinais.getValorTotal());
				honorarioService.inserirAlterar(honorario);
				despesasAdicionaisService.inserirAlterar(despesasAdicinais);

			} else {
				despesa.setStatus(true);
				despesasAdicionaisService.inserirAlterar(despesasAdicinais);
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

	// compara se existe honorario com a competencia da despesa
	boolean existeHonorario = false;

	public void compararCompetencias() {
		try {

			LocalDate compDespesa = despesasAdicinais.getCompetencia().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();

			for (Honorario hon : listaHonorario) {
				System.out.println(String.valueOf(listaHonorario.size()));
				System.out.println("entrou no for");
				System.out.println(hon.getCliente().getNome());
				LocalDate compHonorario = hon.getCompetencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				if (String.valueOf(compDespesa.getMonth()).equals(String.valueOf(compHonorario.getMonth()))
						&& String.valueOf(compDespesa.getYear()).equals(String.valueOf(compHonorario.getYear()))) {

					System.out.println("entrou if do for");
					existeHonorario = true;
					honorario = hon;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Honorario> preencheListaHonorario() {
		listaHonorarioBusca = daoHonorarios.listarCodicaoLivre(Honorario.class,
				"cliente_id = " + despesasAdicinais.getCliente().getId().toString());
		return listaHonorarioBusca;
	}

	// excluir despesaAdicional e subtrair o valor do honorario
	public void excluir(DespesasAdicionais despAdicional) {
		listaHonorario = daoHonorarios.listarCodicaoLivre(Honorario.class,
				"cliente_id = " + despAdicional.getCliente().getId().toString());
		for (Honorario honorario : listaHonorario) {
			if (honorario.getId() == despAdicional.getHonorario().getId()) {
				honorario.setValor(honorario.getValor() - despAdicional.getValorTotal());
				honorarioService.inserirAlterar(honorario);
				despesasAdicionaisService.delete(despAdicional);
				criarNovoObjeto();
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				carregarLista();
				break;
			}
		}

	}

	// cria honorário com valor padrao
	public void criarHonorario() {
		honorario = new Honorario();
		honorario.setValor(despesasAdicinais.getCliente().getHonorario_padrao());
		honorario.setCompetencia(despesasAdicinais.getCompetencia());
		honorario.setVencimento(despesasAdicinais.getCompetencia());
		honorario.setCliente(despesasAdicinais.getCliente());
		honorarioService.inserirAlterar(honorario);

	}

	public void mostrarDespesaAdicional(DespesasAdicionais desp) {
		System.out.println("implementar");
	}

	public void criarNovoObjeto() {
		despesasAdicinais = new DespesasAdicionais();
	}

	public void carregarLista() {
		listaDespesasAdicionais = daoDespesasAdicionais.lista(DespesasAdicionais.class);
	}

	/* Getters & Setters */

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public Honorario getHonorario() {
		return honorario;
	}

	public void setHonorario(Honorario honorario) {
		this.honorario = honorario;
	}

	public DespesasAdicionais getDespesasAdicinais() {
		return despesasAdicinais;
	}

	public void setDespesasAdicinais(DespesasAdicionais despesasAdicinais) {
		this.despesasAdicinais = despesasAdicinais;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public List<Honorario> getListaHonorario() {
		return listaHonorario;
	}

	public void setListaHonorario(List<Honorario> listaHonorario) {
		this.listaHonorario = listaHonorario;
	}

	public List<DespesasAdicionais> getListaDespesasAdicionais() {
		return listaDespesasAdicionais;
	}

	public void setListaDespesasAdicionais(List<DespesasAdicionais> listaDespesasAdicionais) {
		this.listaDespesasAdicionais = listaDespesasAdicionais;
	}

	public GenericDAO<Despesa> getDaoDespesas() {
		return daoDespesas;
	}

	public void setDaoDespesas(GenericDAO<Despesa> daoDespesas) {
		this.daoDespesas = daoDespesas;
	}

	public GenericDAO<Honorario> getDaoHonorarios() {
		return daoHonorarios;
	}

	public void setDaoHonorarios(GenericDAO<Honorario> daoHonorarios) {
		this.daoHonorarios = daoHonorarios;
	}

	public GenericDAO<DespesasAdicionais> getDaoDespesasAdicionais() {
		return daoDespesasAdicionais;
	}

	public void setDaoDespesasAdicionais(GenericDAO<DespesasAdicionais> daoDespesasAdicionais) {
		this.daoDespesasAdicionais = daoDespesasAdicionais;
	}

	public HonorarioService getHonorarioService() {
		return honorarioService;
	}

	public void setHonorarioService(HonorarioService honorarioService) {
		this.honorarioService = honorarioService;
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
