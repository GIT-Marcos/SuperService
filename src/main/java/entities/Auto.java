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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "autos")
public class Auto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_auto")
    private Long id;

    @Column(nullable = false, unique = true)
    private String patente;

    @Column(nullable = false, name = "nro_chasis")
    private String nroChasis;

    @Column(nullable = false, name = "nro_motor")
    private String nroMotor;

    @Column()
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "fk_modelo")
    private ModeloAuto ModeloAuto;

    //RELACIÓN 1 a * CON DATOSAUTO
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "fk_estado_ingreso")
    private List<DatosIngresoAuto> estadoIngreso;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;

    public Auto() {
    }

    public Auto(Long id, String patente, String nroChasis, String nroMotor, String color,
            ModeloAuto ModeloAuto, List<DatosIngresoAuto> estadoIngreso, Cliente cliente) {
        this.id = id;
        this.patente = patente;
        this.nroChasis = nroChasis;
        this.nroMotor = nroMotor;
        this.color = color;
        this.ModeloAuto = ModeloAuto;
        this.estadoIngreso = estadoIngreso;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public String getNroMotor() {
        return nroMotor;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ModeloAuto getModeloAuto() {
        return ModeloAuto;
    }

    public void setModeloAuto(ModeloAuto ModeloAuto) {
        this.ModeloAuto = ModeloAuto;
    }

    public List<DatosIngresoAuto> getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(List<DatosIngresoAuto> estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Auto{" + "id=" + id + ", patente=" + patente + ", nroChasis=" + nroChasis + ", nroMotor=" + nroMotor + ", color=" + color + '}';
    }

}
