package api.theVelopers.sas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.theVelopers.sas.dto.ConsumoDTO;
import api.theVelopers.sas.entity.Consumo;


@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long>{
	
	@Query("select sum(c.consumoId.quantidadeConsumo) from Consumo c where c.consumoId.empresa.cnpj = ?1")
	Long procurarSomaConsumoPorEmpresa(Long cnpj);
	
	@Query("select sum(c.consumoId.quantidadeConsumo) from Consumo c where c.consumoId.empresa.cnpj = ?1"
			+ " and c.consumoId.mesReferencia > ?2")
	Long procurarSomaConsumoPorEmpresaData(Long cnpj, LocalDateTime data);
	
	@Query("select sum(c.consumoId.quantidadeConsumo) from Consumo c where c.consumoId.empresa.usuario.id = ?1"
			+ " and c.consumoId.mesReferencia >= c.consumoId.empresa.dataDeCadastroVendedor")
	Long procurarPorSomaConsumoPorVendedor(Long id);
	
	
	
	@Query(nativeQuery=true)
	List<ConsumoDTO> findConsumoMesReferenciaDTOByEmpresaId_Named(Long cnpj);
	
	@Query(nativeQuery=true)
	List<ConsumoDTO> findConsumoMesReferenciaDTOByUsuarioId_Named(Long id);
}