package Menu;

import Medico.GestorMedico;
import Paciente.GestorPaciente;
import Usuario.GestorUsuario;
import Usuario.Usuario;
import Usuario.UPaciente;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    //
    public Menu() {
        this.scanner=new Scanner(System.in);
        this.usuarios=new GestorUsuario();
        this.pacientes=new GestorPaciente();
        this.medicos=new GestorMedico();
    }

    public Menu(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
    }

    public GestorPaciente getPacientes() {
        return pacientes;
    }

    ///

    public void menuPrincipal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Iniciar Sesion\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t4-Mostrar todos los medicos \n\t0- Finalizar Programa\n";
        int opc;
        this.pacientes.leerPaciente();
        this.medicos.leerListado();
        this.usuarios.leerUsuarios(pacientes,medicos);
        MenuMedico menuMedico=new MenuMedico(scanner,usuarios,pacientes,medicos);
        MenuPaciente menuPaciente=new MenuPaciente(scanner,usuarios,pacientes,medicos);
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                   Usuario user=inicioSesion();
                   if(user instanceof UPaciente){
                       UPaciente paciente=(UPaciente) user;
                       menuPaciente.menuPrincipal(paciente);
                   }else {
                       System.out.println("que sera este user jaja");
                       menuMedico.menuADMINMedicos();
                   }

                    break;
                case 2:
                    menuPaciente.crearUsuario();
                    break;
                case 3:
                    usuarios.mostrarUsuarios();
                    break;
                case 4:
                    System.out.println(medicos.mostrarMedicos());
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
        usuarios.guardarUsuarios();
        pacientes.guardarPacientes();
        medicos.guardarListado();
        scanner.close();
    }
    public void menuADMIN() {

        String menu = "\n \t1- Pacientes\n\t2-Medicos \n\t 3-Secretarias\n\t\n\t0- Salir al menu principal\n";
        int opc;
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del menu ADMIN");
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    ///
    public Usuario inicioSesion() {
        int flag=0;
        Usuario aux=new Usuario();
        boolean rta=false;
        while (flag==0) {
            System.out.println("Ingrese usuario: ");
            String user = scanner.next();
            scanner.nextLine();
            System.out.println("Ingrese Contraseña: ");
            String contraseña = scanner.next();
            scanner.nextLine();
            rta = usuarios.buscarUsuario(user, contraseña);
            if (rta) {
                aux=usuarios.obtenerUsuario(user,contraseña);
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + aux.getUsername());
                flag=1;
            }else {
                System.out.println("Usuario y contraseña incorrectos. Intente nuevamente.");
            }
        }
        return aux;
    }
    ///
    public void crearUsuario(){

    }
    ///

    ///


}


