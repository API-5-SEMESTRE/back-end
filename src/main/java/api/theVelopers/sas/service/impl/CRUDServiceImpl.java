package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.service.CRUDService;

@Service
public class CRUDServiceImpl<T, ID> implements CRUDService<T, ID>{
	
	private JpaRepository<T, ID> repo;
	
	public List<T> procurarTodos() {
		return repo.findAll();
	}
	
	public Optional<T> procurarPorId(ID id) {
		return repo.findById(id);
	}
	
	public <S extends T> S salvar(S entidade) {
		return repo.save(entidade);
	}
	
	@Override
	public <S extends T> S salvarFlush(S entidade) {
		return repo.saveAndFlush(entidade);
	}
	
	public <S extends T> List<S> salvarTodos(Iterable<S> entidades) {
		return repo.saveAll(entidades);
	}
	
	@Override
	public <S extends T> List<S> salvarTodosFlush(Iterable<S> entidades) {
		return repo.saveAllAndFlush(entidades);
	}
	
	public void deletar(T entidade) {
		repo.delete(entidade);
	}
	
	public void deletarTodos(Iterable<T> entidades) {
		repo.flush();
		repo.deleteAllInBatch(entidades);
	}
}
