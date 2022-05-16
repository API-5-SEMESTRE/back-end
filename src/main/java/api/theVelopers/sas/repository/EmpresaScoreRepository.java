package api.theVelopers.sas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.EmpresaScore;

@Repository
public interface EmpresaScoreRepository extends JpaRepository<EmpresaScore, Long>{
	
	@Query("select s from EmpresaScore s where s.regiao = ?1")
	Page<EmpresaScore> findByRegiao(String regiao, Pageable pageable);
	
	@Query("select s from EmpresaScore s where s.origem = ?1")
	Page<EmpresaScore> findByOrigem(String origem, Pageable pageable);
}
