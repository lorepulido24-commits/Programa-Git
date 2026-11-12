
package com.vidasaludable.citas;

import com.vidasaludable.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class CitaDAO {

    private final String contrasena;

    public CitaDAO(String contrasena) {
        this.contrasena = contrasena;
    }

    // Registrar una cita medica
    public void registrarCita(int idPaciente, int idEspecialista,
            Date fecha, Time hora) {

        String sql = "INSERT INTO citas "
                + "(id_paciente, id_especialista, fecha, hora, estado) "
                + "VALUES (?, ?, ?, ?, 'Programada')";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPaciente);
            sentencia.setInt(2, idEspecialista);
            sentencia.setDate(3, fecha);
            sentencia.setTime(4, hora);

            sentencia.executeUpdate();

            System.out.println("Cita registrada correctamente.");

        } catch (SQLException error) {
            System.out.println("Error al registrar cita: "
                    + error.getMessage());
        }
    }

    // Consultar todas las citas medicas
    public void consultarCitas() {

        String sql = "SELECT c.id_cita, "
                + "p.nombre AS nombre_paciente, "
                + "p.apellido AS apellido_paciente, "
                + "e.nombre AS nombre_especialista, "
                + "e.apellido AS apellido_especialista, "
                + "e.especialidad, c.fecha, c.hora, c.estado "
                + "FROM citas c "
                + "INNER JOIN pacientes p "
                + "ON c.id_paciente = p.id_paciente "
                + "INNER JOIN especialistas e "
                + "ON c.id_especialista = e.id_especialista "
                + "ORDER BY c.fecha, c.hora";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultados = sentencia.executeQuery()) {

            System.out.println("\n--- LISTADO DE CITAS MEDICAS ---");

            boolean hayCitas = false;

            while (resultados.next()) {
                hayCitas = true;

                System.out.println("---------------------------");
                System.out.println("ID cita: "
                        + resultados.getInt("id_cita"));

                System.out.println("Paciente: "
                        + resultados.getString("nombre_paciente") + " "
                        + resultados.getString("apellido_paciente"));

                System.out.println("Especialista: "
                        + resultados.getString("nombre_especialista") + " "
                        + resultados.getString("apellido_especialista"));

                System.out.println("Especialidad: "
                        + resultados.getString("especialidad"));

                System.out.println("Fecha: "
                        + resultados.getDate("fecha"));

                System.out.println("Hora: "
                        + resultados.getTime("hora"));

                System.out.println("Estado: "
                        + resultados.getString("estado"));
            }

            if (!hayCitas) {
                System.out.println("No hay citas registradas.");
            }

        } catch (SQLException error) {
            System.out.println("Error al consultar citas: "
                    + error.getMessage());
        }
    }

    // Actualizar una cita medica
    public void actualizarCita(int idCita, int idEspecialista,
            Date fecha, Time hora, String estado) {

        String sql = "UPDATE citas SET id_especialista = ?, "
                + "fecha = ?, hora = ?, estado = ? "
                + "WHERE id_cita = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idEspecialista);
            sentencia.setDate(2, fecha);
            sentencia.setTime(3, hora);
            sentencia.setString(4, estado);
            sentencia.setInt(5, idCita);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Cita actualizada correctamente.");
            } else {
                System.out.println("No se encontro la cita.");
            }

        } catch (SQLException error) {
            System.out.println("Error al actualizar cita: "
                    + error.getMessage());
        }
    }

    // Eliminar una cita medica
    public void eliminarCita(int idCita) {

        String sql = "DELETE FROM citas WHERE id_cita = ?";

        try (Connection conexion = ConexionBD.conectar(contrasena);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idCita);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Cita eliminada correctamente.");
            } else {
                System.out.println("No se encontro la cita.");
            }

        } catch (SQLException error) {
            System.out.println("Error al eliminar cita: "
                    + error.getMessage());
        }
    }
}

