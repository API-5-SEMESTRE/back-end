package api.theVelopers.sas.entity;

/**
 * 
 * @author jef
 *
 */
import java.util.List;

import api.theVelopers.sas.dto.EmpresaCarteiraDTO;
import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.enumeration.SenioridadeVendedor;


public class CarteiraVendedor {
	
	private UsuarioDTO vendedor;
	
	private List<EmpresaCarteiraDTO> clientes;
	
	private Long score;
	
	private SenioridadeVendedor senioridade;
	
	public CarteiraVendedor() {}

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
	}


	public List<EmpresaCarteiraDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<EmpresaCarteiraDTO> clientes) {
		this.clientes = clientes;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public SenioridadeVendedor getSenioridade() {
		return senioridade;
	}

	public void setSenioridade(SenioridadeVendedor senioridade) {
		this.senioridade = senioridade;
	}
	
	
}	
