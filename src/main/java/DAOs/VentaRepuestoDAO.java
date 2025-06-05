package DAOs;

import entities.VentaRepuesto;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface VentaRepuestoDAO {
    
    //LECTURA
    
    List<VentaRepuesto> todasVentas();
    
    VentaRepuesto buscarVenta();
    
    //ESCRITURA
    
    VentaRepuesto cargarVenta(VentaRepuesto venta);
    
    VentaRepuesto modificarVenta(VentaRepuesto venta);
    
    Boolean eliminarVenta(VentaRepuesto venta);
}
