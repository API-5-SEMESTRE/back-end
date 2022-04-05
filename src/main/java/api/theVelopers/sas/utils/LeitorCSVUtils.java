package api.theVelopers.sas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public abstract class LeitorCSVUtils {

	public static Map<String, List<String> > carregarDados(String nomeArquivo) {

		StringBuilder nomeCaminho = new StringBuilder();
		nomeCaminho.append("./uploads/");
		nomeCaminho.append(nomeArquivo);
		nomeCaminho.append(".csv");

		CsvParserSettings parserConfig = new CsvParserSettings();

		parserConfig.setLineSeparatorDetectionEnabled(true);

		RowListProcessor processadorLinha = new RowListProcessor();

		parserConfig.setProcessor(processadorLinha);

		parserConfig.setHeaderExtractionEnabled(true);
		
		parserConfig.setAutoClosingEnabled(true);

		CsvParser parser = new CsvParser(parserConfig);

		File arquivo = new File(nomeCaminho.toString());

		InputStream streamArq;
		try {
			streamArq = new FileInputStream(arquivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		parser.parse(streamArq);

		String[] headers = processadorLinha.getHeaders();
		List<String[]> linhas = processadorLinha.getRows();
		
		Map<String, List<String> > map = new HashMap<>();
		
		
		for(int i=0;i<headers.length;i++) {
			String coluna = headers[i];
			map.put(coluna, new ArrayList<String>());
			for(int j=0;j<linhas.get(i).length;j++) {
				map.get(coluna).add(linhas.get(i)[j]);
			}
		}
		
		return map;
	}

}
