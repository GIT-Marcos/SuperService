package entities;

import enums.EstadoVentaRepuesto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas_repuestos")
public class VentaRepuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_ventas_repuestos")
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    //ENUM ESTADO DE VENTA
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EstadoVentaRepuesto estadoVenta;

    //RELACIÓN CON NOTA DE RETIRO
    @OneToMany( )
    @Column(name = "fk_notas_retiros")
    private List<NotaRetiro> notaRetiroList = new ArrayList<>();

    //RELACIÓN CON FACTURA Q ES DTO (¿
    //RELACIÓN BI  CON CLIENTE
    public VentaRepuesto() {
    }

    public VentaRepuesto(Long id, LocalDate fecha, EstadoVentaRepuesto estadoVenta, List<NotaRetiro> notaRetiroList) {
        this.id = id;
        this.fecha = fecha;
        this.estadoVenta = estadoVenta;
        this.notaRetiroList = notaRetiroList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoVentaRepuesto getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVentaRepuesto estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public List<NotaRetiro> getNotasRetiros() {
        return notaRetiroList;
    }

    public void setNotasRetiros(List<NotaRetiro> notasRetiros) {
        this.notaRetiroList = notasRetiros;
    }

    @Override
    public String toString() {
        return "VentaRepuesto{" + "id=" + id + ", fecha=" + fecha + ", estadoVenta=" + estadoVenta + '}';
    }

}
