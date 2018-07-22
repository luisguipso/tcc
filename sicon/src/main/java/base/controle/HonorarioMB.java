package base.controle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import base.modelo.Cliente;
import base.modelo.DespesasAdicionais;
import base.modelo.DocumentosProtocolos;
import base.modelo.Honorario;
import base.modelo.Protocolo;
import base.service.DespesasAdicionaisService;
import base.service.HonorarioService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("honorarioMB")
public class HonorarioMB {		
		private static final long serialVersionUID = 1L;


		private Honorario honorario;
		private Cliente cliente;
		private DespesasAdicionais despesa;
		private List<Honorario> honorarioBusca;
		private List<Honorario> listaHonorario;
		private List<Cliente> listaClientes;
		private List<DespesasAdicionais> listaDespesasAdicionais;
			
		@Inject
		private GenericDAO<Honorario> daoHonorario; //faz as buscas
		
		@Inject
		private GenericDAO<Cliente> daoCliente; //faz as buscas
		
		@Inject
		private GenericDAO<DespesasAdicionais> daoDespesasAdicionais; //faz as buscas
		
		@Inject
		private HonorarioService honorarioService; // inserir no banco
		
		@Inject
		private DespesasAdicionaisService despesasAdicionaisService; // inserir no banco
		
		@Inject
		private EntityManager manager;
		
		
		@PostConstruct
		public void inicializar() {
		
			honorario = new Honorario();
			cliente = new Cliente();
			despesa = new DespesasAdicionais();
			
			listaHonorario = daoHonorario.lista(Honorario.class);
			listaClientes = new ArrayList<>();
			listaDespesasAdicionais = new ArrayList<>();
			
			listaClientes = daoCliente.listaComStatus(Cliente.class);
			listaDespesasAdicionais = daoDespesasAdicionais.lista(DespesasAdicionais.class);
			honorarioBusca = new ArrayList<>();
			
		}

		public void preencherHonorario(Honorario t) {
			this.honorario = t;

		}

		public void inativar(Honorario t) {
			honorarioService.update(" Tipo set status = false where id = " + t.getId());
			criarNovoObjeto();
			ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
			carregarLista();
		}

		public void salvar() {

			try {
						honorario.setStatus(true);
						for(Cliente cli : listaClientes) {
							honorario.setCliente(cli);//determina a qual cliente o honorario pertence
							honorario.setValor(cli.getHonorario_padrao()); //insere o valor do honorário do cliente
							Date data = new Date();
							System.out.println(String.valueOf(data.getTime()));
							
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
			honorario = new Honorario();
		}

		public void carregarLista() {
			listaHonorario = daoHonorario.listaComStatus(Honorario.class);
		}

		public void mostrarHonorario(Honorario t) {
			this.honorario = t;
			String condicao = "honorario_id =" + honorario.getId().toString();
			this.listaDespesasAdicionais = daoDespesasAdicionais.listar(DespesasAdicionais.class, condicao);
		}
		
		
		public void imprimirRelatorioHonorario(Honorario h) { 
			try {
				List<Honorario> relatorio = daoHonorario.listar(Honorario.class, "status = true");
				if (relatorio.size() > 0) {

					HashMap parametro = new HashMap<>();
					parametro.put("idHonorario", "h.getId()");
					ChamarRelatorio ch = new ChamarRelatorio("honorario.jasper", parametro, "protocolo_de_entrega");
					Session sessions = manager.unwrap(Session.class);
					sessions.doWork(ch);

				} else {
					ExibirMensagem.exibirMensagem(Mensagem.NADA_ENCONTRADO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			}
		}

		/*Getters & Setters*/
		public Honorario getHonorario() {
			return honorario;
		}

		public void setHonorario(Honorario honorario) {
			this.honorario = honorario;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public DespesasAdicionais getDespesa() {
			return despesa;
		}

		public void setDespesa(DespesasAdicionais despesa) {
			this.despesa = despesa;
		}

		public List<Honorario> getHonorarioBusca() {
			return honorarioBusca;
		}

		public void setHonorarioBusca(List<Honorario> honorarioBusca) {
			this.honorarioBusca = honorarioBusca;
		}

		public List<Honorario> getListaHonorario() {
			return listaHonorario;
		}

		public void setListaHonorario(List<Honorario> listaHonorario) {
			this.listaHonorario = listaHonorario;
		}

		public List<Cliente> getListaClientes() {
			return listaClientes;
		}

		public void setListaClientes(List<Cliente> listaClientes) {
			this.listaClientes = listaClientes;
		}

		public List<DespesasAdicionais> getListaDespesasAdicionais() {
			return listaDespesasAdicionais;
		}

		public void setListaDespesasAdicionais(List<DespesasAdicionais> listaDespesasAdicionais) {
			this.listaDespesasAdicionais = listaDespesasAdicionais;
		}

		public GenericDAO<Honorario> getDaoHonorario() {
			return daoHonorario;
		}

		public void setDaoHonorario(GenericDAO<Honorario> daoHonorario) {
			this.daoHonorario = daoHonorario;
		}

		public GenericDAO<Cliente> getDaoCliente() {
			return daoCliente;
		}

		public void setDaoCliente(GenericDAO<Cliente> daoCliente) {
			this.daoCliente = daoCliente;
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

		public EntityManager getManager() {
			return manager;
		}

		public void setManager(EntityManager manager) {
			this.manager = manager;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		

		
}
