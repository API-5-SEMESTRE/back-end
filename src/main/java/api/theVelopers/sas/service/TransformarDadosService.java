package api.theVelopers.sas.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Cnae;
import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.entity.Empresa;

public interface TransformarDadosService{
	
	Set<Cidade> transformarDadosCidade(MultipartFile arquivo);

	Set<Consumo> transformarDadosConsumo(MultipartFile arquivo);

	Set<Empresa> transformarDadosEmpresa(MultipartFile arquivo);

	Set<Cnae> transformarDadosCnae(MultipartFile arquivo);
}
