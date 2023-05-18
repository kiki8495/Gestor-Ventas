package Vista;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewVender {

    private Stage stage;

    public ViewVender() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Vender");

        Text texto = new Text("Esta es la ventana Vender");

        VBox contenedor = new VBox(texto);
        Scene escena = new Scene(contenedor, 400, 300);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}

