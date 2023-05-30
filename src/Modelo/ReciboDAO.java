package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReciboDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestor";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public void guardarRecibo(int idVenta, byte[] contenidoRecibo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO recibo (idVenta, contenido) VALUES (?, ?)")) {
            stmt.setInt(1, idVenta);
            stmt.setBytes(2, contenidoRecibo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}