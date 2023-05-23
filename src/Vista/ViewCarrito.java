package Vista;

import Controlador.Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewCarrito {

    //private final Controlador controlador;
    private TextField nombre_cliente;
    private TextField cedula_cliente;
    private TextField telefono_cliente;

    private final Stage stage;
    private Controlador controlador;

    public ViewCarrito() {
        //this.controlador = controlador;
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {

        stage.setTitle("Carrito de Ventas");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Nombre
        Label nombreClienteLabel = new Label("Nombre Cliente:");
        gridPane.add(nombreClienteLabel, 0, 0);
        nombre_cliente = new TextField();
        gridPane.add(nombre_cliente, 1, 0);

        // Cedula
        Label cedulaCliente = new Label("Cedula Cliente:");
        gridPane.add(cedulaCliente, 0, 1);
        cedula_cliente = new TextField();
        gridPane.add(cedula_cliente, 1, 1);

        // Telefono
        Label telefonoCliente = new Label("Telefono Cliente:");
        gridPane.add(telefonoCliente, 0, 2);
        telefono_cliente = new TextField();
        gridPane.add(telefono_cliente, 1, 2);

        //Tabla resumen de ventas
        TableView<VistaVendedor.Producto> tabla = new TableView<>();
        ObservableList<VistaVendedor.Producto> data = FXCollections.observableArrayList( /*   new VistaVendedor.Producto(1, "Producto 1", "detalle" , 10, 50000),
                new VistaVendedor.Producto(2, "Producto 2","detalle", 20, 60000),
                new VistaVendedor.Producto(3, "Producto 3","detalle", 30, 40000)*/);
        tabla.setItems(data);

        TableColumn<VistaVendedor.Producto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setPrefWidth(180); // Establecer el ancho preferido de la columna

        TableColumn<VistaVendedor.Producto, Double> precioColumn = new TableColumn<>("Precio Unit");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioColumn.setPrefWidth(100); // Establecer el ancho preferido de la columna

        tabla.getColumns().addAll(idColumn, nombreColumn, precioColumn);

        //Botón para agregar productos a una venta
        // Botón para registrar el producto
        Button agregarProducto = new Button("Agregar Producto");
        agregarProducto.setOnAction(e -> controlador.abrirVentanaVender());

        //agregarProducto.setOnAction(e ->controlador.abrirVentanaAñadirProducto());
        gridPane.add(agregarProducto, 3, 2);

        VBox contenedor = new VBox(gridPane, tabla);
        Scene escena = new Scene(contenedor, 400, 500);
        stage.setScene(escena);

    }

    public void mostrar() {
        stage.show();
    }
}
