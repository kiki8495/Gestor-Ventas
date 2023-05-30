package Modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/gestor";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public List<VentaDTO> obtenerVentas() {
        List<VentaDTO> ventas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM venta"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idVenta = rs.getInt("idVenta");
                int vendedorId = rs.getInt("vendedor_id");
                int clienteId = rs.getInt("cliente_id");
                int productoId = rs.getInt("producto_id");
                int formaPago = rs.getInt("forma_pago");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                int cantidad = rs.getInt("cantidad");
                int precioTotal = rs.getInt("precioTotal");

                VentaDTO venta = new VentaDTO(idVenta, vendedorId, clienteId, productoId, formaPago, fecha, cantidad, precioTotal);
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return ventas;
    }

    public void agregarVenta(VentaDTO venta) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA); PreparedStatement stmt = conn.prepareStatement("INSERT INTO venta (idVenta, vendedor_id, cliente_id, producto_id, forma_pago, fecha, cantidad, precioTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1, venta.getIdVenta());
            stmt.setInt(2, venta.getVendedorId());
            stmt.setInt(3, venta.getClienteId());
            stmt.setInt(4, venta.getProductoId());
            stmt.setInt(5, venta.getFormaPago());
            stmt.setTimestamp(6, Timestamp.valueOf(venta.getFecha()));
            stmt.setInt(7, venta.getCantidad());
            stmt.setDouble(8, venta.getPrecioTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public int obtenerUltimoIdVenta() {
        int ultimoIdVenta = 0;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT MAX(idVenta) FROM venta")) {

            if (rs.next()) {
                ultimoIdVenta = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return ultimoIdVenta;
    }

    public void insertarVenta(VentaDTO venta) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA); PreparedStatement stmt = conn.prepareStatement("INSERT INTO venta (idVenta, vendedor_id, cliente_id, producto_id, forma_pago, fecha, cantidad, precioTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1, venta.getIdVenta());
            stmt.setInt(2, venta.getVendedorId());
            stmt.setInt(3, venta.getClienteId());
            stmt.setInt(4, venta.getProductoId());
            stmt.setInt(5, venta.getFormaPago());
            stmt.setTimestamp(6, Timestamp.valueOf(venta.getFecha()));
            stmt.setInt(7, venta.getCantidad());
            stmt.setInt(8, venta.getPrecioTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
