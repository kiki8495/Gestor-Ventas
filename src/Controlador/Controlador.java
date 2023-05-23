package Controlador;

import Vista.*;
import javafx.stage.Stage;

public class Controlador {

    private final SeleccionVendedor seleccionVendedor;
    private VistaVendedor vistaVendedor;
    private String vendedorSeleccionado;

    public Controlador(Stage primaryStage) {
        seleccionVendedor = new SeleccionVendedor(this);
    }

    public String getVendedorSeleccionado() {
        return this.vendedorSeleccionado;
    }

    public void setVendedorSeleccionado(String vendedor) {
        this.vendedorSeleccionado = vendedor;
    }

    public void iniciarAplicacion() {
        seleccionVendedor.mostrar();
    }

    public void cambiarAVistaVendedor() {
        setVendedorSeleccionado(seleccionVendedor.getVendedorSeleccionado());
        vistaVendedor = new VistaVendedor(this, getVendedorSeleccionado());
        vistaVendedor.mostrar();
    }

    public void abrirVentanaAñadirProducto() {
        ViewAñadirProducto vistaAñadirProducto = new ViewAñadirProducto();
        vistaAñadirProducto.mostrar();
    }

    public void abrirVentanaCarrito() {
        ViewCarrito vistaCarrito = new ViewCarrito();
        vistaCarrito.mostrar();
    }

    public void abrirVentanaVender() {
        ViewVender vistaVender = new ViewVender();
        vistaVender.mostrar();
    }

    public void abrirVentanaRecibos() {
        ViewRecibos vistaRecibos = new ViewRecibos();
        vistaRecibos.mostrar();
    }

    public void abrirVentanaVendidoMas() {
        ViewVendidoMas vistaVendidoMas = new ViewVendidoMas();
        vistaVendidoMas.mostrar();
    }

}
