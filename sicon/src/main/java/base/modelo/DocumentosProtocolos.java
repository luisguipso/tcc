package base.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
@Entity
@Table(name = "documentosprotocolos")
public class DocumentosProtocolos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double valor;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date vencimento;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date competencia;
	private boolean devolvido;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataDevolucao;
	@ManyToOne
	private Usuario responsavelDevolucao;
	private int codRecolhimento;
	private String observacao;
	@ManyToOne
	private Documento documento;
	@ManyToOne
	private Protocolo protocolo;
	
	/**************************	GETTERS & SETTERS**************************/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Date getCompetencia() {
		return competencia;
	}
	public void setCompetencia(Date competencia) {
		this.competencia = competencia;
	}
	public int getCod_recolhimento() {
		return codRecolhimento;
	}
	public void setCod_recolhimento(int codRecolhimento) {
		this.codRecolhimento = codRecolhimento;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public Protocolo getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}
	
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public int getCodRecolhimento() {
		return codRecolhimento;
	}
	public void setCodRecolhimento(int codRecolhimento) {
		this.codRecolhimento = codRecolhimento;
	}
	public Usuario getResponsavelDevolucao() {
		return responsavelDevolucao;
	}
	public void setResponsavelDevolucao(Usuario responsavelDevolucao) {
		this.responsavelDevolucao = responsavelDevolucao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public boolean isDevolvido() {
		return devolvido;
	}
	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codRecolhimento;
		result = prime * result + ((competencia == null) ? 0 : competencia.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((protocolo == null) ? 0 : protocolo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentosProtocolos other = (DocumentosProtocolos) obj;
		if (codRecolhimento != other.codRecolhimento)
			return false;
		if (competencia == null) {
			if (other.competencia != null)
				return false;
		} else if (!competencia.equals(other.competencia))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (protocolo == null) {
			if (other.protocolo != null)
				return false;
		} else if (!protocolo.equals(other.protocolo))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
	
}
