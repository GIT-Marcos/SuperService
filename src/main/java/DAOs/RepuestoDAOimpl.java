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
    public Boolean consultaEstado(String codBarra) {
        Boolean resultado;
        session = Util.getHibernateSession();
        resultado = session.createNativeQuery("SELECT r.activo FROM repuestos r WHERE r.codigo_barra = :codBarra",
                Boolean.class)
                .setParameter("codBarra", codBarra)
                .getSingleResultOrNull();
        session.close();
        return resultado;
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
    public List<Repuesto> buscarConFiltros(String inputParaBuscar, Integer opcionBusqueda, Boolean stockNormal,
            Boolean stockBajo, String nombreColumnaOrnenar, Integer tipoOrden) {
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
        if (tipoOrden == 0) {
            query.orderBy(cb.asc(root.get(nombreColumnaOrnenar)));
        } else if (tipoOrden == 1) {
            query.orderBy(cb.desc(root.get(nombreColumnaOrnenar)));
        }
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
    public Repuesto reviveRepuestoInactivo(Repuesto repuesto) {
        session = Util.getHibernateSession();
        //El que viene tiene la id nula pq se la genera el orm, hay que buscarla
        Long idInactivo = session.createNativeQuery("SELECT r.pk_repuesto FROM repuestos r "
                + "WHERE r.codigo_barra = :codigo_barra",
                Long.class)
                .setParameter("codigo_barra", repuesto.getCodBarra())
                .getSingleResultOrNull();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE stock SET cantidad_minima= :cantidad_minima, "
                    + "cantidad= :cantidad, lote= :lote, observaciones= :obser, ubicacion= :ubic, "
                    + "activo = true "
                    + "WHERE pk_stock= :pk",
                    Integer.class)
                    .setParameter("cantidad_minima", repuesto.getStock().getCantMinima())
                    .setParameter("cantidad", repuesto.getStock().getCantidad())
                    .setParameter("lote", repuesto.getStock().getLote())
                    .setParameter("obser", repuesto.getStock().getObservaciones())
                    .setParameter("ubic", repuesto.getStock().getUbicacion())
                    .setParameter("pk", idInactivo)
                    .executeUpdate();
            session.createNativeQuery("UPDATE repuestos SET detalle= :detalle, "
                    + "marca= :marca, precio= :precio, "
                    + "activo = true "
                    + "WHERE pk_repuesto= :pk",
                    Integer.class)
                    .setParameter("detalle", repuesto.getDetalle())
                    .setParameter("marca", repuesto.getMarca())
                    .setParameter("precio", repuesto.getPrecio())
                    .setParameter("pk", idInactivo)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return repuesto;
    }

    @Override
    public Boolean borrarRepuesto(Repuesto repuesto
    ) {
        //TO-DO: MEJORAR ESTO
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
