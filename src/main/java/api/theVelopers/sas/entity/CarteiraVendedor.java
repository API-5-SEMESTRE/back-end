package api.theVelopers.sas.entity;

import java.util.List;

import api.theVelopers.sas.dto.UsuarioDTO;


public class CarteiraVendedor {
	
	private UsuarioDTO vendedor;
	
	private List<Empresa> clientes;
	
	private Long score;
	
	public CarteiraVendedor() {}

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
	}

	public List<Empresa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Empresa> clientes) {
		this.clientes = clientes;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
}	
