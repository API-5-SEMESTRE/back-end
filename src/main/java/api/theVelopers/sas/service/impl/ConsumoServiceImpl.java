package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.ConsumoDTO;
import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.repository.ConsumoRepository;
import api.theVelopers.sas.service.ConsumoService;

@Service
public class ConsumoServiceImpl implements ConsumoService{
	
	@Autowired
	private ConsumoRepository repo;

	@Override
	public List<Consumo> salvarTodos(Iterable<Consumo> consumos) {
		return repo.saveAll(consumos);
	}

	@Override
	public List<Consumo> salvarTodosFlush(Iterable<Consumo> consumos) {
		return repo.saveAllAndFlush(consumos);
	}

	@Override
	public Long procurarSomaConsumoPorEmpresa(Long cnpj) {
		return repo.procurarSomaConsumoPorEmpresa(cnpj);
	}

	@Override
	public Long procurarSomaConsumoPorEmpresaData(Long cnpj, LocalDateTime data) {
		return repo.procurarSomaConsumoPorEmpresaData(cnpj, data);
	}

	@Override
	public Long procurarSomaConsumoPorVendedor(Long id) {
		return repo.procurarPorSomaConsumoPorVendedor(id);
	}

	@Override
	public List<ConsumoDTO> procurarConsumosPorCnpj(Long cnpj) {
		return repo.findConsumoMesReferenciaDTOByEmpresaId_Named(cnpj);
	}

	@Override
	public List<ConsumoDTO> procurarConsumosPorIdVendedor(Long id) {
		return repo.findConsumoMesReferenciaDTOByUsuarioId_Named(id);
	}
}
