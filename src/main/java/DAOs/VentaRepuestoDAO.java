package DAOs;

import entities.VentaRepuesto;
import enums.EstadoVentaRepuesto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface VentaRepuestoDAO {

    //LECTURA
    List<VentaRepuesto> todasVentas();

    List<VentaRepuesto> buscarVentas(Long codVenta, EstadoVentaRepuesto estadoVenta,
            BigDecimal montoMinimo, BigDecimal montomaximo);

    //ESCRITURA
    VentaRepuesto cargarVenta(VentaRepuesto venta);

    VentaRepuesto modificarVenta(VentaRepuesto venta);

    Boolean eliminarVenta(VentaRepuesto venta);
}
