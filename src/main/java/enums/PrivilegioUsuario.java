package enums;

public enum PrivilegioUsuario {
    GERENCIAL(1),
    JEFE_TALLER(2),
    JEFE_VENTAS(3),
    JEFE_RECEPCION(4),
    JEFE_DEPOSITO(5),
    OPERATIVO_TALLER(6),
    OPERATIVO_VENTAS(7),
    OPERATIVO_RECEPCION(8),
    OPERATIVO_DEPOSITO(9);

    private final int numero;

    PrivilegioUsuario(int numero) {
        this.numero = numero;
    }
    
    public static PrivilegioUsuario devuelvePrivPorNumero(int numero) {
        for (PrivilegioUsuario p : values()) {
            if (p.numero == numero)
                return p;
        }
        throw new IllegalArgumentException("Número inválido: " + numero);
    }
}
