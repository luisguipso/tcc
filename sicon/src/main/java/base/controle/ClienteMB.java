package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Cliente;
import base.service.ClienteService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("clienteMB")
public class ClienteMB {
	
	private static final long serialVersionUID = 1L;


	private Cliente cliente;
	private List<Cliente> clienteBusca;
	private List<Cliente> listaCliente;
		
	@Inject
	private GenericDAO<Cliente> daoTipo; //faz as buscas
	
	@Inject
	private ClienteService clienteService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		cliente = new Cliente();
	
		listaCliente = new ArrayList<>();
		listaCliente = daoTipo.listaComStatus(Cliente.class);
		clienteBusca = new ArrayList<>();
		
	}

	public void preencherListaCliente(Cliente t) {
		this.cliente = t;

	}

	public void inativarCliente(Cliente t) {
		clienteService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {

		try {			

				if (cliente.getId() == null) {
					cliente.setStatus(true);
					clienteService.inserirAlterar(cliente);

				} else {
					cliente.setStatus(true);
					clienteService.inserirAlterar(cliente);
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
		cliente = new Cliente();
	}

	public void carregarLista() {
		listaCliente = daoTipo.listaComStatus(Cliente.class);
	}
	
	/*Getters & Setters*/

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClienteBusca() {
		return clienteBusca;
	}

	public void setClienteBusca(List<Cliente> clienteBusca) {
		this.clienteBusca = clienteBusca;
	}

	public List<Cliente> getListCliente() {
		return listaCliente;
	}

	public void setListCliente(List<Cliente> listCliente) {
		this.listaCliente = listCliente;
	}

	public GenericDAO<Cliente> getDaoTipo() {
		return daoTipo;
	}

	public void setDaoTipo(GenericDAO<Cliente> daoTipo) {
		this.daoTipo = daoTipo;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
