package api.theVelopers.sas.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static api.theVelopers.sas.entity.Consumo.*;

@Embeddable
public class ConsumoId implements Serializable{

	private static final long serialVersionUID = -2770264252984250200L;
	
	@Column(name=CONSUMO)
	private Long empresaId;
	
	@Column(name=MES_REFERENCIA)
	private LocalDateTime mesReferencia;
	
	public ConsumoId() {}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public LocalDateTime getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(LocalDateTime mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
}
