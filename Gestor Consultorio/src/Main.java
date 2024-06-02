import Medico.Medico;
import Medico.Agenda;
import Menu.Menu;
import Modelo.Direccion;
import Modelo.Especialidad;
import Paciente.GestorPaciente;
import Paciente.Paciente;
import Medico.GestorMedico;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
     /*  Menu m=new Menu();
       m.menuPrincipal();
        Medico m1=new Medico("Jose","Moreno",LocalDate.now(),42289213,new Direccion("Alvarado",1487,"mdp"),92829, Especialidad.Traumatologia);
        Agenda agenda=new Agenda();
        agenda.inicializarTurnosDisponibles(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalTime.of(9, 0),LocalTime.of(13, 0), m1.getMatricula());
        m1.setAgenda(agenda);
        Medico m2=new Medico("Karina","Manestar",LocalDate.now(),21461658,new Direccion("Peña",1351,"mdp"),213456, Especialidad.Clinica);
        Agenda agenda2=new Agenda();
        agenda.inicializarTurnosDisponibles(DayOfWeek.WEDNESDAY, LocalDateTime.now(), LocalTime.of(5, 0),LocalTime.of(18, 0), m2.getMatricula());
        m2.setAgenda(agenda);*/
        GestorMedico gestorMedico=new GestorMedico();
        gestorMedico.leerListado();
        System.out.println(gestorMedico.mostrarMedicos());

    }
}