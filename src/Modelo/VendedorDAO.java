package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:mysql://localhost:3306/gestor";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "1234");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

public List<VendedorDTO> obtenerVendedores() {
    String sql = "SELECT Nombre, Apellido, Ventas, Precio_Ventas FROM vendedor";
    List<VendedorDTO> vendedores = new ArrayList<>();
    try (Connection conn = this.connect();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){

        while (rs.next()) {
            vendedores.add(new VendedorDTO(
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getInt("Ventas"),
                rs.getInt("Precio_Ventas")
            ));
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return vendedores;
}

}
