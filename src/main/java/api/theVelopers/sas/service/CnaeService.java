package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.entity.Cnae;

public interface CnaeService {
	List<Cnae> salvarTodos(Iterable<Cnae> cnaes);
	List<Cnae> salvarTodosFlush(Iterable<Cnae> cnaes);
}
