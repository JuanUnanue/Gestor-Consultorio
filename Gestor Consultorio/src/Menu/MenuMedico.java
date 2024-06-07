package Menu;

import Medico.GestorMedico;
import Medico.Medico;
import Modelo.Direccion;
import Modelo.Especialidad;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Turno.Turno;
import Usuario.GestorUsuario;
import Medico.Agenda;
import Usuario.UMedico;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MenuMedico extends Menu{
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    private HashMap<String,ArrayList<Paciente>> presentes;
    ////
    public MenuMedico(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos, HashMap<String,ArrayList<Paciente>>presentes) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.presentes = presentes;
    }
    public MenuMedico() {
    }
    /////

    public void menuPrincipal(Medico medico) {
        String menu = "\n \t1-Atender Paciente\n\t2-Buscar Paciente\n\t \n\t0- Salir al menu principal.\n";
        int opc;
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del menu principal");
                    System.out.print("\033[H\033[2J");
                    break;
                case 1:
                    atenderPaciente(medico);
                    break;
                case 2:
                    buscarPaciente();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);

    }


    public void crearUsuario() {

    }

    public void menuADMINMedicos() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int matricula=0;
        String menu = "\n \t1- Crear Medicos\n\t2-Inicializar Turnos \n\t 3-Generar Usuario para medico\n\t4-Eliminar Medico\n\t5-Mostrar todos\n\t0- Salir al menu principal.\n";
        int opc;
        Medico medico=new Medico();
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del menu principal");
                    System.out.print("\033[H\033[2J");
                    break;
                case 1:
                    System.out.println(crearMedico());
                    break;
                case 2:
                    matricula=mostrarYelegirMatricula();
                    medico=this.medicos.buscarMedico(matricula);
                    System.out.println("Perfecto! Comenzemos con la inicializacion de turnos para el Dr. "+medico.getApellido()+".");
                    inicializadorTurnos(medico);
                    break;
                case 3:
                    generarUsuario(mostrarYelegirMatricula());
                    break;
                case 4:
                    medico=medicos.buscarMedico(mostrarYelegirMatricula());
                    boolean rta=medicos.eliminar(medico);
                    if(rta){
                    System.out.println("Medico eliminado.");}
                    break;
                case 5:
                    System.out.println(medicos.mostrarMedicos());
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    public String crearMedico(){
        String rta="";
        boolean flag=true;
        int matricula=0;
        while (flag){
            System.out.println("Ingrese matricula: ");
            matricula=scanner.nextInt();
            scanner.nextLine();
            boolean aux=medicos.validacionMatricula(matricula);
            if (!aux){
                flag=false;
            }else {
                System.out.println("Ya existe un medico con ese numero de matricula. Intente nuevamente");
            }
        }
        System.out.println("Ingrese DNI: ");
        int dni=scanner.nextInt();
        scanner.nextLine();
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
        Especialidad especialidad=null;
        while (especialidad==null){
            especialidad=mostrarYelegirEspecialidad();
        }
        Medico medico=new Medico(nombre,apellido, LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad),matricula,especialidad);
        rta+=medicos.agregarMedico(medico);
        return rta;
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

    public DayOfWeek mostrarYelegirDiaSemana(){
        System.out.println("Seleccione un día de la semana:");
        DayOfWeek diaElegido = null;

        for (int i = 1; i <= 5; i++) {
            System.out.println(i + ". " + DayOfWeek.of(i));
        }
        System.out.print("Ingrese el número correspondiente al día deseado: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion >= 1 && opcion <= 5) {
            diaElegido = DayOfWeek.of(opcion);
            System.out.println("Día seleccionado: " + diaElegido);
        } else {
            System.out.println("Opción inválida. Por favor, ingrese un número válido.");
        }

        return diaElegido;
    }

    public Medico validarMatricula(){
        boolean flag=false;
        int matricula=0;
        Medico medicoUtilizar=new Medico();
        while (!flag){
            System.out.println("Ingrese matricula del medico que desea inicializar los turnos: ");
            matricula=scanner.nextInt();
            scanner.nextLine();
            boolean aux=medicos.validacionMatricula(matricula);
            if (aux){
                flag=true;
                medicoUtilizar=medicos.buscarMedico(matricula);
            }else {
                System.out.println("No existe un medico con ese numero de matricula. Intente nuevamente");
            }
        }
        return medicoUtilizar;
    }

    public void inicializadorTurnos(Medico medico){
        int flag=0;
        Agenda agenda = medico.getAgenda();
        while(flag!=1) {
            DayOfWeek dia = mostrarYelegirDiaSemana();
            System.out.println("¿A que hora inicia con los turnos?");
            int horaHinicio = scanner.nextInt();
            scanner.nextLine();
            System.out.println("¿Y a que hora termina con los turno?");
            int horaFinal = scanner.nextInt();
            scanner.nextLine();
            agenda.generadorTurnosDisponibles(dia, LocalDateTime.now(), LocalTime.of(horaHinicio, 0), LocalTime.of(horaFinal, 0), medico.getMatricula());
            System.out.println("Desea seguir agegando turnos el Dr. "+medico.getApellido()+"?\n 1.Seguir ingresando turnos disponibles      2. Salir.");
            int i=scanner.nextInt();
            scanner.nextLine();
            if(i==2){
                flag=1;
                medico.setAgenda(agenda);
                System.out.println(medico.getAgenda().toString());
            }
        }
    }

    public int mostrarYelegirMatricula(){
        int matricula = 0;
        System.out.println("Medicos Disponibles:");
        int i = 1;
        boolean rta=false;
        int flag=0;
        Iterator<Map.Entry<Especialidad,HashSet<Medico>>> entryIterator=medicos.getListadoMedicos().entrySet().iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<Especialidad,HashSet<Medico>> medicosMapa=entryIterator.next();
            HashSet<Medico>medicoHashSet=medicosMapa.getValue();
            Iterator<Medico>medicoIterator= medicoHashSet.iterator();
            while (medicoIterator.hasNext()) {
                Medico aux = medicoIterator.next();
                System.out.println(i + ")     Dr. " + aux.getApellido() + "     " + aux.getMatricula());
                i++;
            }
        }
        int opcion=0;
        while (flag==0){
            System.out.print("Ingrese la matricula deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (medicos.validacionMatricula(opcion)) {
                Medico extra=medicos.buscarMedico(opcion);
                System.out.println("Su eleccion es el Dr. "+extra.getApellido()+" "+extra.getNombre());
                System.out.println("Desea continuar? 1. Si      2. Volver a ingresar Matricula");
                i=scanner.nextInt();
                scanner.nextLine();
                if(i==1){
                    flag=1;
                }
            }
            if(flag!=1){
                System.out.println("Intente nuevamente. ");
            }
        }
        return opcion;
    }

    public void generarUsuario(int matricula){
        boolean rta=true;
        String username="";
        while (rta){
            System.out.println("Ingrese el username: ");
            username=scanner.nextLine();
            rta=usuarios.buscarUserName(username);
            if(!rta){
                System.out.println("Username disponible!");
            }else {
                System.out.println("Username no esta disponible. Intente nuevamente.");
            }
        }
        System.out.println("Ingrese una contraseña: ");
        String pass=scanner.nextLine();
        UMedico user=new UMedico(username,pass,medicos.buscarMedico(matricula));
        usuarios.agregarUsuario(user);
    }

    public void atenderPaciente(Medico medico){
        ArrayList<Paciente>pacientes=presentes.get(medico.getApellido());
        for(int i=0;i<pacientes.size();i++){
            System.out.println((i+1+". "+pacientes.get(i)));
        }
        int opcion=0;
        do{
            System.out.print("Ingrese el paciente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > pacientes.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while (opcion < 1 || opcion > pacientes.size());
        System.out.println("Atendiendo a "+pacientes.get(opcion-1).getNombre()+" "+pacientes.get(opcion-1).getApellido()+" wuauuuuuu");
        cargarHistoriaClinica(medico,pacientes.get(opcion-1));
        eliminarPresente(medico.getApellido(),pacientes.get(opcion-1));
    }

    public void cargarHistoriaClinica(Medico medico,Paciente paciente){
        HashMap<Especialidad,String>historia=paciente.getHistoriaClinica();
        String string="";
        if(historia.containsKey(medico.getEspecialidad())){
            string+= historia.get(medico.getEspecialidad());
        }
        string+="\nAtendido por el Dr. "+medico.getApellido()+" . ";
        System.out.println("Ingrese las observaciones de la consulta: ");
        string+=scanner.nextLine();
        System.out.println("terminando consulta.");
        historia.put(medico.getEspecialidad(),string);
    }

    public void buscarPaciente(){
        int dni=0;
        boolean rta=false;
        Paciente paciente=new Paciente();
        while (!rta) {
            System.out.println("Ingrese D.N.I del paciente a buscar: ");
            dni = scanner.nextInt();
            scanner.nextLine();
            rta = pacientes.buscarDNI(dni);
            if (rta) {
                paciente=pacientes.buscarPaciente(dni);
                System.out.println("Paciente encontrado:  "+paciente.getNombre()+" "+paciente.getApellido()+".");
                verHistoriaClinica(paciente.getHistoriaClinica());
                System.out.println("Desea buscar otro paciente? \n\t 1. Si            2. No, salir");
                int opc=scanner.nextInt();
                scanner.nextLine();
                if(opc==1){
                    rta=false;
                }
            }else {
                System.out.println("No existe un paciente con ese dni. Intente nuevamente.");
            }
        }
    }

    public void verHistoriaClinica(HashMap<Especialidad,String> historiaClinica){
        Iterator<Map.Entry<Especialidad,String>>entryIterator=historiaClinica.entrySet().iterator();
        ArrayList<Especialidad>especialidades=new ArrayList<>();
        while (entryIterator.hasNext()){
            Map.Entry<Especialidad,String>entry=entryIterator.next();
            especialidades.add((entry.getKey()));
        }
        for(int i=0;i< especialidades.size();i++){
            System.out.println((i+1+". "+especialidades.get(i)));
        }
        int opcion=0;
        do{
            System.out.print("Ingrese la especialidad: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > especialidades.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while (opcion < 1 || opcion > especialidades.size());
        System.out.println(historiaClinica.get(especialidades.get(opcion-1)));
    }

    public void eliminarPresente(String apellidoMedico,Paciente paciente) {
        if (presentes.containsKey(apellidoMedico)) {
            ArrayList<Paciente> listaPacientes = presentes.get(apellidoMedico);
            Iterator<Paciente> iterator = listaPacientes.iterator();
            while (iterator.hasNext()) {
                Paciente aux = iterator.next();
                if (aux.equals(paciente)) {
                    iterator.remove();
                    System.out.println("Paciente eliminado: " + paciente.getNombre() + " " + paciente.getApellido());
                }
            }
        }
    }
    /*
    public void modificarMedico(Medico medico){
    }
    */
}
