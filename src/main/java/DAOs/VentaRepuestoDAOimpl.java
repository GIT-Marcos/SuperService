package DAOs;

import entities.DetalleRetiro;
import entities.NotaRetiro;
import entities.Repuesto;
import entities.Stock;
import entities.VentaRepuesto;
import enums.EstadoVentaRepuesto;
import static enums.EstadoVentaRepuesto.ACEPTADO;
import static enums.EstadoVentaRepuesto.CANCELADO;
import static enums.EstadoVentaRepuesto.PAGADO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import util.Util;

/**
 *
 * @author Usuario
 */
public class VentaRepuestoDAOimpl implements VentaRepuestoDAO {

    private Session session;

    @Override
    public List<VentaRepuesto> todasVentas() {
        session = Util.getHibernateSession();
        List<VentaRepuesto> ventas = session.createQuery("SELECT DISTINCT v FROM VentaRepuesto v",
                VentaRepuesto.class).setMaxResults(100).list();
        session.close();
        return ventas;
    }

    @Override
    public List<VentaRepuesto> buscarVentas(Long codVenta, EstadoVentaRepuesto estadoVenta,
            BigDecimal montoMinimo, BigDecimal montomaximo) {
        session = Util.getHibernateSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<VentaRepuesto> query = cb.createQuery(VentaRepuesto.class);
        Root<VentaRepuesto> root = query.from(VentaRepuesto.class);
        Join<VentaRepuesto, NotaRetiro> joinNota = root.join("notaRetiro");
        Join<NotaRetiro, DetalleRetiro> joinDetalles = joinNota.join("detalleRetiroList");
        Join<DetalleRetiro, Repuesto> joinRepuesto = joinDetalles.join("repuesto");
        Join<Repuesto, Stock> joinStock = joinRepuesto.join("stock");
        List<Predicate> filtros = new ArrayList<>();

        //SI SE QUIERE BUSCAR ALGO POR CÓDIGO...
        if (codVenta != null) {
            filtros.add(cb.equal(root.get("id"), String.valueOf(codVenta)));
        }
        //SI SE BUSCA UN ESTADO != DE CUALQUIERA...
        if (estadoVenta != null) {
            switch (estadoVenta) {
                case PRESUPUESTANDO: //se eligió cod barra
                    filtros.add(cb.like(root.get("estadoVenta"), EstadoVentaRepuesto.PRESUPUESTANDO.toString()));
                    break;
                case PENDIENTE_PAGO:
                    filtros.add(cb.like(root.get("estadoVenta"), EstadoVentaRepuesto.PENDIENTE_PAGO.toString()));
                    break;
                case PAGADO:
                    filtros.add(cb.like(root.get("estadoVenta"), EstadoVentaRepuesto.PAGADO.toString()));
                    break;
                case CANCELADO: //se eligió detalle
                    filtros.add(cb.like(root.get("estadoVenta"), EstadoVentaRepuesto.CANCELADO.toString()));
                    break;
                default:
                    throw new IllegalArgumentException("Error en el estado de la venta:"
                            + " error en el estado de la búsqueda de la venta.");
            }
        }
        //FILTROS DE LA FECHA

        //FILTROS DEL MONTO
        if (montoMinimo != null && montomaximo != null) {
            filtros.add(cb.between(root.get("montoTotal"), montoMinimo, montomaximo));
        } else if (montoMinimo != null && montomaximo == null) {
            filtros.add(cb.greaterThan(root.get("montoTotal"), montoMinimo));
        } else if (montoMinimo == null && montomaximo != null) {
            filtros.add(cb.lessThan(root.get("montoTotal"), montomaximo));
        }

        query.where(cb.and(filtros.toArray(new Predicate[0])));
        List<VentaRepuesto> ventas = session.createQuery(query).setMaxResults(50).getResultList();

        session.close();
        return ventas;
    }

    @Override
    public VentaRepuesto cargarVenta(VentaRepuesto venta) {
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            session.persist(venta);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return venta;
    }

    @Override
    public VentaRepuesto modificarVenta(VentaRepuesto venta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean eliminarVenta(VentaRepuesto venta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
