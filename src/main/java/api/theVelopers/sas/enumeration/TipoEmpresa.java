package api.theVelopers.sas.enumeration;

import java.util.Arrays;
import java.util.Optional;

/**
 * 
 * @author jef
 *
 */
public enum TipoEmpresa {
	
	SPC("SPC"),
	CONCORRENTE("CONCORRENTE"),
	LIVRE("LIVRE");

    private final String tipo;

    /**
     * @param tipo
     */
    TipoEmpresa(final String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
    /**
   	*
   	* @param String descricao
   	* @return TipoUsuario retorna um Optional do TipoUsuario encontrado
   	* a partir da descricao
   	*/
    public static Optional<TipoEmpresa> get(String descricao) {
        return Arrays.stream(TipoEmpresa.values())
            .filter(tipo -> tipo.tipo.equals(descricao))
            .findFirst();
    }
}
