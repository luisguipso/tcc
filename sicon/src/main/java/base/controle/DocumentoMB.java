package base.controle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Documento;
import base.service.DocumentoService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("documentoMB")
public class DocumentoMB {	
		
		private static final long serialVersionUID = 1L;


		private Documento documento;
		private List<Documento> documentoBusca;
		private List<Documento> listaDocumento;
		private String senhaConfirmacao;
			
		@Inject
		private GenericDAO<Documento> daoDocumento; //faz as buscas
		
		@Inject
		private DocumentoService documentoService; // inserir no banco
		
		
		@PostConstruct
		public void inicializar() {
		
			documento = new Documento();
		
			listaDocumento = new ArrayList<>();
			listaDocumento = daoDocumento.listaComStatus(Documento.class);
			documentoBusca = new ArrayList<>();
			
		}

		public void preencherListaDocumento(Documento t) {
			this.documento = t;

		}

		public void inativarDocumento(Documento t) {
			documentoService.update(" Tipo set status = false where id = " + t.getId());
			criarNovoObjeto();
			ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
			carregarLista();
		}

		public void salvar() {
			try {			
					
					if (documento.getId() == null) {
						documento.setStatus(true);
						documentoService.inserirAlterar(documento);
						

					} else {
						documento.setStatus(true);
						documentoService.inserirAlterar(documento);
						
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
			documento = new Documento();
		}

		public void carregarLista() {
			listaDocumento = daoDocumento.listaComStatus(Documento.class);
		}

		/*Getters & Setters*/
		
		
		public Documento getDocumento() {
			return documento;
		}

		public void setDocumento(Documento documento) {
			this.documento = documento;
		}

		public List<Documento> getDocumentoBusca() {
			return documentoBusca;
		}

		public void setDocumentoBusca(List<Documento> documentoBusca) {
			this.documentoBusca = documentoBusca;
		}

		public List<Documento> getListaDocumento() {
			return listaDocumento;
		}

		public void setListaDocumento(List<Documento> listaDocumento) {
			this.listaDocumento = listaDocumento;
		}

		public String getSenhaConfirmacao() {
			return senhaConfirmacao;
		}

		public void setSenhaConfirmacao(String senhaConfirmacao) {
			this.senhaConfirmacao = senhaConfirmacao;
		}

		public GenericDAO<Documento> getDaoDocumento() {
			return daoDocumento;
		}

		public void setDaoDocumento(GenericDAO<Documento> daoDocumento) {
			this.daoDocumento = daoDocumento;
		}

		public DocumentoService getDocumentoService() {
			return documentoService;
		}

		public void setDocumentoService(DocumentoService documentoService) {
			this.documentoService = documentoService;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
	}

