package Vista;

import Controlador.Controlador;
import Modelo.ProductoDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Cristhian
 */
public class ViewAñadirProducto {

    private TextField productNameField;
    private TextField descriptionField;
    private TextField priceField;
    private TextField unitsAvailableField;
    private final Stage stage;
    private final Controlador controlador;

    public ViewAñadirProducto(Controlador controlador) {
        this.stage = new Stage();
        this.controlador = controlador;
        crearVentana();
    }

    private void crearVentana() {

        stage.setTitle("Añadir Producto");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Nombre del producto
        Label productNameLabel = new Label("Nombre del producto:");
        gridPane.add(productNameLabel, 0, 0);
        productNameField = new TextField();
        gridPane.add(productNameField, 1, 0);

        // Descripción
        Label descriptionLabel = new Label("Descripción:");
        gridPane.add(descriptionLabel, 0, 1);

        descriptionField = new TextField();
        gridPane.add(descriptionField, 1, 1);

        // Precio único
        Label priceLabel = new Label("Precio único:");
        gridPane.add(priceLabel, 0, 2);

        priceField = new TextField();
        gridPane.add(priceField, 1, 2);

        // Unidades disponibles
        Label unitsAvailableLabel = new Label("Unidades disponibles:");
        gridPane.add(unitsAvailableLabel, 0, 3);

        unitsAvailableField = new TextField();
        gridPane.add(unitsAvailableField, 1, 3);

        // Botón para registrar el producto
        // Botón para registrar el producto
        Button registerButton = new Button("Registrar Producto");

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    String nombre_producto = productNameField.getText();
                    String descripcion_producto = descriptionField.getText();
                    double precio = Double.parseDouble(priceField.getText());
                    int unidades = Integer.parseInt(unitsAvailableField.getText());

                    if (nombre_producto.isEmpty()
                            || descripcion_producto.isEmpty()
                            || precio <= 0
                            || unidades <= 0) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("No has completado todos los campos");
                        alert.showAndWait();

                    } else {
                        // Obtener el último ID de la base de datos
                        int ultimoID = controlador.getProductoDAO().obtenerUltimoID();

                        // Calcular el nuevo ID sumando 1 al último ID
                        int nuevoID = ultimoID + 1;

                        // Crear un nuevo objeto ProductoDTO con el nuevo ID y los demás datos ingresados
                        ProductoDTO nuevoProducto = new ProductoDTO(nuevoID, nombre_producto, descripcion_producto, unidades, (int) precio);

                        // Guardar el nuevo producto en la base de datos
                        controlador.getProductoDAO().agregarProducto(nuevoProducto);

                        stage.hide(); // Cerrar la ventana actual
                    }
                } catch (NumberFormatException ie) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Datos Inválidos");
                    alert.showAndWait();
                }
            }
        });

        gridPane.add(registerButton, 1, 4);

        Text texto = new Text("Nuevo Producto");

        Scene escena = new Scene(gridPane, 400, 200);

        //VBox contenedor = new VBox(texto);
        //Scene escena = new Scene(contenedor, 400, 300);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}
