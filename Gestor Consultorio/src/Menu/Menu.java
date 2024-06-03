package Menu;

import Medico.GestorMedico;
import Modelo.Direccion;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Usuario.GestorUsuario;
import Usuario.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu implements IGestorMenu{
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

    public GestorPaciente getPacientes() {
        return pacientes;
    }

    ///
    @Override
    public void menuPrincipal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Iniciar Sesion\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t4-Mostrar todos los medicos \n\t0- Finalizar Programa\n";
        int opc;
        this.usuarios.leerUsuarios();
        this.pacientes.leerPaciente();
        this.medicos.leerListado();
        MenuMedico menuMedico=new MenuMedico(scanner,usuarios,pacientes,medicos);
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                   // Usuario user=inicioSesion();
                    menuMedico.menuADMINMedicos();
                    break;
                case 2:
                    crearUsuario();
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
        System.out.println("Ingrese usuario: ");
        String user = scanner.next();
        scanner.nextLine();
        System.out.println("Ingrese Contraseña: ");
        String contraseña = scanner.next();
        scanner.nextLine();
        Usuario aux=usuarios.buscarUsuario(user,contraseña);
        if(aux!=null){
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + aux.getUsername());
        }
        return aux;
    }
    ///
    public void crearUsuario(){
        boolean flag=true;
        String user="";
        while (flag){
            System.out.println("Ingrese userName: ");
            user=scanner.next();
            scanner.nextLine();
            boolean rta=usuarios.buscarUserName(user);
            if (!rta){
                flag=false;
            }else {
                System.out.println("Ese userName ya existe. Intente nuevamente");
            }
        }
        System.out.println("Ingrese contraseña: ");
        String contraseña=scanner.next();
        scanner.nextLine();
        System.out.println(usuarios.agregarUsuario(new Usuario(user,contraseña)));
    }
    ///

    ///


}


