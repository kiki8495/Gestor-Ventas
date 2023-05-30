package Modelo;

public class VendedorDTO {

    private int id;
    private String nombre;
    private String apellido;
    private int ventas;
    private int precioVentas;

    public VendedorDTO(String nombre, String apellido, int id, int ventas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ventas = ventas;
        this.precioVentas = precioVentas;
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

    public int getVentas() {
        return ventas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getPrecioVentas() {
        return precioVentas;
    }

    public void setPrecioVentas(int precioVentas) {
        this.precioVentas = precioVentas;
    }

}
