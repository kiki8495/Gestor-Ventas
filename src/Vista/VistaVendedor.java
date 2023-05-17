package Vista;

import Controlador.Controlador;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaVendedor {

    private Stage stage;
    private Controlador controlador;

    public VistaVendedor(Controlador controlador) {
        this.controlador = controlador;
        this.stage = new Stage();
        this.crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Vista del Vendedor");

        // Crear el texto para el nombre de la empresa y el vendedor
        Text nombreEmpresa = new Text("Gestor Ventas");
        nombreEmpresa.setFill(Color.WHITE);
        nombreEmpresa.setStyle("-fx-font-size: 20px;");

        Text nombreVendedor = new Text("Vendedor");
        nombreVendedor.setFill(Color.WHITE);
        nombreVendedor.setStyle("-fx-font-size: 16px;");

        // Crear los botones para las opciones
        Button vender = new Button("Vender");
        vender.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF;");

        Button ingresarProducto = new Button("Ingresar Producto");
        ingresarProducto.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF;");

        Button catalogoProductos = new Button("Catálogo Productos");
        catalogoProductos.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF;");

        Button masVentas = new Button("¿Quién ha hecho más ventas?");
        masVentas.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF;");

        // Crear el contenedor para el menú lateral
        VBox menuLateral = new VBox(10, nombreEmpresa, nombreVendedor, vender, ingresarProducto, catalogoProductos, masVentas);
        menuLateral.setPadding(new Insets(10));
        menuLateral.setStyle("-fx-background-color: #ffa500;");

        // Crear el contenedor principal y añadir los componentes
        HBox contenedorPrincipal = new HBox(menuLateral);

        Scene escena = new Scene(contenedorPrincipal, 800, 600);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}
