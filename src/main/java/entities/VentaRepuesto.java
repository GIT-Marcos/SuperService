package entities;

import enums.EstadoVentaRepuesto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas_repuestos")
public class VentaRepuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_venta_repuestos")
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fechaVenta;

    @Column(name = "monto_total", precision = 16, scale = 2, nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "monto_faltante", precision = 16, scale = 2, nullable = false)
    private BigDecimal montoFaltante;

    //ENUM ESTADO DE VENTA
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "estado_venta")
    private EstadoVentaRepuesto estadoVenta;

    //RELACIÓN CON NOTA DE RETIRO
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_nota_retiro")
    private NotaRetiro notaRetiro;

    //RELACIÓN BI 1 A * CON PAGO
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_venta")
    private List<Pago> pagosList = new ArrayList<>();

    public VentaRepuesto() {
    }

    public VentaRepuesto(Long id, LocalDate fechaVenta, BigDecimal montoTotal, 
            BigDecimal montoFaltante, EstadoVentaRepuesto estadoVenta, NotaRetiro notaRetiro, List<Pago> pagosList) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.montoFaltante = montoFaltante;
        this.estadoVenta = estadoVenta;
        this.notaRetiro = notaRetiro;
        this.pagosList = pagosList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoFaltante() {
        return montoFaltante;
    }

    public void setMontoFaltante(BigDecimal montoFaltante) {
        this.montoFaltante = montoFaltante;
    }

    public EstadoVentaRepuesto getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVentaRepuesto estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public NotaRetiro getNotaRetiro() {
        return notaRetiro;
    }

    public void setNotaRetiro(NotaRetiro notaRetiro) {
        this.notaRetiro = notaRetiro;
    }

    public List<Pago> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pago> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public String toString() {
        return "VentaRepuesto{" + "id=" + id + ", fechaVenta=" + fechaVenta + ", montoTotal=" + montoTotal + ", montoFaltante=" + montoFaltante + ", estadoVenta=" + estadoVenta + '}';
    }


    public BigDecimal calculaMontoTotal() {
        BigDecimal montoTotal = BigDecimal.ZERO;
        for (DetalleRetiro detalle : this.notaRetiro.getDetallesRetiro()) {
            BigDecimal precio = detalle.getRepuesto().getPrecio();
            BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());
            montoTotal = montoTotal.add(precio.multiply(cantidad));
        }
        return montoTotal;
    }
}
