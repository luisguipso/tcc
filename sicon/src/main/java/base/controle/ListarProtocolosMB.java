package base.controle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Protocolo;
import base.service.ProtocoloService;
import dao.GenericDAO;

@ViewScoped
@Named("listarProtocolosMB")
public class ListarProtocolosMB {	
		
		private static final long serialVersionUID = 1L;


		private Protocolo protocolo;
		private List<Protocolo> listaProtocolo;
		private String senhaConfirmacao;
			
		@Inject
		private GenericDAO<Protocolo> daoProtocolo; //faz as buscas
		
		@Inject
		private ProtocoloService protocoloService; // inserir no banco
		
		
		@PostConstruct
		public void inicializar() {
		
			protocolo = new Protocolo();
		
			listaProtocolo = new ArrayList<>();
			listaProtocolo = daoProtocolo.listaComStatus(Protocolo.class);
			
			
		}

		public void preencherListaDocumento(Protocolo t) {
			this.protocolo = t;

		}


		public void carregarLista() {
			listaProtocolo = daoProtocolo.listaComStatus(Protocolo.class);
		}

		
		/*Getters & Setters*/
		
		public Protocolo getProtocolo() {
			return protocolo;
		}

		public void setProtocolo(Protocolo protocolo) {
			this.protocolo = protocolo;
		}

		public List<Protocolo> getListaProtocolos() {
			return listaProtocolo;
		}

		public void setListaProtocolos(List<Protocolo> listaProtocolos) {
			this.listaProtocolo = listaProtocolos;
		}

		public String getSenhaConfirmacao() {
			return senhaConfirmacao;
		}

		public void setSenhaConfirmacao(String senhaConfirmacao) {
			this.senhaConfirmacao = senhaConfirmacao;
		}

		public GenericDAO<Protocolo> getDaoProtocolo() {
			return daoProtocolo;
		}

		public void setDaoProtocolo(GenericDAO<Protocolo> daoProtocolo) {
			this.daoProtocolo = daoProtocolo;
		}

		public ProtocoloService getProtocoloService() {
			return protocoloService;
		}

		public void setProtocoloService(ProtocoloService protocoloService) {
			this.protocoloService = protocoloService;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
		
		
		
	}

