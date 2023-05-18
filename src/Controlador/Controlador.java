package Controlador;

import Vista.SeleccionVendedor;
import Vista.ViewAñadirProducto;
import Vista.ViewRecibos;
import Vista.ViewVender;
import Vista.ViewVendidoMas;
import javafx.stage.Stage;
import Vista.VistaVendedor;

public class Controlador {

    private Stage primaryStage;
    private SeleccionVendedor seleccionVendedor;
    private VistaVendedor vistaVendedor;

    public Controlador(Stage primaryStage) {
        this.primaryStage = primaryStage;
        seleccionVendedor = new SeleccionVendedor(this);
        vistaVendedor = new VistaVendedor(this);
    }

    public void iniciarAplicacion() {
        seleccionVendedor.mostrar();
    }

    public void cambiarAVistaVendedor() {
        vistaVendedor.mostrar();
    }

    public void abrirVentanaAñadirProducto() {
        ViewAñadirProducto vistaAñadirProducto = new ViewAñadirProducto();
        vistaAñadirProducto.mostrar();
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
