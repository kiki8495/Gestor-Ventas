package Controlador;

import Modelo.ProductoDAO;
import Modelo.ProductoDTO;
import Modelo.VendedorDAO;
import Modelo.VendedorDTO;
import Modelo.VentaDAO;
import Modelo.VentaDTO;
import Modelo.ReciboDAO;
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
    private final VentaDAO ventaDAO;
    private final ReciboDAO reciboDAO;
    private int vendedorId;
    private int clienteId;
    private int formaPago;

    public Controlador(Stage primaryStage) {
        seleccionVendedor = new SeleccionVendedor(this);
        vendedorDAO = new VendedorDAO();
        productoDAO = new ProductoDAO();
        ventaDAO = new VentaDAO();
        reciboDAO = new ReciboDAO();
    }

    private List<ProductoDTO> carrito = new ArrayList<>();

    public void agregarProductoAlCarrito(ProductoDTO producto, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            carrito.add(producto);
        }
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public VentaDAO getVentaDAO() {
        return ventaDAO;
    }

    public ReciboDAO getReciboDAO() {
        return reciboDAO;
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

    public void realizarVenta(String nombreCliente, String telefonoCliente, String direccionCliente, List<ProductoDTO> productosEnCarrito) {
        // Obtener el último ID de venta de la base de datos
        int ultimoIdVenta = ventaDAO.obtenerUltimoIdVenta();

        // Generar el ID para la nueva venta
        int idVenta = ultimoIdVenta + 1;

        // Obtener la fecha actual
        LocalDateTime fecha = LocalDateTime.now();

        // Calcular el precio total de la venta
        int precioTotal = 0;
        for (ProductoDTO producto : productosEnCarrito) {
            int cantidad = producto.getCantidad();
            int precioUnitario = (int) producto.getPrecio();
            precioTotal += cantidad * precioUnitario;
        }

        // Obtener los IDs del vendedor y cliente
        int vendedorId = obtenerIdVendedor(getVendedorSeleccionado());
        int clienteId = obtenerIdCliente(nombreCliente, telefonoCliente, direccionCliente);

        // Asignar el valor correcto a la variable formaPago
        int formaPago = 0; // Aquí debes asignar el valor correspondiente a la forma de pago

        // Insertar la venta en la base de datos
        ventaDAO.insertarVenta(new VentaDTO(idVenta, vendedorId, clienteId, 0, formaPago, fecha, productosEnCarrito.size(), precioTotal));

        // Generar el recibo y guardarlo en la base de datos
        String recibo = generarRecibo(idVenta, nombreCliente, telefonoCliente, direccionCliente, productosEnCarrito, precioTotal);
        byte[] contenidoRecibo = generarContenidoReciboPDF(recibo); // Genera el contenido del recibo en formato PDF
        reciboDAO.guardarRecibo(idVenta, contenidoRecibo);
    }

    private String generarRecibo(int idVenta, String nombreCliente, String telefonoCliente, String direccionCliente, List<ProductoDTO> productosEnCarrito, int precioTotal) {
        StringBuilder recibo = new StringBuilder();

        recibo.append("Recibo de Venta\n\n");
        recibo.append("ID Venta: ").append(idVenta).append("\n");
        recibo.append("Cliente: ").append(nombreCliente).append("\n");
        recibo.append("Teléfono: ").append(telefonoCliente).append("\n");
        recibo.append("Dirección: ").append(direccionCliente).append("\n");
        recibo.append("\nProductos:\n");
        for (ProductoDTO producto : productosEnCarrito) {
            recibo.append("- ").append(producto.getNombre()).append(" (").append(producto.getCantidad()).append(" unidad(es))\n");
        }
        recibo.append("\nTotal: ").append(precioTotal).append(" pesos\n");

        return recibo.toString();
    }

    public int obtenerIdVendedor(String nombreVendedor) {
        List<VendedorDTO> vendedores = vendedorDAO.obtenerVendedores();
        for (VendedorDTO vendedor : vendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombreVendedor)) {
                return vendedor.getId();
            }
        }
        return 0; // Si no se encuentra el vendedor, retorna 0 o algún valor que indique que no se encontró
    }

    public int obtenerIdCliente(String nombreCliente, String telefonoCliente, String direccionCliente) {
        // Aquí deberías implementar la lógica para obtener el ID del cliente
        // Puede ser consultando la base de datos o generando un nuevo ID, dependiendo de tu aplicación
        // Por ahora, vamos a retornar 0 para simular que no se encontró el cliente
        return 0;
    }

    public byte[] generarContenidoReciboPDF(String recibo) {
        // Aquí debes implementar la lógica para generar el contenido del recibo en formato PDF
        // Puedes utilizar una librería de generación de PDF, como iText, para crear el archivo PDF
        // y convertir el contenido del recibo en bytes

        byte[] contenidoRecibo = null;

        // Implementa la lógica para generar el contenido del recibo en formato PDF
        return contenidoRecibo;
    }
}