package api.theVelopers.sas.enumeration;

import java.util.Arrays;
import java.util.Optional;

/**
 * 
 * @author jef
 *
 */
public enum TipoUsuario {

	ADMINISTRADOR("ADMINISTRADOR"),
	VENDEDOR("VENDEDOR"),
	INTELIGENCIA("INTELIGENCIA");
	
	private final String tipo;

    /**
     * @param tipo
     */
    TipoUsuario(final String tipo) {
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
    public static Optional<TipoUsuario> get(String descrição) {
        return Arrays.stream(TipoUsuario.values())
            .filter(tipo -> tipo.tipo.equals(descrição))
            .findFirst();
    }
}
