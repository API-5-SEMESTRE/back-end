package api.theVelopers.sas.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import api.theVelopers.sas.enumeration.TipoUsuario;

import static api.theVelopers.sas.enumeration.TipoUsuario.*;

@Converter
@ReadingConverter
@WritingConverter
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoUsuario attribute) {

		if(attribute == null) {
			return null;
		}
		
		switch(attribute) {
		case ADMINISTRADOR:
			return 1;
		
		case VENDEDOR:
			return 2;
		
		case INTELIGENCIA:
			return 3;
		
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public TipoUsuario convertToEntityAttribute(Integer dbData) {
		if(dbData == null) {
			return null;
		}
		
		switch(dbData) {
		case 1:
			return ADMINISTRADOR;
		
		case 2:
			return VENDEDOR;
		
		case 3:
			return INTELIGENCIA;
		
		default:
			throw new IllegalArgumentException();
		}
	}

}
