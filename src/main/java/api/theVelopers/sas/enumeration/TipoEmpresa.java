package api.theVelopers.sas.enumeration;

import java.util.Arrays;
import java.util.Optional;

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
    
    public static Optional<TipoEmpresa> get(String url) {
        return Arrays.stream(TipoEmpresa.values())
            .filter(tipo -> tipo.tipo.equals(url))
            .findFirst();
    }
}
