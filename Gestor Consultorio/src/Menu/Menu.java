package Menu;

import Medico.Agenda;
import Medico.GestorMedico;
import Modelo.GestorPacientePresente;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Secretaria.GestorSecretaria;
import Turno.Turno;
import Usuario.GestorUsuario;
import Usuario.Usuario;
import Usuario.UMedico;
import Usuario.UPaciente;
import Usuario.USecretaria;

import java.time.DayOfWeek;
import java.util.*;

import Usuario.UAdmin;

public class Menu {
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    private GestorSecretaria secretarias;
    private HashMap<String,ArrayList<Paciente>> presentes;
    //
    public Menu() {
        this.scanner=new Scanner(System.in);
        this.usuarios=new GestorUsuario();
        this.pacientes=new GestorPaciente();
        this.medicos=new GestorMedico();
        this.secretarias=new GestorSecretaria();
        this.presentes=new HashMap<>();
    }
    public Menu(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos, HashMap<String,ArrayList<Paciente>>presentes) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.presentes = presentes;
    }


    public GestorPaciente getPacientes() {
        return pacientes;
    }

    ///

    public void menuPrincipal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Iniciar Sesion\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t4-Mostrar todos los medicos \n\t5-Mostrar todos los pacientes \n\t6-Mostrar todas las secretarias \n\t0- Finalizar Programa\n";
        int opc;
        this.pacientes.leerListado();
        this.medicos.leerListado();
        this.secretarias.leerListado();
        this.usuarios.leerUsuarios(pacientes,medicos,secretarias);
        GestorPacientePresente presentesPacientes=new GestorPacientePresente();
        this.presentes=presentesPacientes.leerPresentes();
        MenuMedico menuMedico=new MenuMedico(scanner,usuarios,pacientes,medicos,presentes);
        MenuPaciente menuPaciente=new MenuPaciente(scanner,usuarios,pacientes,medicos,secretarias,presentes);
        MenuSecretaria menuSecretaria=new MenuSecretaria(scanner,usuarios,pacientes,medicos,secretarias,presentes);
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
                   }else if (user instanceof UAdmin){
                       System.out.println("que sera este user jaja");
                       menuMedico.menuADMINMedicos();
                   }else if(user instanceof USecretaria){
                       menuSecretaria.menuPrincipal();
                   } else if (user instanceof UMedico) {
                       menuMedico.menuPrincipal(((UMedico) user).getMedico());
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
                case 5:
                    pacientes.mostrarPacientes();
                    break;
                case 6:
                    secretarias.mostrarSecretarias();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
        usuarios.guardarListado();
        pacientes.guardarListado();
        medicos.guardarListado();
        presentesPacientes.guardarPresentes(presentes);
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
    public DayOfWeek seleccionDiaDisponible(Agenda agenda){
        Iterator<Map.Entry<DayOfWeek, HashSet<Turno>>> entryIterator=agenda.getTurnos().entrySet().iterator();
        ArrayList<DayOfWeek>diasDispo=new ArrayList<>();
        while (entryIterator.hasNext()){
            Map.Entry<DayOfWeek, HashSet<Turno>>entry=entryIterator.next();
            diasDispo.add(entry.getKey());
        }
        System.out.println("Seleccione el dia: ");
        for(int i=0;i<diasDispo.size();i++){
            System.out.println((i+1+". "+diasDispo.get(i)));
        }
        int opcion=0;
        do{
            System.out.print("Ingrese el número del dia: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > diasDispo.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while (opcion < 1 || opcion > diasDispo.size());
        return diasDispo.get(opcion-1);
    }
    ///
    public void agregarPresente(String apellidoMedico, Paciente paciente) {
        if (!presentes.containsKey(apellidoMedico)) {
            presentes.put(apellidoMedico, new ArrayList<>());
        }
        ArrayList<Paciente> listaPacientes = presentes.get(apellidoMedico);
        listaPacientes.add(paciente);
    }
}


