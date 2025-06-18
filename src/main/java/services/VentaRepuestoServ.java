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

    public List<VentaRepuesto> todasVentas() {
        return dao.todasVentas();
    }

    public VentaRepuesto cargarVenta(VentaRepuesto venta) {

        return dao.cargarVenta(venta);
    }

    /**
     *
     * @param tipoOrden pasar nulo si no importa el orden
     * @return
     */
    public List<VentaRepuesto> buscarVentas(Long codVenta, EstadoVentaRepuesto estadoVenta,
            BigDecimal montoMinimo, BigDecimal montomaximo, String nombreColumnaOrnenar, Integer tipoOrden) {
        if (nombreColumnaOrnenar == null) {
            nombreColumnaOrnenar = "id";
        }
        if (tipoOrden == null) {
            tipoOrden = 0;
        }
        return dao.buscarVentas(codVenta, estadoVenta, montoMinimo, montomaximo, nombreColumnaOrnenar, tipoOrden);
    }

    /**
     * @param venta
     * @return
     */
    public VentaRepuesto modificarVenta(VentaRepuesto venta) {
        return dao.modificarVenta(venta);
    }
}
