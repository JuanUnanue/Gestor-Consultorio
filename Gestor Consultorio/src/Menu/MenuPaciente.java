package Menu;
import Medico.Medico;
import Modelo.Especialidad;
import Turno.Turno;
import Usuario.UPaciente;
import Medico.GestorMedico;
import Modelo.Direccion;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Usuario.GestorUsuario;
import Usuario.Usuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import Medico.Agenda;
public class MenuPaciente extends Menu{
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    private UPaciente user;
    //
    public MenuPaciente(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.user=new UPaciente();
    }

    public void setUser(UPaciente user) {
        this.user = user;
    }

    public void menuPrincipal(UPaciente paciente) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        setUser(paciente);
        String menu = "\n \t1- Turnos\n\t2- \n\t 3-\n\t8-Mostrar Todos\n\t0- Salir al menu principal\n";
        int opc;
        do {
            System.out.println(menu);

            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo al menu principal");
                    break;
                case 1:
                    buscarElegirMedico();
                    System.out.println("wuauuuu");
                    break;
                case 3:

                    break;
                case 8:
                    pacientes.mostrarPacientes();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    //////////////////////////////////////////////////////////
    public void menuADMINPacientes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Crear Paciente\n\t2-Mostrar Pacientes \n\t 3-Turnos\n\t8-Mostrar Todos\n\t0- Salir al menu principal\n";
        int opc;
        do {
            System.out.println(menu);

            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 0:
                    System.out.println("Saliendo al menu principal");
                    break;
                case 1:
                    crearUsuario();
                    break;
                case 3:

                    break;
                case 8:
                    pacientes.mostrarPacientes();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    public Paciente crearPaciente(int dni){
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
        Paciente aux=new Paciente(nombre,apellido, LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad));
        System.out.println(pacientes.agregarPaciente(aux));
        return aux;
    }

    @Override
    public void crearUsuario() {
        int dni=0;
        Paciente paciente=new Paciente();
        System.out.println("Ingrese D.N.I: ");
        dni=scanner.nextInt();
        scanner.nextLine();
        boolean rta=pacientes.buscarDNI(dni);
        if (!rta){
            paciente=crearPaciente(dni);
        }else {
            paciente=pacientes.buscarPaciente(dni);
            System.out.println("Bienvenido de vuelta "+paciente.getNombre()+".");
        }
        boolean flag=true;
        String user="";
        while (flag){
            System.out.println("Ingrese userName: ");
            user=scanner.next();
            scanner.nextLine();
            boolean rta2=usuarios.buscarUserName(user);
            if (!rta2){
                flag=false;
            }else {
                System.out.println("Ese userName ya existe. Intente nuevamente");
            }
        }
        System.out.println("Ingrese contraseña: ");
        String contraseña=scanner.next();
        scanner.nextLine();
        System.out.println(usuarios.agregarUsuario(new UPaciente(user,contraseña,paciente)));
    }
    public Especialidad mostrarYelegirEspecialidad(){
        System.out.println("Seleccione una especialidad:");
        Especialidad especialidadElegida = null;
        for (Especialidad especialidad : Especialidad.values()) {
            System.out.println((especialidad.ordinal() + 1) + ". " + especialidad);
        }
        System.out.print("Ingrese el número correspondiente a la especialidad deseada: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion >= 1 && opcion <= Especialidad.values().length) {
            especialidadElegida = Especialidad.values()[opcion - 1];
            System.out.println("Especialidad seleccionada: " + especialidadElegida);
        } else {
            System.out.println("Opción inválida. Por favor, ingrese un número válido.");
        }
        return especialidadElegida;
    }

    public Medico buscarElegirMedico(){
        Iterator<Map.Entry<Especialidad, HashSet<Medico>>> entryIterator=medicos.getListadoMedicos().entrySet().iterator();
        ArrayList<Especialidad>arrayEspecialidades=new ArrayList<>();
        while (entryIterator.hasNext()){
            Map.Entry<Especialidad, HashSet<Medico>> entry = entryIterator.next();
            arrayEspecialidades.add(entry.getKey());
        }
        System.out.println("Seleccione una especialidad:");

        for (int i = 0; i < arrayEspecialidades.size(); i++) {
            System.out.println((i + 1) + ". " + arrayEspecialidades.get(i));
        }
        int opcion;
        do {
            System.out.print("Ingrese el número de la especialidad: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > arrayEspecialidades.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion < 1 || opcion > arrayEspecialidades.size());
        Especialidad especialidadSeleccionada = arrayEspecialidades.get(opcion - 1);
        Medico aux=seleccionarMedico(medicos.getListadoMedicos().get(especialidadSeleccionada));
        return aux;
    }
    public Medico seleccionarMedico(HashSet<Medico>listado){
    ArrayList<Medico> listaMedicos=new ArrayList<>(listado);
    System.out.println("Seleccione un médico:");
    for(int i=0;i< listaMedicos.size();i++){
        Medico medico = listaMedicos.get(i);
        System.out.println((i + 1) + ". " + medico.getNombre() + " " + medico.getApellido());
    }
        int opcion;
        do {
            System.out.print("Ingrese el número del médico: ");
            opcion = scanner.nextInt();
            if (opcion < 1 || opcion > listaMedicos.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion < 1 || opcion > listaMedicos.size());

        Medico medicoSeleccionado = listaMedicos.get(opcion - 1);
        System.out.println("Médico seleccionado: " + medicoSeleccionado.getNombre() + " " + medicoSeleccionado.getApellido());
        return medicoSeleccionado;
    }
    public void sacarTurno(){
        Medico medico=buscarElegirMedico();
        Agenda agenda=medico.getAgenda();
        DayOfWeek dia=seleccionDiaDisponible(agenda);
        System.out.println("Estos son los turnos disponibles :");


    }
    public DayOfWeek seleccionDiaDisponible(Agenda agenda){
        Iterator<Map.Entry<DayOfWeek, ArrayList<Turno> >> entryIterator=agenda.getTurnos().entrySet().iterator();
        ArrayList<DayOfWeek>diasDispo=new ArrayList<>();
        while (entryIterator.hasNext()){
            Map.Entry<DayOfWeek, ArrayList<Turno>>entry=entryIterator.next();
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
}
