package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Entity
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "activo")
@Table(name = "stock")
public class Stock implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_stock")
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(name = "cantidad_minima")
    private Integer cantMinima;

    @Column(nullable = false)
    private String ubicacion;

    @Column
    private String lote;

    @Column
    private String observaciones;

    public Stock() {
    }

    public Stock(Long id, Integer cantidad, Integer cantMinima, String ubicacion, String lote, String observaciones) {
        this.id = id;
        this.cantidad = cantidad;
        this.cantMinima = cantMinima;
        this.ubicacion = ubicacion;
        this.lote = lote;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantMinima() {
        return cantMinima;
    }

    public void setCantMinima(Integer cantMinima) {
        this.cantMinima = cantMinima;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", cantidad=" + cantidad + ", cantMinima=" + cantMinima + ", ubicacion=" + ubicacion + ", lote=" + lote + ", observaciones=" + observaciones + '}';
    }

}
