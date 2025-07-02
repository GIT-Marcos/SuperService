package DAOs;

import entities.Usuario;
import entities.VentaRepuesto;
import enums.EstadoVentaRepuesto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public interface VentaRepuestoDAO {

    //LECTURA
    List<VentaRepuesto> todasVentas();

    List<VentaRepuesto> buscarVentas(Long codVenta, EstadoVentaRepuesto estadoVenta,
            BigDecimal montoMinimo, BigDecimal montomaximo, String nombreColumnaOrnenar,
            Integer tipoOrden, Date fechaMinima, Date fechaMaxima);
    
    Map<String, Long> ventasPorMeses(Integer anio);

    //ESCRITURA
    VentaRepuesto cargarVenta(VentaRepuesto venta);

    VentaRepuesto modificarVenta(VentaRepuesto venta);

    Boolean borradoLogico(Long idVenta, String motivo, Usuario usuario);
}
