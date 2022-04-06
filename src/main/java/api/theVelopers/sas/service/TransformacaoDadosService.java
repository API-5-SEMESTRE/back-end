package api.theVelopers.sas.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Empresa;

public interface TransformacaoDadosService extends CRUDService<Empresa, Long>{
	
	List<String[]> carregarTransformarDados(MultipartFile arquivo);
}
