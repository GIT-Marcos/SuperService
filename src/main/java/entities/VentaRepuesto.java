package entities;

import enums.EstadoVentaRepuesto;
import enums.MetodosPago;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ventas_repuestos")
public class VentaRepuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_venta_repuestos")
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column()
    private String referencia;

    @Column(name = "ultimos_4")
    private String ultimos4;

    //ENUM MÉTODO DE PAGO
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "metodo_pago")
    private MetodosPago MetodosPago;

    //ENUM ESTADO DE VENTA
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "estado_venta")
    private EstadoVentaRepuesto estadoVenta;

    //RELACIÓN CON NOTA DE RETIRO
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_nota_retiro")
    private NotaRetiro notaRetiro;

    //RELACIÓN CON FACTURA Q ES DTO (¿
    public VentaRepuesto() {
    }

    public VentaRepuesto(Long id, LocalDate fecha, BigDecimal monto, String referencia,
            String ultimos4, MetodosPago MetodosPago, EstadoVentaRepuesto estadoVenta,
            NotaRetiro notaRetiroList) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.referencia = referencia;
        this.ultimos4 = ultimos4;
        this.MetodosPago = MetodosPago;
        this.estadoVenta = estadoVenta;
        this.notaRetiro = notaRetiroList;
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public MetodosPago getMetodosPago() {
        return MetodosPago;
    }

    public void setMetodosPago(MetodosPago MetodosPago) {
        this.MetodosPago = MetodosPago;
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

    public void setNotaRetiro(NotaRetiro notaRetiroList) {
        this.notaRetiro = notaRetiroList;
    }

    @Override
    public String toString() {
        return "VentaRepuesto{" + "id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", referencia=" + referencia + ", ultimos4=" + ultimos4 + ", MetodosPago=" + MetodosPago + ", estadoVenta=" + estadoVenta + '}';
    }

    public BigDecimal calculaMonto() {
        BigDecimal monto = BigDecimal.ZERO;
        for (DetalleRetiro detalle : this.notaRetiro.getDetallesRetiro()) {
            BigDecimal precio = detalle.getRepuesto().getPrecio();
            BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());
            monto = monto.add(precio.multiply(cantidad));
        }
        return monto;
    }
}
