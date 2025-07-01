package services;

import DAOs.VentaRepuestoDAO;
import DAOs.VentaRepuestoDAOimpl;
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
public class VentaRepuestoServ {

    private VentaRepuestoDAO dao = new VentaRepuestoDAOimpl();

    public List<VentaRepuesto> todasVentas() {
        return dao.todasVentas();
    }

    public Map<String, Long> ventasPorMeses(Integer anio) {
        return dao.ventasPorMeses(anio);
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
            BigDecimal montoMinimo, BigDecimal montomaximo, String nombreColumnaOrnenar,
            Integer tipoOrden, Date fechaMinima, Date fechaMaxima) {
        if (nombreColumnaOrnenar == null) {
            nombreColumnaOrnenar = "id";
        }
        if (tipoOrden == null) {
            tipoOrden = 0;
        }
        return dao.buscarVentas(codVenta, estadoVenta, montoMinimo, montomaximo, nombreColumnaOrnenar,
                tipoOrden, fechaMinima, fechaMaxima);
    }

    public VentaRepuesto modificarVenta(VentaRepuesto venta) {
        return dao.modificarVenta(venta);
    }

    public Boolean borradoLogico(VentaRepuesto ventaRepuesto) {
        return dao.borradoLogico(ventaRepuesto.getId());
    }
}
