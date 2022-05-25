package api.theVelopers.sas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empresa_score")
public class EmpresaScore {
	
	public static final String ID = "cnpj";
	public static final String ORIGEM="origem";
	public static final String REGIAO="regiao";
	public static final String MEDIA_CONSUMO="media_consumo";
	public static final String TOTAL_CONSUMO="total_consumo";
	public static final String MEDIA_SCORE="media_score";
	public static final String TOTAL_SCORE="total_score";
	public static final String CNAE="cnae";
	public static final String ESTADO="estado";
	
	@Id
	@Column(name=ID)
	private Long cnpj;
	
	@Column(name=ORIGEM)
	private String origem;
	@Column(name=REGIAO)
	private String regiao;
	@Column(name=MEDIA_CONSUMO)
	private Long mediaConsumo;
	@Column(name=TOTAL_CONSUMO)
	private Long totalConsumo;
	@Column(name=MEDIA_SCORE)
	private Long mediaScore;
	@Column(name=TOTAL_SCORE)
	private Long totalScore;
	@Column(name=CNAE)
	private String cnae;
	@Column(name=ESTADO)
	private String estado;
	
	public Long getCnpj() {
		return cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public Long getMediaConsumo() {
		return mediaConsumo;
	}
	public void setMediaConsumo(Long mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}
	public Long getTotalConsumo() {
		return totalConsumo;
	}
	public void setTotalConsumo(Long totalConsumo) {
		this.totalConsumo = totalConsumo;
	}
	public Long getMediaScore() {
		return mediaScore;
	}
	public void setMediaScore(Long mediaScore) {
		this.mediaScore = mediaScore;
	}
	public Long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}
	public String getCnae() {
		return cnae;
	}
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
