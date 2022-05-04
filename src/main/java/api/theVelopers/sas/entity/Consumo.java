package api.theVelopers.sas.entity;

import java.time.LocalDateTime;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import api.theVelopers.sas.dto.ConsumoDTO;


@NamedNativeQuery(name = "Consumo.findConsumoMesReferenciaDTOByEmpresaId_Named",
query = "SELECT c.cons_consumo as quantidadeConsumo, c.cons_mesref as mesReferencia FROM consumo c WHERE c.emp_cnpj = :cnpj",
resultSetMapping = "Mapping.ConsumoDTO")
@SqlResultSetMapping(name = "Mapping.ConsumoDTO",
   classes = @ConstructorResult(targetClass = ConsumoDTO.class,
                                columns = {@ColumnResult(name = "quantidadeConsumo", type = Long.class),
                                           @ColumnResult(name = "mesReferencia", type = LocalDateTime.class)}))
@NamedNativeQuery(name = "Consumo.findConsumoMesReferenciaDTOByUsuarioId_Named",
query = "SELECT c.cons_consumo as quantidadeConsumo, c.cons_mesref as mesReferencia "
		+ "FROM consumo c "
		+ "inner join empresa e on e.emp_cnpj = c.emp_cnpj "
		+ "and c.cons_mesref >= e.emp_data_cadastro_vendedor "
		+ "inner join usuario u on u.usu_id = e.usu_id where e.usu_id = :id",
resultSetMapping = "Mapping.ConsumoDTO2")
@SqlResultSetMapping(name = "Mapping.ConsumoDTO2",
   classes = @ConstructorResult(targetClass = ConsumoDTO.class,
                                columns = {@ColumnResult(name = "quantidadeConsumo", type = Long.class),
                                           @ColumnResult(name = "mesReferencia", type = LocalDateTime.class)}))
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