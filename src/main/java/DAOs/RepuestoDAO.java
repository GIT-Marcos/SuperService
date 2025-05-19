package DAOs;

import entities.Repuesto;
import java.util.List;

public interface RepuestoDAO {

    /////////////////////LECTURA
    /**
     * 
     * @return 
     */
    List<Repuesto> todosRepuestos();

    /**
     *Busca un repuesto por su id
     * @param id
     * @return
     */
    Repuesto buscarRepuesto(Long id);

    /**
     * Verifica si existen varios repuestos con un mismo código de barras.Sirve
     * para la carga de nuevos y para la modificación.
     *
     * @param repuesto para verificarle al cod de barra
     * @return
     */
    Boolean existeCodBarra(Repuesto repuesto);
    
    /**
     * Cuenta los repuestos que tienen menor stock existente que stock mínimo.
     * @return cantidad que contó
     */
    Long cuentaRespBajoStock();

    Repuesto buscarPorCodBarraExacto(String codBarra);

    List<Repuesto> buscarPorCodBarra(String codBarra);

    List<Repuesto> buscarPorDetalle(String detalle);

    //////////////////////ESCRITURA
    
    /**
     * 
     * @param repuesto
     * @return 
     */
    Repuesto cargarRepuesto(Repuesto repuesto);

    /**
     *
     * @param repuesto
     * @return
     */
    Repuesto modificarRepuesto(Repuesto repuesto);

    /**
     *
     * @param repuesto
     * @return
     */
    Boolean borrarRepuesto(Repuesto repuesto);
}
