package api.theVelopers.sas.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;

public interface CidadeService extends CRUDService<Cidade, Long>{
	
	Map<String, List<String>> carregarDadosEmpresa(MultipartFile arquivo);

}
