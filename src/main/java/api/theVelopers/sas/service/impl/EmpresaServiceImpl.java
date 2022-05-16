package api.theVelopers.sas.service.impl;

import static api.theVelopers.sas.constant.MensagemErroConstant.NENHUM_RESULTADO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.exception.NegocioException;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.EmpresaService;

/**
 * 
 * @author jef
 *
 */
@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Override
	public List<Empresa> findByUsuario(Usuario vendedor) {
		
		List<Empresa> empresas = empresaRepo.findByUsuario(vendedor);
		
		return empresas;
	}

	@Override
	public List<Empresa> salvarTodos(Iterable<Empresa> empresas) {
		return empresaRepo.saveAll(empresas);
	}

	@Override
	public List<Empresa> salvarTodosFlush(Iterable<Empresa> empresas) {
		return empresaRepo.saveAllAndFlush(empresas);
	}

	@Override
	public List<EmpresaDTO> procurarTodos() {
		List<Empresa> empresas = empresaRepo.findAll();
		return empresas.stream().map(Empresa::paraDTO).collect(Collectors.toList());
	}

	@Override
	public EmpresaDTO procurarPorCnpj(Long cnpj) {
		Empresa empresa = empresaRepo.getById(cnpj);
		
		if(empresa == null) {
			throw new NegocioException(NENHUM_RESULTADO);
		}
		
		return Empresa.paraDTO(empresa);
	}

	@Override
	public List<Long> findAllCnpj() {
		return empresaRepo.findAllCnpj();
	}
	
	@Override
	public Page<Empresa> todasEmpresas(int pagina, int tamanho) {
		Page<Empresa> empresas = empresaRepo.findAll(PageRequest.of(pagina, tamanho));
		return empresas;
	}

}
