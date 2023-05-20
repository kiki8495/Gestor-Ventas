package Vista;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class ViewVendidoMas {
    
    private final Stage stage;

    public ViewVendidoMas() {
        this.stage = new Stage();
        crearVentana();
    }

    private void crearVentana() {
        
         stage.setTitle("Comparación de Ventas");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc;
        bc = new BarChart<>(xAxis,yAxis);
        bc.setTitle("Resumen de Ventas");
        xAxis.setLabel("Vendedor");       
        yAxis.setLabel("Ventas");
        
                XYChart.Series series1 = new XYChart.Series();
        series1.setName("Vendedor 1");       
        series1.getData().add(new XYChart.Data("Vendedor 1", 40));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Vendedor 2");
        series2.getData().add(new XYChart.Data("Vendedor 2", 35));

        bc.getData().addAll(series1, series2);
        Scene scene  = new Scene(bc,800,600);
        stage.setScene(scene);
        
    }
    public void mostrar() {
        stage.show();
    }
}