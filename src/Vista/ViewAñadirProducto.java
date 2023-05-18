/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Cristhian
 */
public class ViewAñadirProducto {


    private final Stage stage;

    /**
     *
     */
    public ViewAñadirProducto() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Vender");

        Text texto = new Text("Esta es la ventana Añadir Producto");

        VBox contenedor = new VBox(texto);
        Scene escena = new Scene(contenedor, 400, 300);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}
