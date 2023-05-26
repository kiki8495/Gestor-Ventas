package Vista;

import Controlador.Controlador;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Modelo.ProductoDTO;
import Modelo.ProductoDAO;
import java.util.List;

public class VistaVendedor {

    private final Stage stage;
    private final Controlador controlador;
    private final String nombreVendedor;

    public VistaVendedor(Controlador controlador, String nombreVendedor) {
        this.controlador = controlador;
        this.nombreVendedor = nombreVendedor;
        this.stage = new Stage();
        this.crearVentana();
    }

    public static class Producto {

        private final int id;
        private final String nombre;
        private final String descripcion;
        private final int cantidad;
        private final double precio;

        public Producto(int id, String nombre, String descripcion, int cantidad, int precio) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getPrecio() {
            return precio;
        }
    }

    private void crearVentana() {

        stage.setTitle("Gestor de Ventas - Empresa XYZ");

        Text nombreEmpresa = new Text("Gestor Ventas");
        nombreEmpresa.setFill(Color.WHITE);
        nombreEmpresa.setStyle("-fx-font-size: 20px;");

        Text nombreVendedor = new Text("Vendedor: " + this.nombreVendedor);
        nombreVendedor.setFill(Color.WHITE);
        nombreVendedor.setStyle("-fx-font-size: 16px;");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Text fechaHora = new Text("Buenos días, la fecha es " + dtf.format(now));
        fechaHora.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        Text catalogoProductosText = new Text("El catálogo de Productos disponible es el siguiente:");
        catalogoProductosText.setFont(Font.font("Verdana", 16));

        TableView<ProductoDTO> tabla = new TableView<>();
        ObservableList<ProductoDTO> data = FXCollections.observableArrayList();

        ProductoDAO productoDAO = new ProductoDAO();
        List<ProductoDTO> productos = productoDAO.obtenerProductos();
        data.addAll(productos);
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

        TableColumn<ProductoDTO, Integer> precioColumn = new TableColumn<>("Precio Unit");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioColumn.setPrefWidth(100);

        tabla.getColumns().addAll(idColumn, nombreColumn, descripcionColumn, cantidadColumn, precioColumn);

        tabla.setItems(data);

        VBox contenedorPrincipal = new VBox(10, fechaHora, catalogoProductosText, tabla);
        contenedorPrincipal.setPadding(new Insets(10));
        contenedorPrincipal.setStyle("-fx-border-color: #ffa500; -fx-border-width: 4px;");
        contenedorPrincipal.setAlignment(Pos.TOP_LEFT);

        // Crear los botones del menú lateral
        Button boton1 = new Button("Añadir Producto");
        boton1.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");
        Button boton2 = new Button("Vender");
        boton2.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");
        Button boton3 = new Button("Recibos");
        boton3.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");
        Button boton4 = new Button("Quien ha vendido más");
        boton4.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");

        VBox menuLateral = new VBox(10, nombreEmpresa, nombreVendedor, boton1, boton2, boton3, boton4);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setStyle("-fx-background-color: #ffa500");
        menuLateral.setAlignment(Pos.TOP_LEFT);

        // Crear el contenedor total y añadir los componentes
        HBox contenedorTotal = new HBox(menuLateral, contenedorPrincipal);
        contenedorTotal.setAlignment(Pos.TOP_LEFT);

        // Agregar los eventos a los botones
        boton1.setOnAction(event -> controlador.abrirVentanaAñadirProducto());
        boton2.setOnAction(event -> controlador.abrirVentanaVender());
        boton3.setOnAction(event -> controlador.abrirVentanaRecibos());
        boton4.setOnAction(event -> controlador.abrirVentanaVendidoMas());

        Scene escena = new Scene(contenedorTotal, 800, 600);
        stage.setScene(escena);
    }

    public void mostrar() {
        this.stage.show();
    }

    public void ocultar() {
        this.stage.hide();
    }
}
