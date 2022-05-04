package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.entity.CarteiraVendedor;

public interface CarteiraVendedorService {

	void adicionarVendedorEmpresa(Long idUsuario, Long cnpjEmpresa);
	CarteiraVendedor criarCarteiraVendedor(Long idVendedor);
	List<CarteiraVendedor> procurarMelhoresVendedores();
	void removerVendedorEmpresa(Long cnpjEmpresa);
}
