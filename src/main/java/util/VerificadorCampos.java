package util;

/**
 *
 * @author Usuario
 */
public class VerificadorCampos {

    public void verificarVacio(String paraVerificar, String nombreCampo) {
        if (paraVerificar.isBlank() || paraVerificar.isEmpty() || paraVerificar == null) {
            throw new IllegalArgumentException("El campo '" + nombreCampo
                    + "' no puede quedar vacío.");
        }
    }

    public void verificaLargo(String paraVerificar, int largo, String nombreCampo) {
        if (paraVerificar.length() > largo || paraVerificar.length() < 0) {
            throw new IllegalArgumentException("El campo '" + nombreCampo
                    + "' no puede tener más de " + largo + " caracteres.");
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
            throw new NumberFormatException("El campo '" + nombreCampo + "' está en formato incorrecto.");
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
     * LOS CÓDIGOS DE BARRAS PUEDEN TENER LETRAS ADEMÁS DE NÚMERO, O SEA QUE EL
     * MÉTODO NO ES NECESARIO. usado para verificar que los códigos de barras
     * ingresados no tengan letras ni símbolos.
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

    public void verificaPassword(char[] password) {
        if (password == null) {
            throw new NullPointerException("La contraseña es nula.");
        }
        if (password.length == 0) {
            throw new NullPointerException("La contraseña no puede quedar vacía.");
        }
        if (password.length > 20 && password.length < 6) {
            throw new IllegalArgumentException("La contraseña no puede tener menos de 6 y más de 20 caracteres.");
        }
        for (char c : password) {
            if (Character.isWhitespace(c)) {
                throw new IllegalArgumentException("La contraseña no puede tener espacios en blanco.");
            }
        }
    }
}
