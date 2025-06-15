/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "detalles_orenedes_trabajo")
public class DetalleOrdenTrabajo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_detalle_orden_trabajo")
    private Long id;
    
    @Column()
    private String observaciones;
    
    //RELACIÓN * A 1 CON EL TRABAJO
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_trabajo", nullable = false)
    private Trabajo trabajo;

    public DetalleOrdenTrabajo() {
    }

    public DetalleOrdenTrabajo(Long id, String observaciones, Trabajo trabajo) {
        this.id = id;
        this.observaciones = observaciones;
        this.trabajo = trabajo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    @Override
    public String toString() {
        return "DetalleOrdenTrabajo{" + "id=" + id + ", observaciones=" + observaciones + '}';
    }
    
    
}
