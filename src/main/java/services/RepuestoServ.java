package services;

import DAOs.RepuestoDAO;
import DAOs.RepuestoDAOimpl;
import entities.Repuesto;
import java.util.List;
import org.hibernate.HibernateException;

public class RepuestoServ {

    private final RepuestoDAO dao = new RepuestoDAOimpl();
    

    public List<Repuesto> todosRepuestos() {
        return dao.todosRepuestos();
    }

    public Boolean existeCodBarra(Repuesto repuesto) {
        return dao.existeCodBarra(repuesto);
    }

    public Repuesto buscarPorCodBarraExacto(String codBarra) {
        return dao.buscarPorCodBarraExacto(codBarra);
    }

    public List<Repuesto> buscarPorCodBarra(String codBarra) {
        return dao.buscarPorCodBarra(codBarra);
    }

    public List<Repuesto> buscarPorDetalle(String detalle) {
        return dao.buscarPorDetalle(detalle);
    }

    public Repuesto cargarRepuesto(Repuesto repuesto) {
        if (repuesto.getStock() == null) {
            throw new NullPointerException("Error: el stock es nulo.");
        }
        if (existeCodBarra(repuesto)) {
            throw new HibernateException("Error: ya existe un repuesto con ese código de barras.");
        }
        return dao.cargarRepuesto(repuesto);
    }

    public Repuesto modificarRepuesto(Repuesto repuesto) {
        if (repuesto.getStock() == null) {
            throw new NullPointerException("Error: el stock es nulo.");
        }
        return dao.modificarRepuesto(repuesto);
    }

    public Boolean borrarRepuesto(Repuesto repuesto) {
        return dao.borrarRepuesto(repuesto);
    }

}
