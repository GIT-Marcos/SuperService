package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "detalles_retiros")
public class DetalleRetiro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_detalles_retiros")
    private Long id;

    @Column(length = 10, nullable = false)
    private Double cantidad;

    //RELACIÓN * A 1 CON REPUESTO
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_repuesto", nullable = false)
    private Repuesto repuesto;

    public DetalleRetiro() {
    }

    public DetalleRetiro(Long id, Double cantidad, Repuesto repuesto) {
        this.id = id;
        this.cantidad = cantidad;
        this.repuesto = repuesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
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
        return "DetalleRetiro{" + "id=" + id + ", cantidad=" + cantidad + '}';
    }

}
