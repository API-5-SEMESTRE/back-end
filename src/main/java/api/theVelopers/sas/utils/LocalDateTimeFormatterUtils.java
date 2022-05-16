package api.theVelopers.sas.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author jef
 *
 */
public class LocalDateTimeFormatterUtils {

	public static LocalDateTime padronizarLocalDateTime(LocalDateTime data) {

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = data.format(formatador);

		LocalDateTime dataPadronizada = LocalDateTime.parse(dataFormatada, formatador);

		return dataPadronizada;
	}

	public static LocalDateTime padronizarLocalDateTime(String data) {

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dataPadronizada = LocalDateTime.parse(data, formatador);

		return dataPadronizada;
	}

}
