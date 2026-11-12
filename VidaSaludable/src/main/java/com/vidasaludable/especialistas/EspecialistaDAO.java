
package com.vidasaludable.especialistas;

import com.vidasaludable.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EspecialistaDAO {

    private final String contrasena;

    public EspecialistaDAO(String contrasena) {
        this.contrasena = contrasena;
    }

    // Registrar un nuevo especialista
    public void registrarEspecialista(String nombre,
            String apellido, String especialidad) {

        String sql = "INSERT INTO especialistas "
                + "(nombre, apellido, especialidad) "
                + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, especialidad);

            sentencia.executeUpdate();

            System.out.println("Especialista registrado correctamente.");

        } catch (SQLException error) {
            System.out.println("Error al registrar especialista: "
                    + error.getMessage());
        }
    }

    // Consultar los especialistas registrados
    public void consultarEspecialistas() {

        String sql = "SELECT * FROM especialistas";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultados = sentencia.executeQuery()) {

            System.out.println("\n--- LISTADO DE ESPECIALISTAS ---");

            boolean hayEspecialistas = false;

            while (resultados.next()) {
                hayEspecialistas = true;

                System.out.println("---------------------------");
                System.out.println("ID: "
                        + resultados.getInt("id_especialista"));
                System.out.println("Nombre: "
                        + resultados.getString("nombre"));
                System.out.println("Apellido: "
                        + resultados.getString("apellido"));
                System.out.println("Especialidad: "
                        + resultados.getString("especialidad"));
            }

            if (!hayEspecialistas) {
                System.out.println("No hay especialistas registrados.");
            }

        } catch (SQLException error) {
            System.out.println("Error al consultar especialistas: "
                    + error.getMessage());
        }
    }

    // Actualizar los datos de un especialista
    public void actualizarEspecialista(int idEspecialista,
            String nombre, String apellido, String especialidad) {

        String sql = "UPDATE especialistas SET nombre = ?, "
                + "apellido = ?, especialidad = ? "
                + "WHERE id_especialista = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, especialidad);
            sentencia.setInt(4, idEspecialista);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Especialista actualizado correctamente.");
            } else {
                System.out.println("No se encontro el especialista.");
            }

        } catch (SQLException error) {
            System.out.println("Error al actualizar especialista: "
                    + error.getMessage());
        }
    }

    // Eliminar un especialista
    public void eliminarEspecialista(int idEspecialista) {

        String sql = "DELETE FROM especialistas "
                + "WHERE id_especialista = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idEspecialista);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Especialista eliminado correctamente.");
            } else {
                System.out.println("No se encontro el especialista.");
            }

        } catch (SQLException error) {
            System.out.println("Error al eliminar especialista: "
                    + error.getMessage());
        }
    }
}
