package Vista;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewRecibos {

    private Stage stage;

    public ViewRecibos() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Recibos");

        Text texto = new Text("Esta es la ventana Recibos");

        VBox contenedor = new VBox(texto);
        Scene escena = new Scene(contenedor, 400, 300);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}