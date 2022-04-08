package api.theVelopers.sas.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import api.theVelopers.sas.enumeration.TipoUsuario;

import static api.theVelopers.sas.enumeration.TipoUsuario.*;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, String>{

	@Override
	public String convertToDatabaseColumn(TipoUsuario tipo) {

		if(tipo == null) {
			return null;
		}
		
		switch(tipo) {
		case ADMINISTRADOR:
			return "ADMINISTRADOR";
		
		case VENDEDOR:
			return "VENDEDOR";
		
		case INTELIGENCIA:
			return "INTELIGENCIA";
		
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public TipoUsuario convertToEntityAttribute(String dbData) {
		if(dbData == null) {
			return null;
		}
		
		switch(dbData) {
		case "ADIMINISTRADOR":
			return ADMINISTRADOR;
		
		case "VENDEDOR":
			return VENDEDOR;
		
		case "INTELIGENCIA":
			return INTELIGENCIA;
		
		default:
			throw new IllegalArgumentException();
		}
	}

}
