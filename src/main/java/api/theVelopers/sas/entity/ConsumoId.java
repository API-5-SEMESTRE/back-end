package api.theVelopers.sas.entity;

import static api.theVelopers.sas.entity.Consumo.CONSUMO;
import static api.theVelopers.sas.entity.Consumo.MES_REFERENCIA;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ConsumoId implements Serializable{

	private static final long serialVersionUID = -2770264252984250200L;
	
	@ManyToOne
	@JoinColumn(name=Empresa.ID)
	private Empresa empresa;
	
	@Column(name=MES_REFERENCIA)
	private LocalDateTime mesReferencia;
	
	@Column(name=CONSUMO)
	private Long quantidadeConsumo;
	
	public ConsumoId() {}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LocalDateTime getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(LocalDateTime mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Long getQuantidadeConsumo() {
		return quantidadeConsumo;
	}

	public void setQuantidadeConsumo(Long quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empresa, mesReferencia, quantidadeConsumo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumoId other = (ConsumoId) obj;
		return Objects.equals(empresa, other.empresa) && Objects.equals(mesReferencia, other.mesReferencia)
				&& Objects.equals(quantidadeConsumo, other.quantidadeConsumo);
	}
	
}
