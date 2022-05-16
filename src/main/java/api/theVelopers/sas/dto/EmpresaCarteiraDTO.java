package api.theVelopers.sas.dto;

import api.theVelopers.sas.enumeration.TipoEmpresa;

public class EmpresaCarteiraDTO {

	private Long cnpj;
	private String dataDeCadastroVendedor;
	private TipoEmpresa origem;
	private String cidade;
	private String cnae;

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataDeCadastroVendedor() {
		return dataDeCadastroVendedor;
	}

	public void setDataDeCadastroVendedor(String dataDeCadastroVendedor) {
		this.dataDeCadastroVendedor = dataDeCadastroVendedor;
	}

	public TipoEmpresa getOrigem() {
		return origem;
	}

	public void setOrigem(TipoEmpresa origem) {
		this.origem = origem;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	
}
