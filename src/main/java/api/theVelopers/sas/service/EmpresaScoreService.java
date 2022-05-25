package api.theVelopers.sas.service;

import org.springframework.data.domain.Page;

import api.theVelopers.sas.entity.EmpresaScore;

public interface EmpresaScoreService {
	Page<EmpresaScore> procurarPorRegiao(String regiao, int pagina, int tamanho, int sort);
	Page<EmpresaScore> procurarPorOrigem(String origem, int pagina, int tamanho, int sort);
	EmpresaScore procurarPorCnpj(Long cnpj);
	Page<EmpresaScore> procurarPorFiltroCompleto(String regiao, 
			String origem, String cnae, String estado, int pagina, int tamanho, int sort);
}
