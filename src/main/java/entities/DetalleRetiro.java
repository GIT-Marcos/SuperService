package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "detalles_retiros")
public class DetalleRetiro implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_detalles_retiros")
    private Long id;
    
    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal total;
    
    @Column(length = 10, nullable = false)
    private Integer cantidad;
    
    //RELACIÓN 1 A 1 CON REPUESTO
    @OneToOne(optional = false)
    private Repuesto repuesto;

    public DetalleRetiro() {
    }

    public DetalleRetiro(Long id, BigDecimal total, Integer cantidad, Repuesto repuesto) {
        this.id = id;
        this.total = total;
        this.cantidad = cantidad;
        this.repuesto = repuesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    @Override
    public String toString() {
        return "DetalleRetiro{" + "id=" + id + ", total=" + total + ", cantidad=" + cantidad + '}';
    }
    
    
}
