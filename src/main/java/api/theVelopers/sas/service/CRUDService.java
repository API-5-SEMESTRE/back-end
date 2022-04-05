package api.theVelopers.sas.service;

import java.util.List;
import java.util.Optional;


public interface CRUDService<T, ID> {
	
	List<T> procurarTodos();
	Optional<T> procurarPorId(ID id);
	<S extends T> S salvar(S entidade);
	<S extends T> List<S> salvarTodos(Iterable<S> entidades);
	void deletar(T entidade);
	void deletarTodos(Iterable<T> entidades);
}
