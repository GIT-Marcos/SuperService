package DAOs;

import entities.Repuesto;
import java.util.List;

public interface RepuestoDAO {

    /////////////////////LECTURA
    /**
     * Trae todos los repuestos para la tabla principal.
     * TO-DO: paginar resultados
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
     * Cuenta los repuestos que tienen menor stock existente que stock mínimo para
     * avisos en GUI.
     * @return cantidad que contó
     */
    Long cuentaRespBajoStock();
    
    /**
     * Para saber como proceder en la carga o modificación de un producto.
     * @param codBarra a consultar.
     * @return null: si el producto que se quiere cargar no existe ya; true: si
     * el producto ya existe y está activo; false: si el producto ya existe pero con
     * borrado lógico.
     */
    Boolean consultaEstado(String codBarra);

    /**
     * Trae un repuesto para cargar la GUI de modificación.
     * @param codBarra
     * @return 
     */
    Repuesto buscarPorCodBarraExacto(String codBarra);

    List<Repuesto> buscarPorCodBarra(String codBarra);

    List<Repuesto> buscarPorDetalle(String detalle);
    
    /**
     * 
     * @param inputParaBuscar
     * @param opcionBusqueda
     * @param stockNormal si el stock existente es MAYOR q el mínimo
     * @param stockBajo si el stock existente es MENOR q el mínimo
     * @return 
     */
    List<Repuesto> buscarConFiltros(String inputParaBuscar, Integer opcionBusqueda, Boolean stockNormal, Boolean stockBajo);

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
     * Usado cuando un repuesto se tiene que cargar o modificar y ya existe uno
     * CON BORRADO LÓGICO y con su mismo código de barras que es único.
     * @param repuesto con datos que sobreescriben los que ya están en db.
     * @return 
     */
    Repuesto reviveRepuestoInactivo(Repuesto repuesto); 

    /**
     *
     * @param repuesto
     * @return
     */
    Boolean borrarRepuesto(Repuesto repuesto);
}
