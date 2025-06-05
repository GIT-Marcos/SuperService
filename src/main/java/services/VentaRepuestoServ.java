package services;

import DAOs.VentaRepuestoDAO;
import DAOs.VentaRepuestoDAOimpl;
import entities.VentaRepuesto;

/**
 *
 * @author Usuario
 */
public class VentaRepuestoServ {
    
    private VentaRepuestoDAO dao = new VentaRepuestoDAOimpl();
    
    public VentaRepuesto cargarVenta(VentaRepuesto venta){
        
        return dao.cargarVenta(venta);
    }
    
}
