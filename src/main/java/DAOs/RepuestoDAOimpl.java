package DAOs;

import entities.Repuesto;
import java.util.List;
import org.hibernate.Session;
import util.Util;

public class RepuestoDAOimpl implements RepuestoDAO {

    private Session session;

    @Override
    public List<Repuesto> todosRepuestos() {
        session = Util.getHibernateSession();
        List<Repuesto> repuestos = session.createQuery("SELECT r FROM Repuesto r JOIN FETCH r.stock",
                Repuesto.class).setMaxResults(50).list();
        session.close();
        return repuestos;
    }

    @Override
    public Repuesto buscarRepuesto(Long id) {
        session = Util.getHibernateSession();
        Repuesto repuesto = session.find(Repuesto.class, id);
        session.close();
        return repuesto;
    }

    /**
     * Verifica si existen varios repuestos con un mismo código de barras. Sirve
     * para la carga de nuevos y para la modificación.
     * 
     */
    @Override
    public Boolean existeCodBarra(Repuesto repuesto) {
        session = Util.getHibernateSession();
        Long cantidad = session.createQuery("SELECT COUNT(r) FROM Repuesto r "
                + "WHERE r.codBarra = :codBarra",
                Long.class)
                .setParameter("codBarra", repuesto.getCodBarra())
                .getSingleResult();
        session.close();
        if (cantidad > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Repuesto buscarPorCodBarraExacto(String codBarra
    ) {
        session = Util.getHibernateSession();
        Repuesto repuesto = session.createQuery("SELECT DISTINCT r FROM Repuesto r "
                + "WHERE r.codBarra LIKE :codBarra",
                Repuesto.class)
                .setParameter("codBarra", codBarra)
                .getSingleResult();
        session.close();
        return repuesto;
    }

    @Override
    public List<Repuesto> buscarPorCodBarra(String codBarra
    ) {
        session = Util.getHibernateSession();
        List<Repuesto> lista = session.createQuery("SELECT DISTINCT r FROM Repuesto r "
                + "WHERE r.codBarra LIKE :codBarra",
                Repuesto.class).setParameter("codBarra", "%" + codBarra + "%").list();
        session.close();
        return lista;
    }

    @Override
    public List<Repuesto> buscarPorDetalle(String detalle
    ) {
        session = Util.getHibernateSession();
        List<Repuesto> lista = session.createQuery("SELECT DISTINCT r FROM Repuesto r "
                + "WHERE LOWER(r.detalle) "
                + "LIKE :detalle",
                Repuesto.class).setParameter("detalle", "%" + detalle.toLowerCase() + "%").list();
        session.close();
        return lista;
    }

    @Override
    public Repuesto cargarRepuesto(Repuesto repuesto
    ) {
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            session.persist(repuesto);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return repuesto;
    }

    @Override
    public Repuesto modificarRepuesto(Repuesto repuesto
    ) {
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            session.merge(repuesto);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return repuesto;
    }

    @Override
    public Boolean borrarRepuesto(Repuesto repuesto
    ) {
        //ESTO ES VILLERO PERO ANDA
        session = Util.getHibernateSession();
        Repuesto aBorrar = session.find(Repuesto.class, repuesto.getId());

        try {
            session.beginTransaction();
            session.remove(aBorrar);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }

        /* DA ERROR. EL OBJETO NO ESTA ATTACHEADO A LA SESSION
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            session.remove(repuesto);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }*/
    }

}
