package api.theVelopers.sas.dto;

import java.time.LocalDateTime;


public class ConsumoDTO {
	
	private Long quantidadeConsumo;

	private LocalDateTime mesReferencia;
	
	public ConsumoDTO(Long quantidadeConsumo, LocalDateTime mesReferencia) {
		this.mesReferencia = mesReferencia;
		this.quantidadeConsumo = quantidadeConsumo;
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
	
}
