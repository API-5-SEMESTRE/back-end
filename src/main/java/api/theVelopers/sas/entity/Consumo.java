package api.theVelopers.sas.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Consumo {
	
	public static final String EMPRESA = "empresa_emp_cnpj";
	public static final String MES_REFERENCIA = "cons_mesref";
	public static final String CONSUMO = "cons_consumo";
	
	@EmbeddedId
	private ConsumoId consumoId;
	
	@OneToOne
	@JoinColumn(
			name=EMPRESA,
			referencedColumnName=Empresa.ID,
			nullable=false
	)
	private Empresa empresa;
	
	public Consumo() {}

	public ConsumoId getConsumoId() {
		return consumoId;
	}

	public void setConsumoId(ConsumoId consumoId) {
		this.consumoId = consumoId;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}