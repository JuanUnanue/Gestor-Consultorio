package Menu;

import Medico.GestorMedico;
import Medico.Medico;
import Modelo.Direccion;
import Modelo.Especialidad;
import Paciente.GestorPaciente;
import Usuario.GestorUsuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MenuMedico extends Menu{
    private Scanner scanner;
    private GestorUsuario usuarios;
    private GestorPaciente pacientes;
    private GestorMedico medicos;
    //
    public MenuMedico(Scanner scanner, GestorUsuario usuarios, GestorPaciente pacientes, GestorMedico medicos) {
        this.scanner = scanner;
        this.usuarios = usuarios;
        this.pacientes = pacientes;
        this.medicos = medicos;
    }
    public void menuADMINMedicos() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String menu = "\n \t1- Crear Medicos\n\t2-Inicializar Turnos \n\t 3-\n\t0- Salir al menu principal.\n";
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
                    crearMedico();
                    break;
                case 2:
                    int matricula=mostrarYelegirMatricula();
                    Medico medico=medicos.buscarMedico(matricula);
                    System.out.println("Perfecto! Comenzemos con la inicializacion para el Dr. "+medico.getApellido()+".");
                    System.out.println("wuau");
                    break;
                case 3:

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
        System.out.println(medicos.agregarMedico(medico));
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

        // Validar la opción ingresada
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
        Dayofweek dia=mostrarYelegirDiaSemana();

    }
    //public void inicializarTurnosDisponibles(DayOfWeek diaSemana,LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal,int matricula)

    public int mostrarYelegirMatricula(){
        int matricula = 0;
        System.out.println("Medicos Disponibles:");
        int i = 1;
        boolean rta=false;
        int flag=0;
        Iterator<Medico>iterator=medicos.getListadoMedicos().iterator();
        while (iterator.hasNext()){
            Medico aux=iterator.next();
            System.out.println(i+")     Dr. "+aux.getApellido()+"     "+aux.getMatricula());
            i++;
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

}
