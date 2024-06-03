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

    public GestorPaciente getPacientes() {
        return pacientes;
    }

    ///
    public void menuPrincipal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Iniciar Sesion\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t0- Finalizar Programa\n";
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

        String menu = "\n \t1- Pacientes\n\t2-Medicos \n\t 3-\n\t0- Salir al menu principal\n";
        int opc;
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del menu ADMIN");
                    break;
                case 1:
                    menuADMINPacientes();
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
    public void menuADMINPacientes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Crear Paciente\n\t2-Mostrar Pacientes \n\t 3-\n\t0- Salir al menu principal\n";
        int opc;
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo al menu principal");
                    break;
                case 1:
                    crearPaciente();
                    scanner.nextLine();
                    break;
                case 2:
                    pacientes.mostrarPacientes();
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
    public void crearPaciente(){
        boolean flag=true;
        int dni=0;
        while (flag){
            System.out.println("Ingrese D.N.I: ");
            dni=scanner.nextInt();
            scanner.nextLine();
            boolean rta=pacientes.buscarDNI(dni);
            if (!rta){
                flag=false;
            }else {
                System.out.println("Ya existe un paciente con ese numero de documento. Intente nuevamente");
            }
        }
        System.out.println("Ingrese Nombre: ");
        String nombre=scanner.nextLine();
        System.out.println("Ingrese Apellido: ");
        String apellido=scanner.nextLine();
        System.out.println("Ingrese fecha de nacimiento.\nDia:  ");
        int dia=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Mes:");
        int mes=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Año:  ");
        int año=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese Calle: ");
        String calle=scanner.nextLine();
        System.out.println("Ingrese Numero: ");
        int nro=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese Ciudad: ");
        String ciudad=scanner.nextLine();
        System.out.println(pacientes.agregarPaciente((new Paciente(nombre,apellido, LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad)))));
    }
    ///


}


