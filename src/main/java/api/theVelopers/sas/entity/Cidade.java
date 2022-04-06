package api.theVelopers.sas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cidade")
public class Cidade {
	
	public static final String ID = "cid_id";
	public static final String DESCRICAO = "cid_descricao";
	public static final String SIGLA_ESTADO = "cid_sigla_estado";
	public static final String REGISTRO_IBGE = "cid_reg_ibge";
	
	@Id
	@GeneratedValue
	@Column(name=ID)
	private Long id;
	
	@Column(name=DESCRICAO)
	private String descricao;
	
	@Column(name=SIGLA_ESTADO)
	private String siglaEstado;
	
	@Column(name=REGISTRO_IBGE)
	private String registroIbge;
	
	public Cidade() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getRegistroIbge() {
		return registroIbge;
	}

	public void setRegistroIbge(String registroIbge) {
		this.registroIbge = registroIbge;
	}
	
	
}
