package api.theVelopers.sas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.enumeration.TipoUsuario;

@Entity
@Table(name="usuario")
public class Usuario {
	
	public static final String ID = "usu_id";
	public static final String NOME = "usu_nome";
	public static final String SENHA = "usu_senha";
	public static final String EMAIL = "usu_email";
	public static final String TIPO_ACESSO = "usu_tipo_acesso";
	
	@Id
	@GeneratedValue(generator = "usuario_sequence")
	@SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1,
            initialValue = 0
    )
	@Column(name=ID)
	private Long id;
	
	@Column(name=NOME)
	private String nome;
	
	@Column(name=SENHA)
	private String senha;
	
	@Column(name=EMAIL)
	private String email;
	
	@Column(name=TIPO_ACESSO)
	private TipoUsuario tipoAcesso;
	
	public Usuario() {}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(TipoUsuario tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
	public static UsuarioDTO paraDTO(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setTipoAcesso(usuario.getTipoAcesso().toString());
		
		return dto;
	}
}
