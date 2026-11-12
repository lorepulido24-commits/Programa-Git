
package com.vidasaludable.pacientes;

import com.vidasaludable.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    private final String contrasena;

    public PacienteDAO(String contrasena) {
        this.contrasena = contrasena;
    }

    // Registrar un nuevo paciente
    public void registrarPaciente(String nombre, String apellido,
            String documento, String telefono) {

        String sql = "INSERT INTO pacientes "
                + "(nombre, apellido, documento, telefono) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, documento);
            sentencia.setString(4, telefono);

            sentencia.executeUpdate();

            System.out.println("Paciente registrado correctamente.");

        } catch (SQLException error) {
            System.out.println("Error al registrar paciente: "
                    + error.getMessage());
        }
    }

    // Consultar todos los pacientes
    public void consultarPacientes() {

        String sql = "SELECT * FROM pacientes";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultados = sentencia.executeQuery()) {

            System.out.println("\n--- LISTADO DE PACIENTES ---");

            boolean hayPacientes = false;

            while (resultados.next()) {
                hayPacientes = true;

                System.out.println("---------------------------");
                System.out.println("ID: "
                        + resultados.getInt("id_paciente"));
                System.out.println("Nombre: "
                        + resultados.getString("nombre"));
                System.out.println("Apellido: "
                        + resultados.getString("apellido"));
                System.out.println("Documento: "
                        + resultados.getString("documento"));
                System.out.println("Telefono: "
                        + resultados.getString("telefono"));
            }

            if (!hayPacientes) {
                System.out.println("No hay pacientes registrados.");
            }

        } catch (SQLException error) {
            System.out.println("Error al consultar pacientes: "
                    + error.getMessage());
        }
    }

    // Actualizar los datos de un paciente
    public void actualizarPaciente(int idPaciente, String nombre,
            String apellido, String documento, String telefono) {

        String sql = "UPDATE pacientes SET nombre = ?, "
                + "apellido = ?, documento = ?, telefono = ? "
                + "WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, documento);
            sentencia.setString(4, telefono);
            sentencia.setInt(5, idPaciente);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("No se encontro el paciente.");
            }

        } catch (SQLException error) {
            System.out.println("Error al actualizar paciente: "
                    + error.getMessage());
        }
    }

    // Eliminar un paciente
    public void eliminarPaciente(int idPaciente) {

        String sql = "DELETE FROM pacientes WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPaciente);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se encontro el paciente.");
            }

        } catch (SQLException error) {
            System.out.println("Error al eliminar paciente: "
                    + error.getMessage());
        }
    }
}

