package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.service.TransformarDadosService;

@SpringBootTest
class TransformarDadosServiceImplTest {

	@Autowired
	private TransformarDadosService service;

	@Test
	void carregarDadosEmpresaDeveFuncionar() {

		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append("base_cidade");
		nomeCaminho.append(".csv");

		Path caminho = Paths.get(nomeCaminho.toString());

		byte[] conteudo = null;

		try {
			conteudo = Files.readAllBytes(caminho);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MultipartFile arquivo = new MockMultipartFile("base_cidade.csv", conteudo);

		Set<Cidade> cidades= service.carregarTransformarDados(arquivo);

		assertTrue(!cidades.isEmpty());
	}

}