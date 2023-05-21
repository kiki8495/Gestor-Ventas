package Vista;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;

public class ViewRecibos {

    private final Stage stage;

    public ViewRecibos() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        stage.setTitle("Recibos");

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Codigo 1: Recibo 1 - Fecha 1", "Codigo 2: Recibo 2 - Fecha 2", "Codigo 3: Recibo 3 - Fecha 3"); // Añade aquí tus nombres de archivo de recibo

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            Label label = new Label(item);
                            label.setTextFill(Color.BLACK); // Aquí cambiamos el color del texto a negro
                            setGraphic(label);
                            setText(null);
                        }
                    }
                };
            }
        });

        Button button = new Button("Abrir Recibo");
        button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #ffa500; -fx-font-size: 16px;");
        button.setOnAction(e -> {
            String selectedReceipt = listView.getSelectionModel().getSelectedItem();
            if (selectedReceipt != null) {
                openPDF(selectedReceipt.split(":")[0].trim() + ".pdf"); // Asegúrate de que tus nombres de archivo de recibo corresponden a los nombres de archivo de PDF
            }
        });

        VBox vbox = new VBox(listView, button);
        vbox.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(255, 165, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void openPDF(String fileName) {
        try {
            //Cambiar la direccion donde guardes los PDFs de ejemplos, despues podremos usar la base de datos para guardar los recibos, aun estoy mirando eso pero por ahora usaremos el almacenamiento del PC
            File file = new File("D:\\Universidad\\Cuarto Semestre 2023-1\\Segundo Bloque\\Programacion Avanzada\\Segundo Corte\\Gestor-Ventas\\src\\Vista\\Recibos\\" + fileName);

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

