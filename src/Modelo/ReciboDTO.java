package Modelo;

public class ReciboDTO {

    private int idVenta;
    private String contenido;

    public ReciboDTO(int idVenta, String contenido) {
        this.idVenta = idVenta;
        this.contenido = contenido;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
