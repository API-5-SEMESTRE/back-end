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

public abstract class LeitorCSVUtils {

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
		
		List<String[]> linhasFormatadas = new ArrayList<>();
		
		for(String[] l: linhas) {
			String temp = StringUtils.join(l);
			temp = temp.replaceAll("\"", "");
			String[] resultado = temp.split(",");
			linhasFormatadas.add(resultado);
		}
		
		return linhasFormatadas;
	}

}
