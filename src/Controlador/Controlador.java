package Controlador;

import Vista.SeleccionVendedor;
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
}
