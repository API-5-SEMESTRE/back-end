package api.theVelopers.sas.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

/**
 * 
 * @author jef
 *
 */
public abstract class LeitorCSVUtils {
	
	/**
	* Esse método carrega qualquer tipo de dado de um arquivo csv
	* o csv deve estar em UTF-8!!!
	*
	* @param arquivo .csv
	* @return List Retorna uma lista de array de String
	* contendo cada linha do arquivo 
	*/
	public static List<String[]>  carregarDados(MultipartFile arquivo) {

		CsvParserSettings parserConfig = new CsvParserSettings();

		parserConfig.setLineSeparatorDetectionEnabled(true);

		RowListProcessor processadorLinha = new RowListProcessor();

		parserConfig.setProcessor(processadorLinha);

		parserConfig.setHeaderExtractionEnabled(true);
		
		parserConfig.setAutoClosingEnabled(true);
		
		parserConfig.detectFormatAutomatically();

		CsvParser parser = new CsvParser(parserConfig);

		try {
			parser.parse(arquivo.getInputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			 throw new RuntimeException("Erro ao ler arquivo.");
		} finally {
			try {
				arquivo.getInputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
				 throw new RuntimeException("Erro ao fechar arquivo");
			}
		}
		
		
		List<String[]> linhas = processadorLinha.getRows();
		
		if(!StringUtils.join(linhas.get(0)).contains("\"")) {
			return linhas;
		}
		
		List<String[]> linhasFormatadas = new ArrayList<>();
		
		for(String[] l: linhas) {
			String temp = StringUtils.join(l);
			
			char[] linha = temp.toCharArray();
			boolean flag = false;
			for(int i=0;i<linha.length;i++) {
				if(linha[i] ==  '\"') {
					if(flag) {
						flag = false;
					} else {
						flag = true;
					}
					linha[i] = '!';
				} else if(linha[i] == ',') {
					if(!flag) {
						linha[i] = ';';
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for(char c: linha) {
				sb.append(c);
			}
			String temp3 = sb.toString().replaceAll("!", "");
			String[] resultado = temp3.split(";");
			if(!StringUtils.isBlank(temp3)) {
				linhasFormatadas.add(resultado);
			}
		}
		
		return linhasFormatadas;
	}	
}
