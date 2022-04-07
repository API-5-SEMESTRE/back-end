package api.theVelopers.sas.converter;

import static api.theVelopers.sas.enumeration.TipoEmpresa.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import api.theVelopers.sas.enumeration.TipoEmpresa;

@Converter(autoApply = true)
public class TipoEmpresaConverter implements AttributeConverter<TipoEmpresa, String>{

	@Override
	public String convertToDatabaseColumn(TipoEmpresa attribute) {

		if(attribute == null) {
			return null;
		}
		
		switch(attribute) {
		case SPC:
			return "SPC";
		
		case CONCORRENTE:
			return "CONCORRENTE";
		
		case LIVRE:
			return "LIVRE";
		
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public TipoEmpresa convertToEntityAttribute(String dbData) {
		if(dbData == null) {
			return null;
		}
		
		switch(dbData) {
		case "SPC":
			return SPC;
		
		case "CONCORRENTE":
			return CONCORRENTE;
		
		case "LIVRE":
			return LIVRE;
		
		default:
			throw new IllegalArgumentException();
		}
	}

}