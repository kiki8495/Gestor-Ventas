package Vista;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewVendidoMas {

    private Stage stage;

    public ViewVendidoMas() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Quien ha vendido más");

        Text texto = new Text("Esta es la ventana Quien ha vendido más");

        VBox contenedor = new VBox(texto);
        Scene escena = new Scene(contenedor, 400, 300);
        stage.setScene(escena);
    }

    public void mostrar() {
        stage.show();
    }
}
