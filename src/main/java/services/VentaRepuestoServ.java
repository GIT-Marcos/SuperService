package services;

import DAOs.VentaRepuestoDAO;
import DAOs.VentaRepuestoDAOimpl;
import entities.VentaRepuesto;
import enums.EstadoVentaRepuesto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class VentaRepuestoServ {
    
    private VentaRepuestoDAO dao = new VentaRepuestoDAOimpl();
    
    public List<VentaRepuesto> todasVentas(){
        return dao.todasVentas();
    }
    
    public VentaRepuesto cargarVenta(VentaRepuesto venta){
        
        return dao.cargarVenta(venta);
    }
    
    public List<VentaRepuesto> buscarVentas(Long codVenta, EstadoVentaRepuesto estadoVenta,
            BigDecimal montoMinimo, BigDecimal montomaximo){
        return dao.buscarVentas(codVenta, estadoVenta, montoMinimo, montomaximo);
    }
    
}
