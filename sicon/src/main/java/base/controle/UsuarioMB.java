package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Usuario;
import base.service.UsuarioService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("usuarioMB")
public class UsuarioMB {
	
	private static final long serialVersionUID = 1L;


	private Usuario usuario;
	private List<Usuario> usuarioBusca;
	private List<Usuario> listaUsuario;
	private String senhaConfirmacao;
		
	@Inject
	private GenericDAO<Usuario> daoUsuario; //faz as buscas
	
	@Inject
	private UsuarioService usuarioService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		usuario = new Usuario();
	
		listaUsuario = daoUsuario.listaComStatus(Usuario.class);
		usuarioBusca = new ArrayList<>();
		
	}

	public void preencherListaUsuario(Usuario t) {
		this.usuario = t;

	}

	public void inativarUsuario(Usuario t) {
		usuarioService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		try {			
				
				if (usuario.getId() == null) {
					usuario.setStatus(true);
					usuarioService.inserirAlterar(usuario);
					

				} else {
					usuario.setStatus(true);
					usuarioService.inserirAlterar(usuario);
					
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
		usuario = new Usuario();
	}

	public void carregarLista() {
		listaUsuario = daoUsuario.listaComStatus(Usuario.class);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioBusca() {
		return usuarioBusca;
	}

	public void setUsuarioBusca(List<Usuario> usuarioBusca) {
		this.usuarioBusca = usuarioBusca;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public GenericDAO<Usuario> getDaoTipo() {
		return daoUsuario;
	}

	public void setDaoTipo(GenericDAO<Usuario> daoTipo) {
		this.daoUsuario = daoTipo;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}