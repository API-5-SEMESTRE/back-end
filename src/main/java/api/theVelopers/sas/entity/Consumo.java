package api.theVelopers.sas.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="consumo")
public class Consumo {
	
	public static final String MES_REFERENCIA = "cons_mesref";
	public static final String CONSUMO = "cons_consumo";
	
	@EmbeddedId
	private ConsumoId consumoId;
	
	public Consumo() {}

	public ConsumoId getConsumoId() {
		return consumoId;
	}

	public void setConsumoId(ConsumoId consumoId) {
		this.consumoId = consumoId;
	}

}