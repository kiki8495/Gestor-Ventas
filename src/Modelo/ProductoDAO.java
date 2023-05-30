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

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM producto"); ResultSet rs = stmt.executeQuery()) {

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

    public int obtenerUltimoID() {
        int ultimoID = 0;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT MAX(idProducto) AS ultimoID FROM producto")) {

            if (rs.next()) {
                ultimoID = rs.getInt("ultimoID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return ultimoID;
    }

    public void agregarProducto(ProductoDTO producto) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA); PreparedStatement stmt = conn.prepareStatement("INSERT INTO producto (idProducto, nombre, descripcion, cantidad_actual, precio) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setInt(1, producto.getId());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getCantidad());
            stmt.setDouble(5, producto.getPrecio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
    public ProductoDTO obtenerProductoPorId(int idProducto) {
    ProductoDTO producto = null;

    try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM producto WHERE idProducto = ?")) {
        
        stmt.setInt(1, idProducto);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("idProducto");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            int cantidad = rs.getInt("cantidad_actual");
            double precio = rs.getInt("precio");

            producto = new ProductoDTO(id, nombre, descripcion, cantidad, (int) precio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejo de excepciones
    }

    return producto;
}

}
