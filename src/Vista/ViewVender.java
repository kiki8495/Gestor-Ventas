package Vista;

import Controlador.Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewVender {

    private TextField id_seleccionado;
    private TextField unidades_vendidas;
    private final Stage stage;
    private Controlador Controlador;
    
    public ViewVender(Controlador controlador) {
       this.Controlador = controlador;
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {

        stage.setTitle("Vender");

        //Tabla de productos disponibles
        TableView<VistaVendedor.Producto> tabla = new TableView<>();
        ObservableList<VistaVendedor.Producto> data = FXCollections.observableArrayList(new VistaVendedor.Producto(1, "Producto 1", "detalle", 10, 50000),
                new VistaVendedor.Producto(2, "Producto 2", "detalle", 20, 50000),
                new VistaVendedor.Producto(3, "Producto 3", "detalle", 30, 50000)
        );

        tabla.setItems(data);

        TableColumn<VistaVendedor.Producto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setPrefWidth(180); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcionColumn.setPrefWidth(180); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, Integer> cantidadColumn = new TableColumn<>("Cantidad Disponible");
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidadColumn.setPrefWidth(100); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, Double> precioColumn = new TableColumn<>("Precio Unit");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioColumn.setPrefWidth(100); // Establecer el ancho preferido de la columna

        tabla.getColumns().addAll(idColumn, nombreColumn, descripcionColumn, cantidadColumn, precioColumn);

        // Contexto
        Text Detalle = new Text("Indique el ID y las unidades de producto que desea agregar a la venta:");

        // Agrupado para registrar datos
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Registro del ID
        Label id_lable = new Label("ID:");
        gridPane.add(id_lable, 0, 0);
        id_seleccionado = new TextField();
        gridPane.add(id_seleccionado, 1, 0);

        // Unidades Vendidas   
        Label unidades_lable = new Label("Unidades vendidas:");
        gridPane.add(unidades_lable, 0, 1);
        unidades_vendidas = new TextField();
        gridPane.add(unidades_vendidas, 1, 1);

        // Botón para Agregar al carrito
        Button AgregarProducto = new Button("Agregar a Carrito");
        //registerButton.setOnAction(e -> registerProduct());

        AgregarProducto.setOnAction(e -> {
            try {
                String id_producto = id_seleccionado.getText();
                int unidades = Integer.parseInt(unidades_vendidas.getText());
                if (id_producto.isEmpty() || unidades <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No has completado todos los campos");
                    alert.showAndWait();

                } else {
                    // Acá va la logica
                    Controlador.abrirVentanaCarrito();
                    //Producto(1, nombre_producto, descripcion_producto,unidades, precio);
                    stage.hide(); // Cerrar la ventana actual
                }
            } catch (NumberFormatException ie) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Datos Invalidos");
                alert.showAndWait();

            }

        });

        gridPane.add(AgregarProducto, 2, 1);

        VBox contenedorPrincipal = new VBox(tabla);
        contenedorPrincipal.setPadding(new Insets(10));
        contenedorPrincipal.setStyle("-fx-border-color: #ffa500; -fx-border-width: 2px;");
        contenedorPrincipal.setAlignment(Pos.TOP_LEFT);

        VBox contenedor = new VBox(contenedorPrincipal, Detalle, gridPane);
        Scene escena = new Scene(contenedor, 600, 300);
        stage.setScene(escena);

    }

    public void mostrar() {
        stage.show();
    }
}
