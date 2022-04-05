package api.theVelopers.sas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.Cnae;

@Repository
public interface CnaeRepository extends JpaRepository<Cnae, Long>{

}