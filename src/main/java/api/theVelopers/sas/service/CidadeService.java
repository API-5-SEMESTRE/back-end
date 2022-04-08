package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.entity.Cidade;

public interface CidadeService{
	
	List<Cidade> salvarTodos(Iterable<Cidade> cidades);
	List<Cidade> salvarTodosFlush(Iterable<Cidade> cidades);
}
