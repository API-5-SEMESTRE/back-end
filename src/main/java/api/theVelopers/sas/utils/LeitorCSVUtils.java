package api.theVelopers.sas.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		CsvParser parser = new CsvParser(parserConfig);

		//File arquivo = new File(nomeCaminho.toString());

		/*
		 * InputStream streamArq; try { streamArq = new
		 * FileInputStream(arquivo.getInputStream()); } catch (FileNotFoundException e)
		 * { e.printStackTrace(); throw new RuntimeException(e); }
		 */

		try {
			parser.parse(arquivo.getInputStream());
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
		
		
		String[] headers = processadorLinha.getHeaders();
		List<String[]> linhas = processadorLinha.getRows();
		
		Map<String, List<String> > map = new HashMap<>();
		
		
		/*
		 * for(int i=0;i<headers.length;i++) { String coluna = headers[i];
		 * map.put(coluna, new ArrayList<String>()); for(int j=0;j<linhas.size();j++) {
		 * map.get(coluna).add(linhas.get(j)[i]); } }
		 */
		
		return linhas;
	}

}
