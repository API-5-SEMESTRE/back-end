package api.theVelopers.sas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
