package DAOs;

import entities.Repuesto;
import entities.Stock;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
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
    public Long cuentaRespBajoStock() {
        session = Util.getHibernateSession();
        Long cantidad = session.createQuery("SELECT DISTINCT COUNT(r) FROM Repuesto r "
                + "WHERE r.stock.cantidad <= r.stock.cantMinima",
                Long.class)
                .getSingleResult();

        session.close();
        return cantidad;
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
    public List<Repuesto> buscarConFiltros(String inputParaBuscar, Integer opcionBusqueda, Boolean stockNormal, Boolean stockBajo) {
        session = Util.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Repuesto> query = cb.createQuery(Repuesto.class);
        Root<Repuesto> root = query.from(Repuesto.class);
        Join<Repuesto, Stock> joinStock = root.join("stock");
        List<Predicate> filtros = new ArrayList<>();

        //SI SE QUIERE BUSCAR ALGO...
        if (inputParaBuscar != null) {
            switch (opcionBusqueda) {
                case 0: //se eligió cod barra
                    filtros.add(cb.like(cb.lower(root.get("codBarra")), "%" + inputParaBuscar.toLowerCase() + "%"));
                    break;
                case 1: //se eligió detalle
                    filtros.add(cb.like(cb.lower(root.get("detalle")), "%" + inputParaBuscar.toLowerCase() + "%"));
                    break;
                default:
                    throw new AssertionError();
            }
        }
        //SI LOS 2 VIENEN VERDADEROS, O SEA QUIERE VER CUALQUIERA, NO ENTRA EN NINGÚN IF
        if (stockNormal && !stockBajo) {
            filtros.add(cb.greaterThan(joinStock.get("cantidad"), joinStock.get("cantMinima")));
        } else if (stockBajo && !stockNormal) {
            filtros.add(cb.lessThanOrEqualTo(joinStock.get("cantidad"), joinStock.get("cantMinima")));
        }
        query.where(cb.and(filtros.toArray(new Predicate[0])));
        List<Repuesto> repuestos = session.createQuery(query).setMaxResults(50).getResultList();
        session.close();
        return repuestos;
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
        //TO DO: MEJORAR ESTO
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
