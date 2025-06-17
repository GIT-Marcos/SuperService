package DAOs;

import entities.Pago;

/**
 *
 * @author Usuario
 */
public interface PagoDAO {

    Pago cargarNuevoPago(Pago pago);
    
    Pago agregarPagoVentaPendientePago(Pago pago);
    
}
