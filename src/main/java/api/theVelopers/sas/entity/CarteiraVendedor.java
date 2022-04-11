package api.theVelopers.sas.entity;

import java.util.List;


public class CarteiraVendedor {
	
	private Usuario vendedor;
	
	private List<Empresa> clientes;
	
	private Long score;
	
	public CarteiraVendedor() {}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
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
