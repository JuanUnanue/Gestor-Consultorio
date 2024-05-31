package Menu;

import Paciente.GestorPaciente;
import Usuario.GestorUsuario;
import Usuario.Usuario;

import java.util.Scanner;

public class MenuAdmin {
    private Scanner scanner;

    public MenuAdmin() {
        this.scanner = new Scanner(System.in);
    }

    public void menuPrincipal(GestorUsuario usuarios, GestorPaciente pacientes) {

        String menu = "\n \t1- Crear Paciente\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t0- Finalizar Programa\n";
        int opc;

        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    pacientes.crearPaciente();
                    break;
                case 2:
                    usuarios.crearUsuario();
                    break;
                case 3:
                    usuarios.mostrarUsuarios();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
        scanner.close();
    }
}
