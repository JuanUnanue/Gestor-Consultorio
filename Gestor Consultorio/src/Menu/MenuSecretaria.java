package Menu;

import Medico.GestorMedico;
import Medico.Medico;
import Usuario.USecretaria;
import Modelo.Direccion;
import Modelo.Especialidad;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Secretaria.GestorSecretaria;
import Secretaria.Secretaria;
import Turno.Turno;
import Usuario.GestorUsuario;
import Usuario.Usuario;
import Medico.Agenda;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MenuSecretaria extends Menu{
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    private GestorSecretaria secretarias;
    private HashMap<String,ArrayList<Paciente>> presentes;
    ////
    public MenuSecretaria(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos, GestorSecretaria secretarias,HashMap<String,ArrayList<Paciente>>presentes) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.presentes = presentes;
        this.secretarias=secretarias;
    }

    public MenuSecretaria() {
    }


    ///////

    @Override
    public void menuPrincipal() {
        String menu = "\n \t1-Crear Paciente\n\t2-Presente\n\t 3-Dar un turno\n\t0- Salir al menu principal.\n";
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
                    crearPaciente();
                    break;
                case 2:
                    darPresente();
                    break;
                case 3:
                    Paciente paciente=verificarDNI();
                    sacarTurno(paciente);
                    break;
                case 4:

                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    public void menuADMINsecretaria() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Crear Secretaria\n\t2-Eliminar Secretaria \n\t3-Mostrar todos\n\t0- Salir al menu principal\n";
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
                    Secretaria aux=buscarSecretaria();
                    boolean rta=secretarias.eliminar(aux);
                    if(rta){
                        System.out.println("Secretaria eliminada correctamente.");
                    }
                    break;
                case 3:
                    System.out.println(secretarias.mostrarSecretarias());;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
    }

    @Override
    public Usuario inicioSesion() {
        return super.inicioSesion();
    }

    public void crearUsuario(){
        Secretaria secretaria=crearSecretaria();
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
        System.out.println(usuarios.agregarUsuario(new USecretaria(user,contraseña,secretaria)));
    }

    public Secretaria crearSecretaria(){
        System.out.println("Ingrese D.N.I: ");
        int dni = scanner.nextInt();
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
        Secretaria secre=new Secretaria(nombre,apellido,LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad));
        secretarias.agregarSecretaria(secre);
        return secre;
    }

    public void crearPaciente() {
        int dni=0;
        boolean rta=true;
        while (rta) {
            System.out.println("Ingrese D.N.I: ");
            dni = scanner.nextInt();
            scanner.nextLine();
            rta = pacientes.buscarDNI(dni);
            if (rta) {
                System.out.println("Ya existe un paciente con ese dni. Intente nuevamente.");
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
        Paciente aux=new Paciente(nombre,apellido, LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad));
        String string=pacientes.agregarPaciente(aux);
        System.out.println(string);
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

    public void darPresente(){
        Medico medico=buscarElegirMedico();
        Agenda agenda=medico.getAgenda();
        ArrayList<Paciente>arrayList=new ArrayList<>();
        DayOfWeek diaSemana=seleccionDiaDisponible(agenda);
        System.out.println("Estos son los turnos disponibles :");
        HashMap<LocalDate, HashSet<Turno>> turnosDisponibles=obtenerTurnosOcupados(agenda.getTurnos().get(diaSemana));
        Iterator<Map.Entry<LocalDate,HashSet<Turno>>> entryIterator=turnosDisponibles.entrySet().iterator();
        LocalDate diaElegido=seleccionDiaTurno(turnosDisponibles);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = diaElegido.format(formatter);
        System.out.println("Dia elegido: "+fechaFormateada);
        HashSet<Turno>turnosDelDia=turnosDisponibles.get(diaElegido);
        Turno turno=elegirTurnoDelDia(turnosDelDia,fechaFormateada);
        if(presentes.containsKey(medicos.buscarMedico(turno.getMatriculaMedico()).getApellido())){
            arrayList=presentes.get(medicos.buscarMedico(turno.getMatriculaMedico()).getApellido());
        }else {
            presentes.put(medicos.buscarMedico(turno.getMatriculaMedico()).getApellido(),arrayList);
        }
        arrayList.add(pacientes.buscarPaciente(turno.getPaciente()));
        agenda.borrarTurno(diaSemana,turno);
        System.out.println("Presente "+presentes);
    }

    public HashMap<LocalDate, HashSet<Turno>> obtenerTurnosOcupados(HashSet<Turno> turnosDispo) {
        HashMap<LocalDate, HashSet<Turno>> entryMap = new HashMap<>();
        for (Turno turno : turnosDispo) {
            if (!turno.isDisponible()) {
                LocalDate diaSinHora = turno.getFechaHora().toLocalDate();
                entryMap.putIfAbsent(diaSinHora, new HashSet<>());
                entryMap.get(diaSinHora).add(turno);
            }
        }
        return entryMap;
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
        ArrayList<Turno> aux=new ArrayList<>(turnosDispo);
        System.out.println("Estos son los turnos disponibles para el "+dia);
        for (int j=0;j< aux.size();j++){
            System.out.println((j+1+". "+aux.get(j)));
        }
        int opcion=0;
        do{
            System.out.print("Ingrese el número del dia: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion < 1 || opcion > aux.size()) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while (opcion < 1 || opcion > aux.size());
        return aux.get(opcion-1);
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

    public Secretaria buscarSecretaria() {
        int dni = 0;
        Secretaria secretaria=new Secretaria();
        System.out.println("Secretarias Disponibles:");
        int i = 1;
        boolean rta = false;
        int flag = 0;
        Iterator<Secretaria> iterator = secretarias.getListadoSecretarias().iterator();
        while (iterator.hasNext()) {
                Secretaria aux = iterator.next();
                System.out.println(i + ") " + aux.getApellido() + "     " + aux.getDni());
                i++;
        }
        int opcion = 0;
        while (flag == 0){
        System.out.print("Ingrese el dni de la secreteria: ");
        opcion = scanner.nextInt();
        scanner.nextLine();
        if (secretarias.validarSecretaria(opcion)) {
            secretaria = secretarias.buscar(opcion);
            System.out.println("Su eleccion es " + secretaria.getApellido() + " " + secretaria.getNombre());
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
            return secretaria;
    }

    public Paciente verificarDNI(){
        int dni=0;
        boolean rta=false;
        Paciente paciente=new Paciente();
        while (!rta) {
            System.out.println("Ingrese D.N.I: ");
            dni = scanner.nextInt();
            scanner.nextLine();
            rta = pacientes.buscarDNI(dni);
            if (rta) {
                paciente=pacientes.buscarPaciente(dni);
                System.out.println("Paciente: "+paciente.getApellido()+" "+paciente.getNombre());
            }else {
                System.out.println("No existe un paciente registrado con ese dni. Intente nuevamente.");
            }
        }
        return paciente;
    }
}
