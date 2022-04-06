package api.theVelopers.sas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cane")
public class Cnae {
	
	public static final String ID="cnae_id";
	public static final String CODIGO="cnae_cod";
	public static final String DESCRICAO="cnae_desc";
	
	@Id
	@GeneratedValue
	@Column(name=ID)
	private Long id;
	
	@Column(name=CODIGO)
	private Long codigo;
	
	@Column(name=DESCRICAO)
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
