package api.theVelopers.sas.service;

import api.theVelopers.sas.entity.CarteiraVendedor;

public interface CarteiraVendedorService {

	void adicionarVendedorEmpresa(Long idUsuario, Long cnpjEmpresa);

	CarteiraVendedor criarCarteiraVendedor(Long idVendedor);

	void removerVendedorEmpresa(Long cnpjEmpresa);

}
