package DAOs;

import entities.VentaRepuesto;
import java.util.List;
import org.hibernate.Session;
import util.Util;

/**
 *
 * @author Usuario
 */
public class VentaRepuestoDAOimpl implements VentaRepuestoDAO{

    private Session session;
    
    @Override
    public List<VentaRepuesto> todasVentas() {
        session = Util.getHibernateSession();
        List<VentaRepuesto> ventas = session.createQuery("SELECT DISTINCT v FROM VentaRepuesto v "
                + "JOIN FETCH v.notaRetiro n "
                + "JOIN FETCH n.detalleRetiroList",
                VentaRepuesto.class).setMaxResults(100).list();
        session.close();
        return ventas;
    }

    @Override
    public VentaRepuesto buscarVenta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
