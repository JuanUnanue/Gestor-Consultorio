package Menu;
import Medico.Medico;
import Modelo.Especialidad;
import Secretaria.GestorSecretaria;
import Secretaria.Secretaria;
import Turno.Turno;
import Usuario.UPaciente;
import Medico.GestorMedico;
import Modelo.Direccion;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Usuario.GestorUsuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Medico.Agenda;
import Usuario.Usuario;

public class MenuPaciente extends Menu{
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    private GestorSecretaria secretarias;
    private UPaciente user;
    private HashMap<String,ArrayList<Paciente>> presentes;
    ////
    public MenuPaciente(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos,GestorSecretaria secretarias, HashMap<String,ArrayList<Paciente>>presentes) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.presentes = presentes;
        this.user=new UPaciente();
        this.secretarias=secretarias;
    }

    public void setUser(UPaciente user) {
        this.user = user;
    }

    public void menuPrincipal(UPaciente paciente) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        setUser(paciente);
        String menu = "\n \t1- Sacar un turno\n\t2-Historia Clinica\n\t3-Ver Turnos\n\t8-Mostrar Todos\n\t0- Salir al menu principal\n";
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
                    sacarTurno(user.getPaciente());
                    break;
                case 2:
                    verHistoriaClinica(user.getPaciente().getHistoriaClinica());
                    break;
                case 3:
                    System.out.println(user.getPaciente().getHistorialTurnos());
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
        String menu = "\n \t1- Crear Paciente\n\t2-Eliminar Paciente \n\t 3-Mostrar Todos\n\t0- Salir al menu principal\n";
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
                case 2:
                    Paciente aux=buscarElegirPaciente();
                    boolean rta=pacientes.eliminar(aux);
                    if(rta){
                        System.out.println("Paciente eliminado correctamente.");
                    }
                    break;
                case 3:
                    System.out.println(pacientes.mostrarPacientes());
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }
    public void menuADMINusuarios() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Eliminar usuario\n\t2- Mostrar Todos\n\t0- Salir al menu principal\n";
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
                    Usuario aux=buscarElegirUsuario();
                    boolean rta=usuarios.eliminar(aux);
                    if(rta){
                        System.out.println("Paciente eliminado correctamente.");
                    }
                    break;
                case 2:
                    System.out.println(usuarios.mostrarUsuarios());
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

    public void sacarTurno(Paciente paciente){
        Medico medico=buscarElegirMedico();
        Agenda agenda=medico.getAgenda();
        DayOfWeek diaSemana=seleccionDiaDisponible(agenda);
        System.out.println("Estos son los turnos disponibles :");
        HashMap<LocalDate,HashSet<Turno>> turnosDisponibles=obtenerTurnosDisponibles(agenda.getTurnos().get(diaSemana));
        Iterator<Map.Entry<LocalDate,HashSet<Turno>>>entryIterator=turnosDisponibles.entrySet().iterator();
        LocalDate diaElegido=seleccionDiaTurno(turnosDisponibles);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = diaElegido.format(formatter);
        System.out.println("Dia elegido: "+fechaFormateada);
        HashSet<Turno>turnosDelDia=turnosDisponibles.get(diaElegido);
        Turno turno=elegirTurnoDelDia(turnosDelDia,fechaFormateada);
        turno.setDisponible(false);
        int dniPaciente = paciente.getDni();
        System.out.println("DNI del paciente: " + dniPaciente);
        turno.setDniPaciente(dniPaciente);
        paciente.agregarTurno(turno);
        System.out.println(turno);

    }

    public HashMap<LocalDate, HashSet<Turno>> obtenerTurnosDisponibles(HashSet<Turno> turnosDispo) {
        HashMap<LocalDate, HashSet<Turno>> entryMap = new HashMap<>();
        for (Turno turno : turnosDispo) {
            if (turno.isDisponible()) {
                LocalDate diaSinHora = turno.getFechaHora().toLocalDate();
                entryMap.putIfAbsent(diaSinHora, new HashSet<>());
                entryMap.get(diaSinHora).add(turno);
            }
        }
        return entryMap;
    }

    public DayOfWeek seleccionDiaDisponible(Agenda agenda){
        Iterator<Map.Entry<DayOfWeek, HashSet<Turno> >> entryIterator=agenda.getTurnos().entrySet().iterator();
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

    public LocalDate seleccionDiaTurno(HashMap<LocalDate, HashSet<Turno>> turnos) {
        Iterator<Map.Entry<LocalDate, HashSet<Turno>>> entryIterator = turnos.entrySet().iterator();
        ArrayList<LocalDate> aux = new ArrayList<>();
        System.out.println("Seleccione el día: ");
        while (entryIterator.hasNext()) {
            Map.Entry<LocalDate, HashSet<Turno>> entry = entryIterator.next();
            aux.add(entry.getKey());
        }
        Collections.sort(aux, Comparator.naturalOrder());
        for (int i = 0; i < aux.size(); i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = aux.get(i).format(formatter);
            System.out.println((i + 1 + ". " + fechaFormateada));
        }
        int opcion = 0;
        do {
            System.out.print("Ingrese el número del día elegido: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > turnos.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion < 1 || opcion > turnos.size());
        return aux.get(opcion - 1);
    }

    public Turno elegirTurnoDelDia(HashSet<Turno> turnosDispo,String dia){
        ArrayList<Turno> turnos=new ArrayList<>(turnosDispo);
        System.out.println("Estos son los turnos disponibles para el "+dia);
        for (int i=0;i< turnos.size();i++){
            System.out.println((i+1+". "+turnos.get(i)));
        }
        int opcion=0;
        do{
            System.out.print("Ingrese el número del dia: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > turnos.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while (opcion < 1 || opcion > turnos.size());
        return turnos.get(opcion-1);
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
    @Override
    public void agregarPresente(String apellidoMedico, Paciente paciente) {
        super.agregarPresente(apellidoMedico, paciente);
    }

    public Paciente buscarElegirPaciente() {
        int dni = 0;
        Paciente paciente=new Paciente();
        System.out.println("Pacientes Disponibles:");
        int i = 1;
        boolean rta = false;
        int flag = 0;
        Iterator<Paciente> iterator = pacientes.getListadoPacientes().iterator();
        while (iterator.hasNext()) {
            Paciente aux = iterator.next();
            System.out.println(i + ") " + aux.getApellido() + "     " + aux.getDni());
            i++;
        }
        int opcion = 0;
        while (flag == 0){
            System.out.print("Ingrese el dni del paciente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (pacientes.buscarDNI(opcion)) {
                paciente = pacientes.buscarPaciente(opcion);
                System.out.println("Su eleccion es " +paciente.getApellido() + " " + paciente.getNombre());
                System.out.println("Desea continuar? 1. Si      2. Volver a ingresar Matricula");
                i = scanner.nextInt();
                scanner.nextLine();
                if (i == 1) {
                    flag = 1;
                }
            }
            if (flag != 1) {
                System.out.println("Intente nuevamente. ");
            }
        }
        return paciente;
    }

    public Usuario buscarElegirUsuario() {
        int dni = 0;
        ArrayList<Usuario>arrayList=new ArrayList<>(usuarios.getListadoUsuarios());
        System.out.println("Usuarios Disponibles:");
        Usuario user=new Usuario();
        for(int i=0;i<arrayList.size();i++){
            System.out.println(i+1 + ") " + arrayList.get(i).getUsername());
        }
        System.out.print("Ingrese el número correspondiente al username deseado: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion >= 1 && opcion <= arrayList.size()) {
            user=arrayList.get(opcion-1);
            System.out.println("Usuario seleccionado: " + arrayList.get(opcion-1).getUsername());
        } else {
            System.out.println("Opción inválida. Por favor, ingrese un número válido.");
        }
        return user;
    }

}
