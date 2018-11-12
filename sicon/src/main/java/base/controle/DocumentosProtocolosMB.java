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
import base.modelo.DocumentosProtocolos;
import base.modelo.Protocolo;
import base.service.ItensProtocoloService;
import base.service.ProtocoloService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.ExibirMensagem;
import util.Mensagem;


@ViewScoped
@Named("documentosProtocoloMB")
public class DocumentosProtocolosMB {
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private boolean relatorioDevolvido;
	
	private List<DocumentosProtocolos> listaItensProtocolo;
	private List<DocumentosProtocolos> listaItensProtocoloMostrar; //para buscas
	@Inject
	private GenericDAO<DocumentosProtocolos> daoItens;

	@Inject
	private ItensProtocoloService itensProtocoloService; // inserir no banco

	
	@Inject
	private EntityManager manager;
	
	@Inject
	private Protocolo protocolo;
	
	@Inject
	private DocumentosProtocolos itensProtocolo;
	
	@PostConstruct
	public void inicializar() {
		listaItensProtocolo = new ArrayList<>();	
		listaItensProtocolo = daoItens.lista(DocumentosProtocolos.class);
		listaItensProtocoloMostrar = new ArrayList<>();
		relatorioDevolvido = true;
		cliente = new Cliente();
		//preenche o protocolo do item pra passar o cliente como parametro pro relatorio
		itensProtocolo.setProtocolo(protocolo);

	}
	
	
	public void devolverItem(DocumentosProtocolos i) {
		System.out.println(String.valueOf(i.getValor()));
		if(i.isDevolvido()==true) {
			System.out.println("no if");
			ExibirMensagem.exibirMensagem(Mensagem.ITEM_JA_DEVOLVIDO);
			
		}else {
			System.out.println("no else");
			i.setDevolvido(true);
			
			Date data = new Date();
			i.setDataDevolucao(data);
			
			itensProtocoloService.inserirAlterar(i);
			ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
			
		}
	}
	
	public void mostrarDocumentoProtocolo(DocumentosProtocolos i) {
		listaItensProtocoloMostrar = new ArrayList<>();
		listaItensProtocoloMostrar.add(i);
		
	}
	
	public void verificaTipoRelatorio(boolean relatorioDocumentoDevolvido,boolean temParametro) {
		
		
		long cidadeId = 0;
		HashMap parametro = new HashMap<>();
		String caminhoDoRelatorio = "";
		if(relatorioDocumentoDevolvido) { //se é relatorio de documentos devolvidos
			caminhoDoRelatorio = "documentosDevolvidos.jasper";
			if(temParametro) {
				parametro.put("idCliente", itensProtocolo.getProtocolo().getCliente().getId());
				caminhoDoRelatorio = "documentosDevolvidosUnicoCliente.jasper";
			}
		}else {
			caminhoDoRelatorio = "documentosNaoDevolvidos.jasper";
			if(temParametro) {
				parametro.put("idCliente", itensProtocolo.getProtocolo().getCliente().getId());
				caminhoDoRelatorio = "documentosNaoDevolvidosUnicoCliente.jasper";
			}
		}
		
		
		
		imprimirRelatorioDocumentos(parametro, caminhoDoRelatorio);
	}
	
	public void criarNovoObjetoItem() {
		itensProtocolo = new DocumentosProtocolos();
	}
	
	public void imprimirRelatorioDocumentos(HashMap parametro, String caminho) { 
		try {
			List<DocumentosProtocolos> relatorio = daoItens.listar(DocumentosProtocolos.class, "id != 0");
			if (relatorio.size() > 0) {

				ChamarRelatorio ch = new ChamarRelatorio(caminho, parametro, "documentos_devolvidos");
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


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public boolean isRelatorioDevolvido() {
		return relatorioDevolvido;
	}


	public void setRelatorioDevolvido(boolean relatorioDevolvido) {
		this.relatorioDevolvido = relatorioDevolvido;
	}


	public List<DocumentosProtocolos> getListaItensProtocolo() {
		return listaItensProtocolo;
	}


	public void setListaItensProtocolo(List<DocumentosProtocolos> listaItensProtocolo) {
		this.listaItensProtocolo = listaItensProtocolo;
	}


	public List<DocumentosProtocolos> getListaItensProtocoloMostrar() {
		return listaItensProtocoloMostrar;
	}


	public void setListaItensProtocoloMostrar(List<DocumentosProtocolos> listaItensProtocoloMostrar) {
		this.listaItensProtocoloMostrar = listaItensProtocoloMostrar;
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


	public EntityManager getManager() {
		return manager;
	}


	public void setManager(EntityManager manager) {
		this.manager = manager;
	}


	public Protocolo getProtocolo() {
		return protocolo;
	}


	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}


	public DocumentosProtocolos getItensProtocolo() {
		return itensProtocolo;
	}


	public void setItensProtocolo(DocumentosProtocolos itensProtocolo) {
		this.itensProtocolo = itensProtocolo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	


	
	
}
