package api.theVelopers.sas.dto;

public class UsuarioDTO {
	
	private Long id;
	
	private String nome;
	
	private String tipoAcesso;
	
	public UsuarioDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
}
