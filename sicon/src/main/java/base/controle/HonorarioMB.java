package base.controle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Cliente;
import base.modelo.DespesasAdicionais;
import base.modelo.Honorario;
import base.service.DespesasAdicionaisService;
import base.service.HonorarioService;
import dao.GenericDAO;
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
		private List<DespesasAdicionais> listaDespesas;
			
		@Inject
		private GenericDAO<Honorario> dao_honorario; //faz as buscas
		
		@Inject
		private GenericDAO<Cliente> dao_cliente; //faz as buscas
		
		@Inject
		private GenericDAO<DespesasAdicionais> dao_despesasAdicionais; //faz as buscas
		
		@Inject
		private HonorarioService honorarioService; // inserir no banco
		
		@Inject
		private DespesasAdicionaisService despesasAdicionaisService; // inserir no banco
		
		@PostConstruct
		public void inicializar() {
		
			honorario = new Honorario();
			cliente = new Cliente();
			despesa = new DespesasAdicionais();
			
			listaHonorario = new ArrayList<>();
			listaClientes = new ArrayList<>();
			listaDespesas = new ArrayList<>();
			
			listaClientes = dao_cliente.listaComStatus(Cliente.class);
			listaDespesas = dao_despesasAdicionais.listaComStatus(DespesasAdicionais.class);
			honorarioBusca = new ArrayList<>();
			
		}

		public void preencherLista(Honorario t) {
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
							double valor = cli.getHonorario_padrao(); //salva o valor padrao do honorario pra cada cliente
							//honorarioService.inserirAlterar(honorario); 
							for(DespesasAdicionais desp : listaDespesas) {
								if(desp.getCliente()==honorario.getCliente() && desp.getCompetencia() == honorario.getCompetencia()) {
									//verifica se a despesa é do mesmo cliente e de mesma competencia que o honorario
									desp.setHonorario(honorario);
									despesasAdicionaisService.inserirAlterar(desp);
									honorario.setValor(valor += desp.getValorTotal());//calcula valor total do honorario									
								}
								
							}
							honorarioService.inserirAlterar(honorario);//salva ou altera honorario
							criarNovoObjeto();
						}	
						
						//quartz scheduler - lib pra programar o cadastro
					//fazer tudo isso ou gerar o honorario sempre que gerar a despesa?

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
			listaHonorario = dao_honorario.listaComStatus(Honorario.class);
		}

		/*Getters & Setters*/
		
		public Honorario getHonorario() {
			return honorario;
		}

		public void setHonorario(Honorario honorario) {
			this.honorario = honorario;
		}

		public List<Honorario> getHonorarioBusca() {
			return honorarioBusca;
		}

		public void setHonorarioBusca(List<Honorario> honorarioBusca) {
			this.honorarioBusca = honorarioBusca;
		}

		public List<Honorario> getLista_honorario() {
			return listaHonorario;
		}

		public void setLista_honorario(List<Honorario> lista_honorario) {
			this.listaHonorario = lista_honorario;
		}

		public GenericDAO<Honorario> getDao_honorario() {
			return dao_honorario;
		}

		public void setDao_honorario(GenericDAO<Honorario> dao_honorario) {
			this.dao_honorario = dao_honorario;
		}

		public HonorarioService getHonorarioService() {
			return honorarioService;
		}

		public void setHonorarioService(HonorarioService honorarioService) {
			this.honorarioService = honorarioService;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		

		
		
	

}
