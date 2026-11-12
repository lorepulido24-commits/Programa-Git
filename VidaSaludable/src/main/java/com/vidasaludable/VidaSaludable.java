
package com.vidasaludable;

import com.vidasaludable.conexion.ConexionBD;
import com.vidasaludable.pacientes.PacienteDAO;
import com.vidasaludable.especialistas.EspecialistaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.vidasaludable.citas.CitaDAO;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class VidaSaludable {

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("          VIDA SALUDABLE");
        System.out.println("    Sistema de Citas Medicas");
        System.out.println("=================================");

        System.out.print("Ingrese la contraseña de MySQL: ");
        String contrasena = teclado.nextLine();

        try (Connection conexion = ConexionBD.conectar(contrasena)) {
            System.out.println("Conexion exitosa con MySQL.");

        } catch (SQLException error) {
            System.out.println("Error de conexion: "
                    + error.getMessage());
            return;
        }

        PacienteDAO pacientes = new PacienteDAO(contrasena);
        EspecialistaDAO especialistas =
                new EspecialistaDAO(contrasena);
        CitaDAO citas = new CitaDAO(contrasena);

        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestionar pacientes");
            System.out.println("2. Gestionar especialistas");
            System.out.println("3. Gestionar citas medicas");
            System.out.println("0. Salir");

            opcion = leerNumero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuPacientes(pacientes);
                    break;
                case 2:
                    menuEspecialistas(especialistas);
                    break;
                case 3:
                    menuCitas(citas);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    // Leer un numero entero
    public static int leerNumero(String mensaje) {
        System.out.print(mensaje);

        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException error) {
            System.out.println("Debe ingresar un numero valido.");
            return -1;
        }
    }

    // Menu del modulo de pacientes
    public static void menuPacientes(PacienteDAO pacientes) {

        int opcion;

        do {
            System.out.println("\n===== MENU DE PACIENTES =====");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Consultar pacientes");
            System.out.println("3. Actualizar paciente");
            System.out.println("4. Eliminar paciente");
            System.out.println("0. Volver al menu principal");

            opcion = leerNumero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = teclado.nextLine();

                    System.out.print("Documento: ");
                    String documento = teclado.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = teclado.nextLine();

                    pacientes.registrarPaciente(
                            nombre, apellido, documento, telefono);
                    break;

                case 2:
                    pacientes.consultarPacientes();
                    break;

                case 3:
                    int idActualizar = leerNumero(
                            "ID del paciente: ");

                    if (idActualizar <= 0) {
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = teclado.nextLine();

                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = teclado.nextLine();

                    System.out.print("Nuevo documento: ");
                    String nuevoDocumento = teclado.nextLine();

                    System.out.print("Nuevo telefono: ");
                    String nuevoTelefono = teclado.nextLine();

                    pacientes.actualizarPaciente(
                            idActualizar, nuevoNombre,
                            nuevoApellido, nuevoDocumento,
                            nuevoTelefono);
                    break;

                case 4:
                    int idEliminar = leerNumero(
                            "ID del paciente a eliminar: ");

                    if (idEliminar > 0) {
                        pacientes.eliminarPaciente(idEliminar);
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    // Menu del modulo de especialistas
    public static void menuEspecialistas(
            EspecialistaDAO especialistas) {

        int opcion;

        do {
            System.out.println("\n===== MENU DE ESPECIALISTAS =====");
            System.out.println("1. Registrar especialista");
            System.out.println("2. Consultar especialistas");
            System.out.println("3. Actualizar especialista");
            System.out.println("4. Eliminar especialista");
            System.out.println("0. Volver al menu principal");

            opcion = leerNumero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = teclado.nextLine();

                    System.out.print("Especialidad: ");
                    String especialidad = teclado.nextLine();

                    especialistas.registrarEspecialista(
                            nombre, apellido, especialidad);
                    break;

                case 2:
                    especialistas.consultarEspecialistas();
                    break;

                case 3:
                    int idActualizar = leerNumero(
                            "ID del especialista: ");

                    if (idActualizar <= 0) {
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = teclado.nextLine();

                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = teclado.nextLine();

                    System.out.print("Nueva especialidad: ");
                    String nuevaEspecialidad = teclado.nextLine();

                    especialistas.actualizarEspecialista(
                            idActualizar, nuevoNombre,
                            nuevoApellido, nuevaEspecialidad);
                    break;

                case 4:
                    int idEliminar = leerNumero(
                            "ID del especialista a eliminar: ");

                    if (idEliminar > 0) {
                        especialistas.eliminarEspecialista(idEliminar);
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }
    
    
    // Menu del modulo de citas medicas
    public static void menuCitas(CitaDAO citas) {

        int opcion;

        do {
            System.out.println("\n===== MENU DE CITAS MEDICAS =====");
            System.out.println("1. Registrar cita");
            System.out.println("2. Consultar citas");
            System.out.println("3. Actualizar cita");
            System.out.println("4. Eliminar cita");
            System.out.println("0. Volver al menu principal");

            opcion = leerNumero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    int idPaciente = leerNumero(
                            "ID del paciente: ");

                    int idEspecialista = leerNumero(
                            "ID del especialista: ");

                    if (idPaciente <= 0 || idEspecialista <= 0) {
                        System.out.println("Los ID deben ser positivos.");
                        break;
                    }

                    try {
                        System.out.print("Fecha (AAAA-MM-DD): ");
                        String fechaTexto = teclado.nextLine();

                        System.out.print("Hora (HH:MM): ");
                        String horaTexto = teclado.nextLine();

                        Date fecha = Date.valueOf(
                                LocalDate.parse(fechaTexto));

                        Time hora = Time.valueOf(
                                LocalTime.parse(horaTexto));

                        citas.registrarCita(
                                idPaciente, idEspecialista, fecha, hora);

                    } catch (java.time.format.DateTimeParseException error) {
                        System.out.println(
                                "Formato de fecha u hora incorrecto.");
                    }
                    break;

                case 2:
                    citas.consultarCitas();
                    break;

                case 3:
                    int idCita = leerNumero(
                            "ID de la cita a actualizar: ");

                    int nuevoEspecialista = leerNumero(
                            "Nuevo ID del especialista: ");

                    if (idCita <= 0 || nuevoEspecialista <= 0) {
                        System.out.println("Los ID deben ser positivos.");
                        break;
                    }

                    try {
                        System.out.print("Nueva fecha (AAAA-MM-DD): ");
                        String nuevaFechaTexto = teclado.nextLine();

                        System.out.print("Nueva hora (HH:MM): ");
                        String nuevaHoraTexto = teclado.nextLine();

                        System.out.print(
                                "Nuevo estado (Programada/Cancelada/Atendida): ");
                        String estado = teclado.nextLine();

                        if (!estado.equalsIgnoreCase("Programada")
                                && !estado.equalsIgnoreCase("Cancelada")
                                && !estado.equalsIgnoreCase("Atendida")) {

                            System.out.println("Estado no valido.");
                            break;
                        }

                        Date nuevaFecha = Date.valueOf(
                                LocalDate.parse(nuevaFechaTexto));

                        Time nuevaHora = Time.valueOf(
                                LocalTime.parse(nuevaHoraTexto));

                        citas.actualizarCita(
                                idCita, nuevoEspecialista,
                                nuevaFecha, nuevaHora, estado);

                    } catch (java.time.format.DateTimeParseException error) {
                        System.out.println(
                                "Formato de fecha u hora incorrecto.");
                    }
                    break;

                case 4:
                    int idEliminar = leerNumero(
                            "ID de la cita a eliminar: ");

                    if (idEliminar > 0) {
                        citas.eliminarCita(idEliminar);
                    } else {
                        System.out.println("ID no valido.");
                    }
                    break;

                case 0:
                    System.out.println(
                            "Volviendo al menu principal.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

}





