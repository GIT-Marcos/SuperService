package services;

import DAOs.PagoDAO;
import DAOs.PagoDAOimpl;
import entities.Pago;

/**
 *
 * @author Usuario
 */
public class PagoServ {
    
    PagoDAO dao = new PagoDAOimpl();
    
    public Pago cargarNuevoPago(Pago pago){
        return dao.cargarNuevoPago(pago);
    }
}
