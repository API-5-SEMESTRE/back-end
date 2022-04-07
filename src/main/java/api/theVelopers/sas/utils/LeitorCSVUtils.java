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

		/*
		 * StringBuilder nomeCaminho = new StringBuilder();
		 * nomeCaminho.append("./uploads/"); nomeCaminho.append(nomeArquivo);
		 * nomeCaminho.append(".csv");
		 */

		CsvParserSettings parserConfig = new CsvParserSettings();

		parserConfig.setLineSeparatorDetectionEnabled(true);

		RowListProcessor processadorLinha = new RowListProcessor();

		parserConfig.setProcessor(processadorLinha);

		parserConfig.setHeaderExtractionEnabled(true);
		
		parserConfig.setAutoClosingEnabled(true);
		
		parserConfig.detectFormatAutomatically();

		CsvParser parser = new CsvParser(parserConfig);

		//File arquivo = new File(nomeCaminho.toString());

		/*
		 * InputStream streamArq; try { streamArq = new
		 * FileInputStream(arquivo.getInputStream()); } catch (FileNotFoundException e)
		 * { e.printStackTrace(); throw new RuntimeException(e); }
		 */
		
		
		

		try {
			parser.parse(arquivo.getInputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			 throw new RuntimeException(e);
		} finally {
			try {
				arquivo.getInputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
				 throw new RuntimeException(e);
			}
		}
		
		
		List<String[]> linhas = processadorLinha.getRows();
		
		Map<String, List<String> > map = new HashMap<>();
		
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
