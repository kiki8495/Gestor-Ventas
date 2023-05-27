package Vista;

import Modelo.VendedorDTO;
import java.util.List;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class ViewVendidoMas {

    private final Stage stage;

    public ViewVendidoMas(List<VendedorDTO> vendedores) {
        this.stage = new Stage();
        crearVentana(vendedores);
    }

    private void crearVentana(List<VendedorDTO> vendedores) {

        stage.setTitle("Comparación de Ventas");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc;
        bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Resumen de Ventas");
        xAxis.setLabel("Vendedor");
        yAxis.setLabel("Ventas");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(vendedores.get(0).getNombre());
        series1.getData().add(new XYChart.Data(
                vendedores.get(0).getNombre() + "\n$" + vendedores.get(0).getPrecioVentas(),
                vendedores.get(0).getVentas()
        ));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName(vendedores.get(1).getNombre());
        series2.getData().add(new XYChart.Data(
                vendedores.get(1).getNombre() + "\n$" + vendedores.get(1).getPrecioVentas(),
                vendedores.get(1).getVentas()
        ));

        bc.getData().addAll(series1, series2);
        Scene scene = new Scene(bc, 800, 600);
        stage.setScene(scene);

    }

    public void mostrar() {
        stage.show();
    }
}
