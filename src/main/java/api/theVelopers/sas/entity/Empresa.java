package api.theVelopers.sas.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CNPJ;

import api.theVelopers.sas.enumeration.TipoEmpresa;

@Entity
@Table(name="empresa")
public class Empresa {
	
	
	public static final String ID = "emp_cnpj";
	public static final String CIDADE="cidade_cid_id";
	public static final String CNAE="cnae_cnae_id";
	public static final String ORIGEM="emp_origem";
	public static final String VENDEDOR="vendedor_usu_id";
	public static final String DATA_CADASTRO_VENDEDOR="emp_data_cadastro_vendedor";
	
	@Id
	@CNPJ
	@Column(name=ID)
	private String cnpj;
	
	@ManyToOne
	@JoinColumn(
				name=Cidade.ID
	)
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(
				name=Cnae.ID
	)
	private Cnae cnae;
	
	@Column(name=ORIGEM)
	@Enumerated(EnumType.ORDINAL)
	private TipoEmpresa origem;
	
	@Column(name=DATA_CADASTRO_VENDEDOR)
	private LocalDateTime dataDeCadastroVendedor;
	
	public Empresa() {}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public TipoEmpresa getOrigem() {
		return origem;
	}

	public void setOrigem(TipoEmpresa origem) {
		this.origem = origem;
	}

	public LocalDateTime getDataDeCadastroVendedor() {
		return dataDeCadastroVendedor;
	}

	public void setDataDeCadastroVendedor(LocalDateTime dataDeCadastroVendedor) {
		this.dataDeCadastroVendedor = dataDeCadastroVendedor;
	}
}
