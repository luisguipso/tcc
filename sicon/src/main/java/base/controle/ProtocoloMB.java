package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;

import base.modelo.DocumentosProtocolos;
import base.modelo.Protocolo;
import base.modelo.Usuario;
import base.service.ItensProtocoloService;
import base.service.ProtocoloService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.Mensagem;

@ViewScoped
@Named("protocoloMB")
public class ProtocoloMB {
	private static final long serialVersionUID = 1L;

	private Protocolo protocolo;
	private DocumentosProtocolos itensProtocolo;
	private List<Protocolo> protocoloBusca;
	private List<Protocolo> listaProtocolo;
	private List<DocumentosProtocolos> listaItensProtocolo;

	@Inject
	private GenericDAO<Protocolo> daoProtocolo; //faz as buscas

	@Inject
	private GenericDAO<DocumentosProtocolos> daoItens;

	@Inject
	private ItensProtocoloService itensProtocoloService; // inserir no banco

	@Inject
	private ProtocoloService protocoloService;
	
	@PostConstruct
	public void inicializar() {
		protocolo = new Protocolo();
		listaProtocolo = new ArrayList<>();
		listaProtocolo = daoProtocolo.listaComStatus(Protocolo.class);
		protocoloBusca = new ArrayList<>();
		itensProtocolo = new DocumentosProtocolos();
		listaItensProtocolo = new ArrayList<>();	

	}

	
	public void preencherListaItensProtocolo(DocumentosProtocolos t) {
		this.itensProtocolo = t;
	}
	

	public void inativar(Protocolo t) {
		itensProtocoloService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjetoProtocolo();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		try {
			if (protocolo.getId() == null) {
				protocolo.setStatus(true);
				protocoloService.inserirAlterar(protocolo);
				for (DocumentosProtocolos docProtocolo : listaItensProtocolo) {
					docProtocolo.setProtocolo(protocolo);					
					itensProtocoloService.inserirAlterar(docProtocolo);
				}
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				criarNovoObjetoProtocolo();
			} else {
				
			}
			//carregarLista();

		} catch (Exception e) {
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			e.printStackTrace();
		}

	}

	public void salvarItem() {
		listaItensProtocolo.add(itensProtocolo);
		criarNovoObjetoItem();
	}

	public void excluirItem(DocumentosProtocolos item) {
		listaItensProtocolo.remove(item);
	}

	public void criarNovoObjetoProtocolo() {
		protocolo = new Protocolo();
	}

	public void criarNovoObjetoItem() {
		itensProtocolo = new DocumentosProtocolos();
	}

	public void carregarLista() {
		listaProtocolo = daoProtocolo.listaComStatus(Protocolo.class);
	}
	public void mostrarProtocolo(Protocolo t) {
		this.protocolo = t;
		String condicao="protocolo_id ="+protocolo.getId().toString();
		this.listaItensProtocolo = daoItens.listar(DocumentosProtocolos.class, condicao);
	}
	
	
	
	
	/*GET & SET*/	
	
	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public List<Protocolo> getProtocoloBusca() {
		return protocoloBusca;
	}

	public void setProtocoloBusca(List<Protocolo> protocoloBusca) {
		this.protocoloBusca = protocoloBusca;
	}

	public List<Protocolo> getListaProtocolo() {
		return listaProtocolo;
	}

	public void setListaProtocolo(List<Protocolo> listaProtocolo) {
		this.listaProtocolo = listaProtocolo;
	}

	public GenericDAO<Protocolo> getDaoUsuario() {
		return daoProtocolo;
	}

	public void setDaoUsuario(GenericDAO<Protocolo> daoUsuario) {
		this.daoProtocolo = daoUsuario;
	}

	public DocumentosProtocolos getItensProtocolo() {
		return itensProtocolo;
	}

	public void setItensProtocolo(DocumentosProtocolos itensProtocolo) {
		this.itensProtocolo = itensProtocolo;
	}

	public List<DocumentosProtocolos> getListaItensProtocolo() {
		return listaItensProtocolo;
	}

	public void setListaItensProtocolo(List<DocumentosProtocolos> listaItensProtocolo) {
		this.listaItensProtocolo = listaItensProtocolo;
	}

	public GenericDAO<Protocolo> getDaoProtocolo() {
		return daoProtocolo;
	}

	public void setDaoProtocolo(GenericDAO<Protocolo> daoProtocolo) {
		this.daoProtocolo = daoProtocolo;
	}

	public GenericDAO<DocumentosProtocolos> getDaoItens() {
		return daoItens;
	}

	public void setDaoItens(GenericDAO<DocumentosProtocolos> daoItens) {
		this.daoItens = daoItens;
	}

	public ItensProtocoloService getItensProtocoloService() {
		return itensProtocoloService;
	}

	public void setItensProtocoloService(ItensProtocoloService itensProtocoloService) {
		this.itensProtocoloService = itensProtocoloService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
