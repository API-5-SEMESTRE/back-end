package api.theVelopers.sas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	List<Empresa> findByUsuario(Usuario vendedor);
}