package util;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class VerificadorCamposVacios {

    public void verificarVacios(List<String> lista) {
        for (String campo : lista) {
            if (campo.isBlank() || campo.isEmpty() || campo == null) {
                throw new IllegalArgumentException("Hay campos obligatirios vacíos.");
            }
        }
    }

    /**
     * usado para verificar que las cantidades de stock ingresadas no tengan
     * letras ni símbolos.
     *
     * @param valorCampo
     * @param nombreCampo
     */
    public void verificaFormatoInteger(String valorCampo, String nombreCampo) {
        try {
            Integer.valueOf(valorCampo);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El " + nombreCampo + " está en formato incorrecto.");
        }
    }

    /**
     * usado para verificar que los precios ingresados no tengan letras ni
     * símbolos.
     *
     * @param valorCampo
     * @param nombreCampo
     */
    public void verificaFormatoDouble(String valorCampo, String nombreCampo) {
        try {
            Double.valueOf(valorCampo);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El " + nombreCampo + " está en formato incorrecto.");
        }
    }
    
    /**
     * usado para verificar que los códigos de barras ingresados no tengan letras ni
     * símbolos.
     *
     * @param valorCampo
     * @param nombreCampo
     */
    public void verificaFormatoLong(String valorCampo, String nombreCampo) {
        try {
            Long.valueOf(valorCampo);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El " + nombreCampo + " está en formato incorrecto.");
        }
    }
}
