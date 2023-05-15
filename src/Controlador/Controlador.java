package Controlador;

import Vista.SeleccionVendedor;
import javafx.stage.Stage;
import Vista.VistaVendedor;

public class Controlador {
    private Stage primaryStage;
    private SeleccionVendedor seleccionVendedor;
    private VistaVendedor vistaVendedor;
    private String vendedorSeleccionado;

    public Controlador(Stage primaryStage) {
        this.primaryStage = primaryStage;
        seleccionVendedor = new SeleccionVendedor(this, primaryStage); // Pasar el Stage principal
        
        // No creamos la VistaVendedor todavía
    }
    public void setVendedorSeleccionado(String vendedorSeleccionado) {
        this.vendedorSeleccionado = vendedorSeleccionado;
}
    public String getVendedorSeleccionado() {
        return vendedorSeleccionado;
    }


    public void iniciarAplicacion() {
        seleccionVendedor.mostrar();
    }

    public void cambiarAVistaVendedor() {
       vistaVendedor = new VistaVendedor(this, primaryStage);
       seleccionVendedor.ocultar(); // Ocultar la vista actual
        //Creamos la VistaVendedor solo cuando realmente necesitamos mostrarla
        vistaVendedor.mostrar(); // Mostrar la nueva vista
    }
}