package api.theVelopers.sas.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Empresa;

public interface EmpresaService extends CRUDService<Empresa, Long>{

	Map<String, List<String>> carregarDadosEmpresa(MultipartFile arquivo);
}
