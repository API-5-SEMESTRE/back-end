package api.theVelopers.sas.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.enumeration.TipoEmpresa;

/**
 * 
 * @author jef
 *
 */
@Entity
@Table(name="empresa")
public class Empresa {
	
	
	public static final String ID = "emp_cnpj";
	public static final String ORIGEM="emp_origem";
	public static final String DATA_CADASTRO_VENDEDOR="emp_data_cadastro_vendedor";
	
	@Id
	@Column(name=ID)
	private Long cnpj;
	
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
	private TipoEmpresa origem;
	
	@Column(name=DATA_CADASTRO_VENDEDOR)
	private LocalDateTime dataDeCadastroVendedor;
	
	@OneToOne
	@JoinColumn(
			name=Usuario.ID,
			referencedColumnName=Usuario.ID,
			nullable = true
			)
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa() {}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
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
	
	public static EmpresaDTO paraDTO(Empresa empresa) {
		EmpresaDTO dto = new EmpresaDTO();
		dto.setCnpj(empresa.getCnpj());
		
		return dto;
	}
}
