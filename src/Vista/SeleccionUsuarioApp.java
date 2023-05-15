package Vista;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeleccionUsuarioApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        Label usuarioSeleccionado = new Label("Selecciona un vendedor");
        vBox.getChildren().add(usuarioSeleccionado);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        Button usuario1Button = new Button("Usuario 1");
        Button usuario2Button = new Button("Usuario 2");
        hBox.getChildren().addAll(usuario1Button, usuario2Button);
        vBox.getChildren().add(hBox);

        Button aceptarButton = new Button("Aceptar");
        aceptarButton.disableProperty().bind(Bindings.isEmpty(usuarioSeleccionado.textProperty()).or(usuarioSeleccionado.textProperty().isEqualTo("Selecciona un usuario")));
        vBox.getChildren().add(aceptarButton);

        usuario1Button.setOnAction(event -> usuarioSeleccionado.setText("Usuario 1"));
        usuario2Button.setOnAction(event -> usuarioSeleccionado.setText("Usuario 2"));

        aceptarButton.setOnAction(event -> {
            primaryStage.hide();

            ObservableList<String> productos = FXCollections.observableArrayList(
                    "Producto 1",
                    "Producto 2",
                    "Producto 3",
                    "Producto 4"
            );

            ListView<String> listaProductos = new ListView<>(productos);
            listaProductos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            Button comprarButton = new Button("Comprar");
            Button venderButton = new Button("Vender");

            HBox botonesBox = new HBox(10, comprarButton, venderButton);
            botonesBox.setAlignment(Pos.CENTER);

            VBox root = new VBox(10, listaProductos, botonesBox);
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.CENTER);

            Scene scene = new Scene(root, 300, 400);

            Stage newStage = new Stage();
            newStage.setTitle("Productos");
            newStage.setScene(scene);
            newStage.show();
        });

        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setTitle("Seleccionar Usuario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


