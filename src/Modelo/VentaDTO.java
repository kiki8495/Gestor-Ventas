package Modelo;

import java.time.LocalDateTime;

public class VentaDTO {
    private int idVenta;
    private int vendedorId;
    private int clienteId;
    private int productoId;
    private int formaPago;
    private LocalDateTime fecha;
    private int cantidad;
    private int precioTotal;

    public VentaDTO(int idVenta, int vendedorId, int clienteId, int productoId, int formaPago, LocalDateTime fecha, int cantidad, int precioTotal) {
        this.idVenta = idVenta;
        this.vendedorId = vendedorId;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
}