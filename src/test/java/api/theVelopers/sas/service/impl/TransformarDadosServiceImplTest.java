package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Cnae;
import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.service.TransformarDadosService;

@SpringBootTest
@Transactional
class TransformarDadosServiceImplTest {

	@Autowired
	private TransformarDadosService service;

	@Test
	@Rollback
	void carregarDadosCidadeDeveFuncionar() {
		//teste
		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_cidade_teste");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_cidade.csv", conteudo);
		
		Set<Cidade> cidades= service.transformarDadosCidade(arquivo);
		
		assertTrue(cidades.size() == 5);
	}
	
	@Test
	@Rollback
	void carregarDadosCnaeDeveFuncionar() {

		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_cnae_teste");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_cnae.csv", conteudo);

		Set<Cnae> cnaes= service.transformarDadosCnae(arquivo);
		
		assertTrue(cnaes.size() == 5);
	}
	
	@Test
	@Rollback
	void dadosCnaeDevemEstarCorretos() {

		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_cnae_teste");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_cnae.csv", conteudo);

		Set<Cnae> cnaes= service.transformarDadosCnae(arquivo);
		
		List<Cnae> cnaesL = new ArrayList<>(cnaes);
		
		assertTrue(StringUtils.equals(cnaesL.get(0).getDescricao(), "FABRICA????O DE APARELHOS E EQUIPAMENTOS DE MEDIDA, TESTE E CONTROLE"));
	}
	
	@Test
	@Rollback
	void carregarDadosEmpresaDeveFuncionar() {
		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_empresas_teste");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_empresas.csv", conteudo);

		Set<Empresa> cnaes= service.transformarDadosEmpresa(arquivo);
		
		assertTrue(cnaes.size() == 5);
	}
	
	@Test
	@Rollback
	void carregarDadosConsumoDeveFuncionar() {
		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_consumo_teste");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_consumo.csv2", conteudo);

		Set<Consumo> cnaes= service.transformarDadosConsumo(arquivo);
		
		assertTrue(cnaes.size() == 20);
	}

}