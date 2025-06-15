/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.ElementosInventario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "inventarios_ordenes")
public class InventarioOrden implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_inventario_orden")
    private Long id;

    @Column()
    private String observacion;

    @Column(nullable = false)
    private Integer cantidad;

    //ENUM ELEMENTOS
    @Enumerated(value = EnumType.STRING)
    @Column(name = "elementos")
    private ElementosInventario elementos;

    public InventarioOrden() {
    }

    public InventarioOrden(Long id, String observacion, Integer cantidad, ElementosInventario elementos) {
        this.id = id;
        this.observacion = observacion;
        this.cantidad = cantidad;
        this.elementos = elementos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ElementosInventario getElementos() {
        return elementos;
    }

    public void setElementos(ElementosInventario elementos) {
        this.elementos = elementos;
    }

    @Override
    public String toString() {
        return "InventarioOrden{" + "id=" + id + ", observacion=" + observacion + ", cantidad=" + cantidad + ", elementos=" + elementos + '}';
    }

}
