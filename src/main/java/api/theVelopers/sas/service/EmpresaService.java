package api.theVelopers.sas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;

public interface EmpresaService{
	
	List<Empresa> findByUsuario(Usuario vendedor);
	EmpresaDTO procurarPorCnpj(Long cnpj);
	List<Empresa> salvarTodos(Iterable<Empresa> empresas);
	List<Empresa> salvarTodosFlush(Iterable<Empresa> empresas);
	List<EmpresaDTO> procurarTodos();
	List<Long> findAllCnpj();
	Page<Empresa> todasEmpresas(int pagina, int tamanho);
}
