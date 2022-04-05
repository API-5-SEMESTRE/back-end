package api.theVelopers.sas.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class CarteiraVendedor {
	
	private Usuario vendedor;
	
	private Set<Empresa> clientes;
	
	public CarteiraVendedor() {}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Set<Empresa> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Empresa> clientes) {
		this.clientes = clientes;
	}
}	
