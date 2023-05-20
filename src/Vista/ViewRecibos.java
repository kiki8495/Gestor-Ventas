package Vista;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewRecibos {

    private final Stage stage;

    public ViewRecibos() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Recibos");

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Recibo 1", "Recibo 2", "Recibo 3"); // Añade aquí tus nombres de archivo de recibo

        Button button = new Button("Abrir Recibo");
        button.setOnAction(e -> {
            String selectedReceipt = listView.getSelectionModel().getSelectedItem();
            if (selectedReceipt != null) {
                openPDF(selectedReceipt + ".pdf"); // Asegúrate de que tus nombres de archivo de recibo corresponden a los nombres de archivo de PDF
            }
        });

        VBox vbox = new VBox(listView, button);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void openPDF(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Awt Desktop no está disponible.");
                }
            } else {
                System.out.println("El archivo no existe.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mostrar() {
        stage.show();
    }
}
