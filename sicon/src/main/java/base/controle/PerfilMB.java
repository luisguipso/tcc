package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Perfil;
import base.service.PerfilService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("perfilMB")
public class PerfilMB {
	
	private static final long serialVersionUID = 1L;


	private Perfil perfil;
	private List<Perfil> perfilBusca;
	private List<Perfil> listaPerfil;
	private String senhaConfirmacao;
		
	@Inject
	private GenericDAO<Perfil> daoTipo; //faz as buscas
	
	
	@Inject
	private PerfilService perfilService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		perfil = new Perfil();
	
		listaPerfil = new ArrayList<>();
		listaPerfil = daoTipo.listaComStatus(Perfil.class);
		perfilBusca = new ArrayList<>();
		
		
	}

	public void preencherListaPerfil(Perfil t) {
		this.perfil = t;

	}

	public void inativarPerfil(Perfil t) {
		perfilService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		try {			
				
				if (perfil.getId() == null) {
					perfil.setStatus(true);
					perfilService.inserirAlterar(perfil);
					

				} else {
					perfil.setStatus(true);
					perfilService.inserirAlterar(perfil);
					
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
		perfil = new Perfil();
	}

	public void carregarLista() {
		listaPerfil = daoTipo.listaComStatus(Perfil.class);
	}
	
	
	
	/*Getters & Setters*/
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfilBusca() {
		return perfilBusca;
	}

	public void setPerfilBusca(List<Perfil> perfilBusca) {
		this.perfilBusca = perfilBusca;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public GenericDAO<Perfil> getDaoTipo() {
		return daoTipo;
	}

	public void setDaoTipo(GenericDAO<Perfil> daoTipo) {
		this.daoTipo = daoTipo;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}
	
	
	
}