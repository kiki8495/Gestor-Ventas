package Vista;

import Controlador.Controlador;
import Modelo.ClienteDAO;
import Modelo.ClienteDTO;
import Modelo.ProductoDTO;
import java.util.List;
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

    private TextField nombre_cliente;
    private TextField cedula_cliente;
    private TextField telefono_cliente;
    private TextField direccion_cliente; // Asumiendo que también solicitas la dirección del cliente

    private final Stage stage;
    private final Controlador controlador;
    private final ClienteDAO clienteDAO;  // Agregamos el ClienteDAO

    public ViewCarrito(Controlador controlador) {
        this.controlador = controlador;
        this.stage = new Stage();
        this.clienteDAO = new ClienteDAO(); // Inicializamos el ClienteDAO
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
        Label cedulaClienteLabel = new Label("Cedula Cliente:");
        gridPane.add(cedulaClienteLabel, 0, 1);
        cedula_cliente = new TextField();
        gridPane.add(cedula_cliente, 1, 1);

        // Telefono
        Label telefonoClienteLabel = new Label("Telefono Cliente:");
        gridPane.add(telefonoClienteLabel, 0, 2);
        telefono_cliente = new TextField();
        gridPane.add(telefono_cliente, 1, 2);
        
         // Direccion
        Label direccionClienteLabel = new Label("Direccion Cliente:");
        gridPane.add(direccionClienteLabel, 0, 3);
        direccion_cliente = new TextField();
        gridPane.add(direccion_cliente, 1, 3);

        //Tabla resumen de ventas
        TableView<VistaVendedor.Producto> tabla = new TableView<>();
        List<ProductoDTO> productosEnCarrito = controlador.getCarrito();
        ObservableList<VistaVendedor.Producto> data = FXCollections.observableArrayList();

        for (ProductoDTO productoDTO : productosEnCarrito) {
            VistaVendedor.Producto producto = new VistaVendedor.Producto(
                    productoDTO.getId(),
                    productoDTO.getNombre(),
                    productoDTO.getDescripcion(),
                    productoDTO.getCantidad(),
                    (int) productoDTO.getPrecio()
            );
            data.add(producto);
        }

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
        
        Button RealizarVenta = new Button("Realizar la venta");
        RealizarVenta.setOnAction(e -> {
            String nombreCliente = nombre_cliente.getText();
            String cedulaCliente = cedula_cliente.getText();
            String telefonoCliente = telefono_cliente.getText();
            String direccionCliente = direccion_cliente.getText();  // Obtén la dirección del cliente

            if (nombreCliente.isEmpty() || cedulaCliente.isEmpty() || telefonoCliente.isEmpty() || direccionCliente.isEmpty()) {
                // mostrar un mensaje de error
                return;
            }

            // Aquí debes decidir cómo manejar el apellido del cliente
            // En este ejemplo asumimos que el campo de cedula es utilizado como apellido, ya que no tienes un campo específico para el apellido
            ClienteDTO nuevoCliente = new ClienteDTO(
                0, // El id se generará automáticamente en la base de datos
                nombreCliente,
                cedulaCliente, // Usamos cedula como apellido por el momento
                telefonoCliente);

            clienteDAO.agregarCliente(nuevoCliente); // Agrega el nuevo cliente a la base de datos

            controlador.realizarVenta(nombreCliente, cedulaCliente, telefonoCliente);
            stage.hide(); // Cerrar la ventana actual
        });

        gridPane.add(RealizarVenta, 2, 4);  // Actualizamos la posición de este botón ya que agregamos un nuevo campo
    }

    public void mostrar() {
        stage.show();
    }
}



