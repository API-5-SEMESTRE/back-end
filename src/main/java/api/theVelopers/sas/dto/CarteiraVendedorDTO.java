package api.theVelopers.sas.dto;

import java.util.List;

import api.theVelopers.sas.entity.Empresa;

public class CarteiraVendedorDTO {
	
	private UsuarioDTO usuario;
	
	private List<Empresa> clientes;
	
	public CarteiraVendedorDTO() {}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<Empresa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Empresa> clientes) {
		this.clientes = clientes;
	}
}
