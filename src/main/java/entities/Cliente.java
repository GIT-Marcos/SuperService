package entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_cliente")
    private Long id;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    //RELACIÓN 1 A * CONTACTO
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_contacto_cliente", nullable = false)
    private List<ContactoCliente> contactosCliente = new ArrayList<>();

    //RELACIÓN BI 1 A * CON AUTO
    @OneToMany(mappedBy = "cliente")
    private List<Auto> autos = new ArrayList<>();

    //RELACIÓN BI 1 A * CON SERVICE
    @OneToMany(mappedBy = "cliente")
    private List<Service> services = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String dni, String nombre, String apellido, List<ContactoCliente> contactosCliente,
            List<Auto> autos, List<Service> services) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contactosCliente = contactosCliente;
        this.autos = autos;
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<ContactoCliente> getContactosCliente() {
        return contactosCliente;
    }

    public void setContactosCliente(List<ContactoCliente> contactosCliente) {
        this.contactosCliente = contactosCliente;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }

}
