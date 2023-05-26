/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cristhian
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class createDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestor";
        String usuario = "root";
        String contraseña = "1234";
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos!");

            // Crear una sentencia SQL SELECT
            String consulta = "SELECT nombre FROM vendedor";

            // Crear un objeto Statement
            Statement statement = conexion.createStatement();

            // Ejecutar la consulta y obtener los resultados
            ResultSet resultados = statement.executeQuery(consulta);

            // Recorrer los resultados
            while (resultados.next()) {
                // Obtener los valores de las columna
                String nombre = resultados.getString("nombre");

                // Hacer algo con los valores obtenidos
                System.out.println("Nombre: " + nombre);
            }

            // Cerrar los recursos
            resultados.close();
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
