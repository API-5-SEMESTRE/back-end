package api.theVelopers.sas.converter;

import static api.theVelopers.sas.enumeration.TipoEmpresa.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import api.theVelopers.sas.enumeration.TipoEmpresa;

@Converter
@ReadingConverter
@WritingConverter
public class TipoEmpresaConverter implements AttributeConverter<TipoEmpresa, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoEmpresa attribute) {

		if(attribute == null) {
			return null;
		}
		
		switch(attribute) {
		case SPC:
			return 1;
		
		case CONCORRENTE:
			return 2;
		
		case LIVRE:
			return 3;
		
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public TipoEmpresa convertToEntityAttribute(Integer dbData) {
		if(dbData == null) {
			return null;
		}
		
		switch(dbData) {
		case 1:
			return SPC;
		
		case 2:
			return CONCORRENTE;
		
		case 3:
			return LIVRE;
		
		default:
			throw new IllegalArgumentException();
		}
	}

}