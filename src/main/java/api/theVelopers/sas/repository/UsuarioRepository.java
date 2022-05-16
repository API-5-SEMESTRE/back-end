package api.theVelopers.sas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findUsuarioByEmail(String email);
	
	@Query("select u from Usuario u where u.tipoAcesso = 'VENDEDOR'")
	List<Usuario> findAllVendedor();
}
