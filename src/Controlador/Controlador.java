package Controlador;

import Modelo.ClienteDAO;
import Modelo.ClienteDTO;
import Modelo.ProductoDAO;
import Modelo.ProductoDTO;
import Modelo.VendedorDAO;
import Modelo.VendedorDTO;
import Modelo.VentaDAO;
import Modelo.VentaDTO;
import Vista.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;

public class Controlador {

    private final SeleccionVendedor seleccionVendedor;
    private VistaVendedor vistaVendedor;
    private String vendedorSeleccionado;
    private VendedorDAO vendedorDAO;
    private final ProductoDAO productoDAO;
    private final VentaDAO ventaDAO; // Agrega esto

    public Controlador(Stage primaryStage) {
        seleccionVendedor = new SeleccionVendedor(this); // Pasar "this" como argumento
        vendedorDAO = new VendedorDAO();
        productoDAO = new ProductoDAO(); // Agregar esta línea para inicializar productoDAO
        ventaDAO = new VentaDAO(); // Inicializa ventaDAO
    }

    private List<ProductoDTO> carrito = new ArrayList<>();

    public void agregarProductoAlCarrito(ProductoDTO producto) {
        carrito.add(producto);
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public VentaDAO getVentaDAO() { // Agrega este método
        return ventaDAO;
    }

    public String getVendedorSeleccionado() {
        return this.vendedorSeleccionado;
    }

    public void setVendedorSeleccionado(String vendedor) {
        this.vendedorSeleccionado = vendedor;
    }

    public void iniciarAplicacion() {
        List<VendedorDTO> vendedores = vendedorDAO.obtenerVendedores();
        seleccionVendedor.setVendedores(vendedores);
        seleccionVendedor.mostrar();
    }

    public void cambiarAVistaVendedor() {
        setVendedorSeleccionado(seleccionVendedor.getVendedorSeleccionado());
        vistaVendedor = new VistaVendedor(this, getVendedorSeleccionado());
        vistaVendedor.mostrar();
    }

    public void abrirVentanaAñadirProducto() {
        ViewAñadirProducto vistaAñadirProducto = new ViewAñadirProducto(this);
        vistaAñadirProducto.mostrar();
    }

    public void abrirVentanaCarrito() {
        ViewCarrito vistaCarrito = new ViewCarrito(this);
        vistaCarrito.mostrar();
    }

    public void abrirVentanaVender() {
        ViewVender vistaVender = new ViewVender(this);
        vistaVender.mostrar();
    }

    public void abrirVentanaRecibos() {
        ViewRecibos vistaRecibos = new ViewRecibos();
        vistaRecibos.mostrar();
    }

    public void abrirVentanaVendidoMas() {
        List<VendedorDTO> vendedores = vendedorDAO.obtenerVendedores();
        ViewVendidoMas vistaVendidoMas = new ViewVendidoMas(vendedores);
        vistaVendidoMas.mostrar();
    }

    public List<ProductoDTO> getCarrito() {
        return carrito;
    }

    public void realizarVenta(String nombreCliente, String telefonoCliente, String direccionCliente) {
        if (carrito.isEmpty()) {
            // mostrar mensaje de error
            return;
        }

        ClienteDTO cliente = new ClienteDTO(0, nombreCliente, telefonoCliente, direccionCliente);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.agregarCliente(cliente);

        int total = 0;
        for (ProductoDTO producto : carrito) {
            total += producto.getPrecio();
        }

        VentaDTO venta = new VentaDTO(0, Integer.parseInt(vendedorSeleccionado), cliente.getIdCliente(), 0, 0, LocalDateTime.now(), 0, total);
        ventaDAO.agregarVenta(venta);
        carrito.clear();
    }
}
