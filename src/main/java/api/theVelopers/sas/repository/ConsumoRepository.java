package api.theVelopers.sas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.entity.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long>{

}