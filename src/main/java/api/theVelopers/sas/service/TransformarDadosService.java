package api.theVelopers.sas.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Empresa;

public interface TransformarDadosService extends CRUDService<Empresa, Long>{
	
	Set<Cidade> carregarTransformarDados(MultipartFile arquivo);
}
