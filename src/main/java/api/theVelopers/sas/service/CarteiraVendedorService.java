package api.theVelopers.sas.service;

import java.util.Map;

import api.theVelopers.sas.entity.CarteiraVendedor;

public interface CarteiraVendedorService {

	void adicionarVendedorEmpresa(Long idUsuario, Long cnpjEmpresa);
	CarteiraVendedor criarCarteiraVendedor(Long idVendedor);
	Map<String, Long> procurarMelhoresVendedores();
	void removerVendedorEmpresa(Long cnpjEmpresa);
}
