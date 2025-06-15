/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.CascadeType;
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
@Table(name = "modelos_autos")
public class ModeloAuto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_modelo_auto")
    private Long id;

    @Column(name = "nombre_modelo", nullable = false, unique = true)
    private String nombreModelo;

    @Column(nullable = false)
    private Integer anio;
    
    @Column(nullable = false)
    private Double cilindrada;
    
    //RELACIÓN * A 1 CON MARCA
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "fk_marca")
    private MarcaAuto marcaAuto;

    public ModeloAuto() {
    }

    public ModeloAuto(Long id, String nombreModelo, Integer anio, Double cilindrada, MarcaAuto marcaAuto) {
        this.id = id;
        this.nombreModelo = nombreModelo;
        this.anio = anio;
        this.cilindrada = cilindrada;
        this.marcaAuto = marcaAuto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public MarcaAuto getMarcaAuto() {
        return marcaAuto;
    }

    public void setMarcaAuto(MarcaAuto marcaAuto) {
        this.marcaAuto = marcaAuto;
    }

    @Override
    public String toString() {
        return "ModeloAuto{" + "id=" + id + ", nombreModelo=" + nombreModelo + ", anio=" + anio + ", cilindrada=" + cilindrada + '}';
    }

   
    
    
}
