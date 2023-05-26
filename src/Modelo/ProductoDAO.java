package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestor";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "1234";

    public List<ProductoDTO> obtenerProductos() {
        List<ProductoDTO> productos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM producto");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad_actual");
                double precio = rs.getInt("precio");

                ProductoDTO producto = new ProductoDTO(id, nombre, descripcion, cantidad, (int) precio);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return productos;
    }
}

