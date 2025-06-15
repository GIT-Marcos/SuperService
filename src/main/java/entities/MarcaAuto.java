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

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "marcas_autos")
public class MarcaAuto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_marca_auto")
    private Long id;

    @Column(name = "nombre_marca_auto", nullable = false, unique = true)
    private String nombreMarca;
    
    //TO-DO:HACER TODA ESTA CADENA DE RELACIONES BIDIRECCIONALES

    public MarcaAuto() {
    }

    public MarcaAuto(Long id, String nombreMarca) {
        this.id = id;
        this.nombreMarca = nombreMarca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @Override
    public String toString() {
        return "MarcaAuto{" + "id=" + id + ", nombreMarca=" + nombreMarca + '}';
    }
    
}
