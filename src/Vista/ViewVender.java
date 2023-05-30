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
import Modelo.ProductoDAO;
import Modelo.ProductoDTO;
import Modelo.VentaDAO;
import java.util.List;
import Modelo.VentaDTO;

public class ViewVender {

    private TextField id_seleccionado;
    private TextField unidades_vendidas;
    private final Stage stage;
    private final Controlador Controlador;
    private final ProductoDAO productoDAO;
    private final VentaDAO ventaDAO;

    public ViewVender(Controlador controlador) {
        this.Controlador = controlador;
        this.productoDAO = controlador.getProductoDAO(); // Obtener la instancia de ProductoDAO desde el controlador
        this.ventaDAO = controlador.getVentaDAO(); // Obtener la instancia de VentaDAO desde el controlador
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {

        stage.setTitle("Vender");

        List<ProductoDTO> productos = productoDAO.obtenerProductos();
        TableView<ProductoDTO> tabla = new TableView<>();
        ObservableList<ProductoDTO> data = FXCollections.observableArrayList(productos);

        tabla.setItems(data);

        tabla.setItems(data);

        TableColumn<ProductoDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<ProductoDTO, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setPrefWidth(180);

        TableColumn<ProductoDTO, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcionColumn.setPrefWidth(180);

        TableColumn<ProductoDTO, Integer> cantidadColumn = new TableColumn<>("Cantidad Disponible");
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidadColumn.setPrefWidth(100);

        TableColumn<ProductoDTO, Double> precioColumn = new TableColumn<>("Precio Unit");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioColumn.setPrefWidth(100);

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
                    int idProducto = Integer.parseInt(id_producto);
                    ProductoDTO productoSeleccionado = productoDAO.obtenerProductoPorId(idProducto);
                    if (productoSeleccionado == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("El producto seleccionado no existe");
                        alert.showAndWait();
                    } else {
                        Controlador.agregarProductoAlCarrito(productoSeleccionado);
                        stage.hide(); // Cerrar la ventana actual
                    }
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

        Button RealizarVenta = new Button("Realizar la venta");
        RealizarVenta.setOnAction(e -> {
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
                    // Aquí va la lógica para guardar la venta en la base de datos
                    // Por ahora, he establecido los valores a 0 o null, pero debes reemplazarlos con los valores adecuados
                    VentaDTO venta = new VentaDTO(0, 0, 0, Integer.parseInt(id_producto), 0, null, unidades, 0);
                    ventaDAO.agregarVenta(venta);

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

        gridPane.add(RealizarVenta, 2, 2);
    }

    public void mostrar() {
        stage.show();
    }
}
