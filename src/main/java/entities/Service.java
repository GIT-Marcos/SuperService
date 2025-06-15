package entities;

import enums.EstadoService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "services")
public class Service implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_service")
    private Long id;
    
    private LocalDate fechaInicio;
    
    private LocalDate fechaFin;
    
    //ENUM ESTADO SERVICE
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "estado_service")
    private EstadoService estadoService;
    
    //RELACIÓN BI * A 1 CON CLIENTE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;
    
    //RELACION BI 1 A 1 CON ORDEN TRABAJO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_orden_trabajo", nullable = false)
    private OrdenTrabajo ordenTrabajo;
    
    //RELACIÓN 1 A 1 CON PAGO

    public Service() {
    }

    public Service(Long id, LocalDate fechaInicio, LocalDate fechaFin, EstadoService estadoService, Cliente cliente, OrdenTrabajo ordenTrabajo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoService = estadoService;
        this.cliente = cliente;
        this.ordenTrabajo = ordenTrabajo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
}
