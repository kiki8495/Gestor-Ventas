package Vista;

import Controlador.Controlador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SeleccionVendedor {

    private Stage stage;
    private Controlador controlador;
    private String vendedorSeleccionado = null;

    public SeleccionVendedor(Controlador controlador, Stage stage) {
        this.controlador = controlador;
        this.stage = stage; // Usar el Stage principal en lugar de crear uno nuevo
        this.crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Selección del Vendedor");

        // Crear el texto y centrarlo
        Text texto = new Text("Seleccione el vendedor");
        texto.setFill(Color.WHITE);

        // Crear las imágenes de los vendedores
        ImageView imagenVendedor1 = new ImageView(new Image("file:///D:/Universidad/Cuarto%20Semestre%202023-1/Segundo%20Bloque/Programacion%20Avanzada/Segundo%20Corte/Gestor-Ventas/src/Vista/IMG/icono_usuario.png"));
        ImageView imagenVendedor2 = new ImageView(new Image("file:///D:/Universidad/Cuarto%20Semestre%202023-1/Segundo%20Bloque/Programacion%20Avanzada/Segundo%20Corte/Gestor-Ventas/src/Vista/IMG/icono_usuario.png"));

        // Crear los textos para los vendedores
        Text nombreVendedor1 = new Text("Vendedor 1");
        nombreVendedor1.setFill(Color.WHITE);
        Text nombreVendedor2 = new Text("Vendedor 2");
        nombreVendedor2.setFill(Color.WHITE);
                
        // Crear los contenedores para los vendedores
        VBox vendedor1 = new VBox(imagenVendedor1, nombreVendedor1);
        VBox vendedor2 = new VBox(imagenVendedor2, nombreVendedor2);
        vendedor1.setAlignment(Pos.CENTER);
        vendedor2.setAlignment(Pos.CENTER);

        // Crear botón aceptar
        Button aceptar = new Button("Aceptar");
        aceptar.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");

        // Crear el contenedor principal y añadir los componentes
        VBox contenedorPrincipal = new VBox(10, texto, new HBox(10, vendedor1, vendedor2), aceptar);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        // Eventos de click en las imágenes y textos
        vendedor1.setOnMouseClicked(event -> {
            texto.setText("Ha seleccionado al vendedor 1");
            vendedorSeleccionado = "Vendedor 1"; // Aquí actualizamos la variable con el vendedor seleccionado
            controlador.setVendedorSeleccionado("Vendedor 1");
        });

        vendedor2.setOnMouseClicked(event -> {
            texto.setText("Ha seleccionado al vendedor 2");
            vendedorSeleccionado = "Vendedor 2"; // Aquí actualizamos la variable con el vendedor seleccionado
            controlador.setVendedorSeleccionado("Vendedor 2");
        });

    aceptar.setOnAction(event -> {
    if (vendedorSeleccionado != null) {
        controlador.setVendedorSeleccionado(vendedorSeleccionado);
        controlador.cambiarAVistaVendedor();
        stage.hide();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor seleccione un vendedor antes de continuar.");
        alert.showAndWait();
    }
    });


        Scene escena = new Scene(contenedorPrincipal, 500, 500);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }

    public void ocultar() {
    stage.hide();
    }
}