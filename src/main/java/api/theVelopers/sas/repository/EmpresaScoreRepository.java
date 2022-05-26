package api.theVelopers.sas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.EmpresaScore;

@Repository
public interface EmpresaScoreRepository extends JpaRepository<EmpresaScore, Long> {

	@Query("select s from EmpresaScore s where s.regiao = ?1")
	Page<EmpresaScore> findByRegiao(String regiao, Pageable pageable);

	@Query("select s from EmpresaScore s where s.origem = ?1")
	Page<EmpresaScore> findByOrigem(String origem, Pageable pageable);

	@Query("select s from EmpresaScore s where "
			+ "s.regiao =?1 "
			+ "and ?2 is null "
			+ "and ?3 is null "
			+ "and ?4 is null "
			+ "or "
			+ "s.regiao=?1 "
			+ "and s.origem=?2 "
			+ "and ?3 is null "
			+ "and ?4 is null "
			+ "or "
			+ "s.regiao=?1 "
			+ "and s.origem=?2 "
			+ "and s.cnae like ?3 "
			+ "and ?4 is null "
			+ "or "
			+ "s.regiao=?1 "
			+ "and s.origem=?2 "
			+ "and s.cnae like ?3 "
			+ "and s.estado=?4 "
			+ "or "
			+ "s.regiao=?1 "
			+ "and ?2 is null "
			+ "and s.cnae like ?3 "
			+ "and s.estado=?4 "
			+ "or "
			+ "s.regiao=?1 "
			+ "and ?2 is null "
			+ "and ?3 is null "
			+ "and s.estado=?4 "
			+ "or "
			+ "s.regiao=?1 "
			+ "and s.origem=?2 "
			+ "and s.cnae like ?3 "
			+ "and ?4 is null "
			+ "or "
			+ "s.regiao=?1 "
			+ "and ?2 is null "
			+ "and s.cnae like ?3 "
			+ "and ?4 is null "
			+ "or "
			+ "s.regiao=?1 "
			+ "and ?2 is null "
			+ "and s.cnae like ?3 "
			+ "and s.estado=?4 "
			+ "or "
			+ "s.regiao=?1 "
			+ "and s.origem=?2 "
			+ "and ?3 is null "
			+ "and s.estado=?4")
	Page<EmpresaScore> procurarPorFiltroCompleto(String regiao, 
													String origem, String cnae, String estado, Pageable pageable);
}
