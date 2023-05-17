package Vista;

import Controlador.Controlador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VistaVendedor {

    private Stage stage;
    private Controlador controlador;

    public VistaVendedor(Controlador controlador) {
        this.controlador = controlador;
        this.stage = new Stage();
        this.crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Vista Vendedor");

        // Crear el encabezado con el nombre del vendedor y la empresa
        Label nombreVendedor = new Label("Nombre Vendedor");
        nombreVendedor.setFont(new Font("Arial", 20));
        Label nombreEmpresa = new Label("Nombre Empresa");
        nombreEmpresa.setFont(new Font("Arial", 20));
        HBox encabezado = new HBox(10, nombreVendedor, nombreEmpresa);
        encabezado.setAlignment(Pos.CENTER);

        // Crear los botones para las acciones
        Button realizarCompra = new Button("Realizar Compra");
        Button mirarVentas = new Button("Mirar Ventas");
        Button quienHaVendidoMas = new Button("¿Quién ha vendido más?");
        VBox acciones = new VBox(10, realizarCompra, mirarVentas, quienHaVendidoMas);
        acciones.setPadding(new Insets(10, 10, 10, 10));

        // Crear el label para la fecha y hora actual
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Label fechaHora = new Label(dtf.format(now));
        fechaHora.setFont(new Font("Arial", 14));

        // Crear el layout principal
        BorderPane layoutPrincipal = new BorderPane();
        layoutPrincipal.setTop(encabezado);
        layoutPrincipal.setLeft(acciones);
        layoutPrincipal.setBottom(fechaHora);
        BorderPane.setAlignment(fechaHora, Pos.BOTTOM_RIGHT);

        // Configurar la escena y mostrar la ventana
        Scene escena = new Scene(layoutPrincipal, 500, 500);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}
