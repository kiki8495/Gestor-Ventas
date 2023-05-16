package main;

import Controlador.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controlador controlador = new Controlador(primaryStage);
        controlador.iniciarAplicacion();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
