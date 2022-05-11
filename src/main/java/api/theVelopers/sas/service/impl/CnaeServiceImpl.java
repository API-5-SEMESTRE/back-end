package api.theVelopers.sas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Cnae;
import api.theVelopers.sas.repository.CnaeRepository;
import api.theVelopers.sas.service.CnaeService;

/**
 * 
 * @author jef
 *
 */
@Service
public class CnaeServiceImpl implements CnaeService{
	
	@Autowired
	private CnaeRepository repo;
	
	@Override
	public List<Cnae> salvarTodos(Iterable<Cnae> cnaes) {
		return repo.saveAll(cnaes);
	}

	@Override
	public List<Cnae> salvarTodosFlush(Iterable<Cnae> cnaes) {
		return repo.saveAllAndFlush(cnaes);
	}

}
