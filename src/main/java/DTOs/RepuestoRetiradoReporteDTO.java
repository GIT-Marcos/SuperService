package DTOs;

/**
 * CLASE DTO PARA HACER REPORTE DE REPUESTOS MÁS RETIRADOS DEL MES
 * @author Usuario
 */
public class RepuestoRetiradoReporteDTO {

    private String codBarra;
    private String marca;
    private String detalle;
    private Long cantidad;

    public RepuestoRetiradoReporteDTO(String codBarra, String marca, String detalle, Long cantidad) {
        this.codBarra = codBarra;
        this.marca = marca;
        this.detalle = detalle;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RepuestoRetiradoReporteDTO{" + "codBarra=" + codBarra + ", marca=" + marca + ", detalle=" + detalle + ", cantidad=" + cantidad + '}';
    }
    
}
