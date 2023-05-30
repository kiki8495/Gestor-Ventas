package Vista;

import Controlador.Controlador;
import Modelo.ProductoDAO;
import Modelo.ProductoDTO;
import java.util.ArrayList;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class ViewVender {

    private TextField id_seleccionado;
    private TextField unidades_vendidas;
    private final Stage stage;
    private final Controlador controlador;
    private final ProductoDAO productoDAO;
    private final Map<Integer, Integer> carrito; // Mapa para almacenar la cantidad seleccionada de cada producto

    public ViewVender(Controlador controlador) {
        this.controlador = controlador;
        this.productoDAO = controlador.getProductoDAO();
        this.stage = new Stage();
        this.carrito = new HashMap<>();
        crearVentana();
    }

    private void crearVentana() {

        stage.setTitle("Vender");

        List<ProductoDTO> productos = productoDAO.obtenerProductos();
        TableView<ProductoDTO> tabla = new TableView<>();
        ObservableList<ProductoDTO> data = FXCollections.observableArrayList(productos);

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

        TableColumn<ProductoDTO, Integer> unidadesColumn = new TableColumn<>("Unidades Seleccionadas");
        unidadesColumn.setCellValueFactory((CellDataFeatures<ProductoDTO, Integer> param) -> {
            ProductoDTO producto = param.getValue();
            int unidadesSeleccionadas = carrito.getOrDefault(producto.getId(), 0);
            return new SimpleIntegerProperty(unidadesSeleccionadas).asObject();
        });
        unidadesColumn.setPrefWidth(150);

        tabla.getColumns().addAll(idColumn, nombreColumn, descripcionColumn, cantidadColumn, precioColumn, unidadesColumn);

        // Contexto
        Text detalle = new Text("Indique el ID y las unidades de producto que desea agregar a la venta:");

        // Agrupado para registrar datos
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Registro del ID
        Label idLabel = new Label("ID:");
        gridPane.add(idLabel, 0, 0);
        id_seleccionado = new TextField();
        gridPane.add(id_seleccionado, 1, 0);

        // Unidades Vendidas   
        Label unidadesLabel = new Label("Unidades vendidas:");
        gridPane.add(unidadesLabel, 0, 1);
        unidades_vendidas = new TextField();
        gridPane.add(unidades_vendidas, 1, 1);

        // Botón para Agregar al carrito
        Button agregarProducto = new Button("Agregar a Carrito");
        agregarProducto.setOnAction(e -> {
            try {
                String id_producto = id_seleccionado.getText();
                int unidades = Integer.parseInt(unidades_vendidas.getText());
                if (id_producto.isEmpty() || unidades <= 0) {
                    mostrarError("No has completado todos los campos");
                } else {
                    int idProducto = Integer.parseInt(id_producto);
                    ProductoDTO productoSeleccionado = productoDAO.obtenerProductoPorId(idProducto);
                    if (productoSeleccionado == null) {
                        mostrarError("El producto seleccionado no existe");
                    } else if (productoSeleccionado.getCantidad() < unidades) {
                        mostrarError("No hay suficientes unidades disponibles");
                    } else {
                        carrito.put(idProducto, unidades);
                        actualizarTabla(tabla, carrito);
                        mostrarMensaje("Producto agregado al carrito");
                    }
                }
            } catch (NumberFormatException ie) {
                mostrarError("Datos inválidos");
            }
        });

        gridPane.add(agregarProducto, 2, 1);

        VBox contenedorPrincipal = new VBox(tabla);
        contenedorPrincipal.setPadding(new Insets(10));
        contenedorPrincipal.setStyle("-fx-border-color: #ffa500; -fx-border-width: 2px;");
        contenedorPrincipal.setAlignment(Pos.TOP_LEFT);

        VBox contenedor = new VBox(contenedorPrincipal, detalle, gridPane);
        Scene escena = new Scene(contenedor, 600, 300);
        stage.setScene(escena);

        Button realizarVenta = new Button("Realizar la venta");
        realizarVenta.setOnAction(e -> {
            List<ProductoDTO> productosEnCarrito = obtenerProductosEnCarrito();
            if (productosEnCarrito.isEmpty()) {
                mostrarError("El carrito de ventas está vacío");
                return;
            }

            String nombreCliente = obtenerTexto("Nombre del Cliente");
            String apellidoCliente = obtenerTexto("Apellido del Cliente");
            String telefonoCliente = obtenerTexto("Teléfono del Cliente");
            String direccionCliente = obtenerTexto("Dirección del Cliente");

            if (nombreCliente.isEmpty() || apellidoCliente.isEmpty() || telefonoCliente.isEmpty() || direccionCliente.isEmpty()) {
                mostrarError("No has completado todos los campos");
                return;
            }

            String nombreCompleto = nombreCliente + " " + apellidoCliente;
            controlador.realizarVenta(nombreCompleto, telefonoCliente, direccionCliente, productosEnCarrito);
            vaciarCarrito();
            stage.hide();
        });

        gridPane.add(realizarVenta, 2, 2);
    }

    private void actualizarTabla(TableView<ProductoDTO> tabla, Map<Integer, Integer> carrito) {
        for (ProductoDTO producto : tabla.getItems()) {
            int id = producto.getId();
            ProductoDTO productoActualizado = productoDAO.obtenerProductoPorId(id);
            int cantidadDisponible = productoActualizado.getCantidad();
            int unidadesSeleccionadas = carrito.getOrDefault(id, 0);
            int cantidadActualizada = cantidadDisponible - unidadesSeleccionadas;
            producto.setCantidad(cantidadActualizada > 0 ? cantidadActualizada : 0);
            producto.setUnidades(unidadesSeleccionadas);
        }
        tabla.refresh();
    }

    private List<ProductoDTO> obtenerProductosEnCarrito() {
        List<ProductoDTO> productosEnCarrito = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrito.entrySet()) {
            int id = entry.getKey();
            int unidades = entry.getValue();
            ProductoDTO producto = productoDAO.obtenerProductoPorId(id);
            producto.setCantidad(unidades); // Utiliza la cantidad seleccionada del carrito
            productosEnCarrito.add(producto);
        }
        return productosEnCarrito;
    }

    private void vaciarCarrito() {
        carrito.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private String obtenerTexto(String etiqueta) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle(etiqueta);

        Label etiquetaNombre = new Label(etiqueta);
        TextField textField = new TextField();

        Button button = new Button("Aceptar");
        button.setOnAction(e -> dialogStage.close());

        VBox vbox = new VBox(etiquetaNombre, textField, button);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 300, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        return textField.getText();
    }

    public void mostrar() {
        stage.show();
    }
}