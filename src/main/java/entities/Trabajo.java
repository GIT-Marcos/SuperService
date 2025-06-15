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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "trabajos_ordenes")
public class Trabajo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_trabajo")
    private Long id;
    
    @Column(name = "nombre_trabajo",nullable = false)
    private String nombreTrabajo;
    
    @Column(name = "precio_trabajo", nullable = false)
    private BigDecimal precioTrabajo;

    public Trabajo() {
    }

    public Trabajo(Long id, String nombreTrabajo, BigDecimal precioTrabajo) {
        this.id = id;
        this.nombreTrabajo = nombreTrabajo;
        this.precioTrabajo = precioTrabajo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public BigDecimal getPrecioTrabajo() {
        return precioTrabajo;
    }

    public void setPrecioTrabajo(BigDecimal precioTrabajo) {
        this.precioTrabajo = precioTrabajo;
    }

    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", nombreTrabajo=" + nombreTrabajo + ", precioTrabajo=" + precioTrabajo + '}';
    }
    
}
