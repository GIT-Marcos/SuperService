package entities;

import enums.EstadoOrdenTrabajo;
import enums.PrioridadOrden;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_orden_trabajo")
    private Long id;

    @Column(nullable = false)
    private Boolean inspecionGeneral;

    @Column()
    private String informesTecnicos;

    @Column(nullable = false)
    private BigDecimal totalPecioTrabajos;

    //ENUM ESTADO ORDEN
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "estado_orden")
    private EstadoOrdenTrabajo estadoOrden;

    //ENUM PRIORIDAD
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "prioridad")
    private PrioridadOrden prioridadOrden;

    //RELACIÓN 1 A * CON DETALLE ORDEN
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_detalle_orden", nullable = false)
    private List<DetalleOrdenTrabajo> DetalleOrdenTrabajoList = new ArrayList<>();

    //RELACIÓN 1 A * CON INVENTARIO
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_invetario")
    private List<InventarioOrden> InventarioOrdenList = new ArrayList<>();

    //RELCAION 1 A * CON OTA RETIRO
    @OneToMany()
    @JoinColumn(name = "fk_nota_retiro")
    private List<NotaRetiro> notasRetiroService = new ArrayList<>();

    //RELACION 1 a 1 CON AUTO
    @OneToOne()
    @JoinColumn(name = "fk_auto_service", nullable = false)
    private Auto autoService;

    //RELACIÓN BI 1 A 1 CON SERVICE
    @OneToOne(mappedBy = "ordenTrabajo")
    private Service service;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(Long id, Boolean inspecionGeneral, String informesTecnicos,
            BigDecimal totalPecioTrabajos, EstadoOrdenTrabajo estadoOrden, PrioridadOrden prioridadOrden,
            List<DetalleOrdenTrabajo> DetalleOrdenTrabajoList, List<InventarioOrden> InventarioOrdenList,
            List<NotaRetiro> notasRetiroService, Auto autoService, Service service) {
        this.id = id;
        this.inspecionGeneral = inspecionGeneral;
        this.informesTecnicos = informesTecnicos;
        this.totalPecioTrabajos = totalPecioTrabajos;
        this.estadoOrden = estadoOrden;
        this.prioridadOrden = prioridadOrden;
        this.DetalleOrdenTrabajoList =DetalleOrdenTrabajoList;
        this.InventarioOrdenList=InventarioOrdenList;
        this.notasRetiroService = notasRetiroService;
        this.autoService = autoService;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInspecionGeneral() {
        return inspecionGeneral;
    }

    public void setInspecionGeneral(Boolean inspecionGeneral) {
        this.inspecionGeneral = inspecionGeneral;
    }

    public String getInformesTecnicos() {
        return informesTecnicos;
    }

    public void setInformesTecnicos(String informesTecnicos) {
        this.informesTecnicos = informesTecnicos;
    }

    public BigDecimal getTotalPecioTrabajos() {
        return totalPecioTrabajos;
    }

    public void setTotalPecioTrabajos(BigDecimal totalPecioTrabajos) {
        this.totalPecioTrabajos = totalPecioTrabajos;
    }

    public EstadoOrdenTrabajo getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrdenTrabajo estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public PrioridadOrden getPrioridadOrden() {
        return prioridadOrden;
    }

    public void setPrioridadOrden(PrioridadOrden prioridadOrden) {
        this.prioridadOrden = prioridadOrden;
    }

    public List<DetalleOrdenTrabajo> getDetalleOrdenTrabajoList() {
        return DetalleOrdenTrabajoList;
    }

    public void setDetalleOrdenTrabajoList(List<DetalleOrdenTrabajo> DetalleOrdenTrabajoList) {
        this.DetalleOrdenTrabajoList = DetalleOrdenTrabajoList;
    }

    public List<InventarioOrden> getInventarioOrdenList() {
        return InventarioOrdenList;
    }

    public void setInventarioOrdenList(List<InventarioOrden> InventarioOrdenList) {
        this.InventarioOrdenList = InventarioOrdenList;
    }

    public List<NotaRetiro> getNotasRetiroService() {
        return notasRetiroService;
    }

    public void setNotasRetiroService(List<NotaRetiro> notasRetiroService) {
        this.notasRetiroService = notasRetiroService;
    }

    public Auto getAutoService() {
        return autoService;
    }

    public void setAutoService(Auto autoService) {
        this.autoService = autoService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "OrdenTrabajo{" + "id=" + id + ", inspecionGeneral=" + inspecionGeneral + ", informesTecnicos=" + informesTecnicos + ", totalPecioTrabajos=" + totalPecioTrabajos + ", estadoOrden=" + estadoOrden + ", prioridadOrden=" + prioridadOrden + '}';
    }


}
