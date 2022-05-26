package api.theVelopers.sas.service.impl;

import static api.theVelopers.sas.constant.MensagemErroConstant.NENHUM_RESULTADO;
import static api.theVelopers.sas.constant.MensagemErroConstant.FILTRO_REGIAO_NAO_PODE_SER_NULL;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.EmpresaScore;
import api.theVelopers.sas.exception.NegocioException;
import api.theVelopers.sas.repository.EmpresaScoreRepository;
import api.theVelopers.sas.service.EmpresaScoreService;

/**
 * 
 * @author jef
 *
 */
@Service
public class EmpresaScoreServiceImpl implements EmpresaScoreService{
	
	@Autowired
	private EmpresaScoreRepository repo;

	@Override
	public Page<EmpresaScore> procurarPorRegiao(String regiao, int pagina, int tamanho, int sort) {
		PageRequest request = montarPagina(pagina, tamanho, sort);
		Page<EmpresaScore> scores = repo.findByRegiao(regiao, request);
		
		return scores;
	}

	@Override
	public Page<EmpresaScore> procurarPorOrigem(String origem, int pagina, int tamanho, int sort) {
		PageRequest request = montarPagina(pagina, tamanho, sort);
		Page<EmpresaScore> scores = repo.findByOrigem(origem, request);
		return scores;
	}
	
	private PageRequest montarPagina(int pagina, int tamanho, int sort) {
		if(sort == 1) {
			return PageRequest.of(--pagina, tamanho, Sort.by("totalScore").ascending());
		}
		return PageRequest.of(--pagina, tamanho, Sort.by("totalScore").descending());
	}

	@Override
	public EmpresaScore procurarPorCnpj(Long cnpj) {
		
		Optional<EmpresaScore> score = repo.findById(cnpj);
		
		if(score.isEmpty()) {
			throw new NegocioException(NENHUM_RESULTADO);
		}
		
		return score.get();
	}

	@Override
	public Page<EmpresaScore> procurarPorFiltroCompleto(String regiao, String origem, String cnae, String estado,
			int pagina, int tamanho, int sort) {
		PageRequest request = montarPagina(pagina, tamanho, sort);
		if(checarCampo(regiao) == null) {
			throw new NegocioException(FILTRO_REGIAO_NAO_PODE_SER_NULL);
		}
		origem = checarCampo(origem);
		cnae = checarCampo(cnae);
		estado = checarCampo(estado);
		if(StringUtils.isNotBlank(cnae)) {
			cnae = "%" + cnae + "%";
		}
		Page<EmpresaScore> scores = repo.procurarPorFiltroCompleto(regiao, origem, cnae, estado, request);
		return scores;
	}
	
	private String checarCampo(String campo) {
		return StringUtils.isBlank(campo) ? null:campo;
	}
}
