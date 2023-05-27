package Vista;

import Controlador.Controlador;
import Modelo.VendedorDTO;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SeleccionVendedor {

    private final Stage stage;
    private final Controlador controlador;
    private String vendedorSeleccionado = null; // Variable para llevar registro del vendedor seleccionado

    // Crear los textos para los vendedores como campos de clase
    private final Text nombreVendedor1;
    private final Text nombreVendedor2;
    private final Text ventasVendedor1; // Nuevo campo para mostrar las ventas
    private final Text ventasVendedor2; // Nuevo campo para mostrar las ventas

    public SeleccionVendedor(Controlador controlador) {
        this.controlador = controlador;
        this.stage = new Stage();

        // Inicializar los textos para los vendedores en el constructor
        nombreVendedor1 = new Text();
        nombreVendedor1.setFill(Color.WHITE);
        nombreVendedor1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        nombreVendedor2 = new Text();
        nombreVendedor2.setFill(Color.WHITE);
        nombreVendedor2.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        ventasVendedor1 = new Text(); // Inicializar el campo de ventas para el vendedor 1
        ventasVendedor1.setFill(Color.WHITE);
        ventasVendedor1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        ventasVendedor2 = new Text(); // Inicializar el campo de ventas para el vendedor 2
        ventasVendedor2.setFill(Color.WHITE);
        ventasVendedor2.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        this.crearVentana();
    }

    private void crearVentana() {
        //Crear el titulo
        Text titulo = new Text("Empresa XYZ");
        titulo.setFill(Color.WHITE);
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        // Crear el texto y centrarlo
        Text texto = new Text("Seleccione el vendedor");
        texto.setFill(Color.WHITE);
        texto.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        // Crear las imágenes de los vendedores
        Image image1 = new Image("file:///D:/Universidad/Cuarto%20Semestre%202023-1/Segundo%20Bloque/Programacion%20Avanzada/Segundo%20Corte/Gestor-Ventas/src/Vista/IMG/Vendedor%201.png", 100, 100, false, false);
        ImageView imagenVendedor1 = new ImageView(image1);
        Image image2 = new Image("file:///D:/Universidad/Cuarto%20Semestre%202023-1/Segundo%20Bloque/Programacion%20Avanzada/Segundo%20Corte/Gestor-Ventas/src/Vista/IMG/Vendedor%202.png", 100, 100, false, false);
        ImageView imagenVendedor2 = new ImageView(image2);

        // Crear los contenedores para los vendedores
        VBox vendedor1 = new VBox(10, imagenVendedor1, nombreVendedor1, ventasVendedor1);
        VBox vendedor2 = new VBox(10, imagenVendedor2, nombreVendedor2, ventasVendedor2);
        vendedor1.setAlignment(Pos.CENTER);
        vendedor2.setAlignment(Pos.CENTER);

        // Crear botón aceptar
        Button aceptar = new Button("Aceptar");
        aceptar.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FFA500; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Crear contenedor para vendedores y centrarlo
        HBox contenedorVendedores = new HBox(10, vendedor1, vendedor2);
        contenedorVendedores.setAlignment(Pos.CENTER);

        // Crear el contenedor principal y añadir los componentes
        VBox contenedorPrincipal = new VBox(10, texto, contenedorVendedores, aceptar);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setBackground(new Background(new BackgroundFill(Color.web("#FFA500"), CornerRadii.EMPTY, null)));

        // Crear un StackPane para centrar el contenedorPrincipal
        StackPane root = new StackPane(contenedorPrincipal);
        root.setAlignment(Pos.CENTER);

        // Eventos de click en las imágenes y textos
        vendedor1.setOnMouseClicked(event -> {
            texto.setText("Bienvenido " + nombreVendedor1.getText() + ", dale click en Aceptar");
            vendedorSeleccionado = nombreVendedor1.getText(); // Aquí actualizamos la variable con el vendedor seleccionado
        });

        vendedor2.setOnMouseClicked(event -> {
            texto.setText("Bienvenido " + nombreVendedor2.getText() + ", dale click en Aceptar");
            vendedorSeleccionado = nombreVendedor2.getText(); // Aquí actualizamos la variable con el vendedor seleccionado
        });

        aceptar.setOnAction(event -> {
            if (vendedorSeleccionado != null) {
                controlador.cambiarAVistaVendedor();
                stage.hide(); // Cerrar la ventana actual
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor seleccione un vendedor antes de continuar.");
                alert.showAndWait();
            }
        });

        Scene escena = new Scene(root, 500, 500);
        stage.setScene(escena);
    }

    public String getVendedorSeleccionado() {
        return vendedorSeleccionado;
    }

    public void setVendedores(List<VendedorDTO> vendedores) {
        if (vendedores.size() > 0) {
            VendedorDTO vendedor1 = vendedores.get(0);
            setNombreVendedor1(vendedor1.getNombre() + " " + vendedor1.getApellido());
            setVentasVendedor1(Integer.toString(vendedor1.getVentas())); // Mostrar las ventas del vendedor
        }

        if (vendedores.size() > 1) {
            VendedorDTO vendedor2 = vendedores.get(1);
            setNombreVendedor2(vendedor2.getNombre() + " " + vendedor2.getApellido());
            setVentasVendedor2(Integer.toString(vendedor2.getVentas())); // Mostrar las ventas del vendedor
        }
    }

    private void setNombreVendedor1(String nombre) {
        nombreVendedor1.setText(nombre);
    }

    private void setNombreVendedor2(String nombre) {
        nombreVendedor2.setText(nombre);
    }

    private void setVentasVendedor1(String ventas) {
        ventasVendedor1.setText("Ventas: " + ventas);
    }

    private void setVentasVendedor2(String ventas) {
        ventasVendedor2.setText("Ventas: " + ventas);
    }

    public void mostrar() {
        stage.show();
    }
}

