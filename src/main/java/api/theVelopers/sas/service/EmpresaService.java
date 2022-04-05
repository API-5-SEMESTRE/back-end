package api.theVelopers.sas.service;

import java.util.List;
import java.util.Map;

import api.theVelopers.sas.entity.Empresa;

public interface EmpresaService extends CRUDService<Empresa, Long>{

	Map<String, List<String>> carregarDadosEmpresa(String nomeArquivo);
}
