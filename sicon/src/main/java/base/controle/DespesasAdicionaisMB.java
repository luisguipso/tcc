package base.controle;

import java.math.BigDecimal;
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
	private BigDecimal quantidade, valor;

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
				quantidade = new BigDecimal(despesasAdicinais.getQuantidade());
				valor = new BigDecimal(despesasAdicinais.getDespesa().getValor().toString());
				
				// multiplica o valor pela quantidade				
				despesasAdicinais.setValorTotal(quantidade.multiply(valor));
				System.out.println("Valor total das despesas:" + quantidade.multiply(valor));
				
				// preenche com todos os honorarios para o cliente
				listaHonorario = preencheListaHonorario();

				//verifica se existem honorario para o cliente, se nao exitirem um honorario ser� criado com a mesma competencia da despesa adicional
				if (listaHonorario.isEmpty() || listaHonorario.size() == 0) {
					System.out.println("nao existem honorarios para o cliente\n criando novo honorario...\n");
					System.out.println(
							String.valueOf("Tamanho da lista antes de criar novo honorario: " + listaHonorario.size()));
					criarHonorario();
					listaHonorario = preencheListaHonorario();
				}

				System.out.println(String.valueOf("Tamanho da lista apos verificar existencia de honorarios: " + listaHonorario.size()));
				
				//compara a competencia dos honorarios da lista com a despesa adicional, se existir honorario nesse ponto
				//ele estara preenchido para o cliente e competencia, se nao existir sera criado um honorario com valores padrao e dados da despesa para ser 
				compararCompetencias();
				
				if (existeHonorario == false) {
					criarHonorario();
				}

				// soma os valores do honorario com o valor da despesa
				somarHonorario(honorario, despesasAdicinais.getValorTotal());
				
				
				//define o honorario da despesa adicional e salva
				despesasAdicinais.setHonorario(honorario);
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
					honorario = hon; //aponta o honorario certo (mesma competencia e cliente)
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

	// excluir a despesaAdicional e subtrai o valor do honorario
	public void excluir(DespesasAdicionais despAdicional) {
		listaHonorario = daoHonorarios.listarCodicaoLivre(Honorario.class,
				"cliente_id = " + despAdicional.getCliente().getId().toString());
		for (Honorario honorario : listaHonorario) {
			if (honorario.getId() == despAdicional.getHonorario().getId()) {
				if(honorario.isPago()){
					ExibirMensagem.exibirMensagem(Mensagem.HONORARIO_JA_PAGO);
					break;
				}else {
				System.out.println("valor subtraindo: " + honorario.getValor().subtract(despAdicional.getValorTotal()));
				honorario.setValor(honorario.getValor().subtract(despAdicional.getValorTotal()));
				honorarioService.inserirAlterar(honorario);
				despesasAdicionaisService.delete(despAdicional);
				criarNovoObjeto();
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				carregarLista();
				break;
				}
			}
		}

	}

	
	// cria honorario com valor padrao
	public void criarHonorario() {
		honorario = new Honorario();
		honorario.setValor(despesasAdicinais.getCliente().getHonorario_padrao());
		honorario.setCompetencia(despesasAdicinais.getCompetencia());
		honorario.setVencimento(despesasAdicinais.getCompetencia());
		honorario.setCliente(despesasAdicinais.getCliente());
		honorarioService.inserirAlterar(honorario);

	}

	public void somarHonorario(Honorario hon, BigDecimal valorDesp) {
		honorario = hon;
		honorario.setValor(honorario.getValor().add(valorDesp));
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
