package api.theVelopers.sas.enumeration;

import java.util.Arrays;
import java.util.Optional;

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
    
    public static Optional<TipoUsuario> get(String url) {
        return Arrays.stream(TipoUsuario.values())
            .filter(tipo -> tipo.tipo.equals(url))
            .findFirst();
    }
}
