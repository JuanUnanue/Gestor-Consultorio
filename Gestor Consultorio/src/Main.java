import Medico.Agenda;
import Modelo.Direccion;
import Modelo.Persona;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Agenda a=new Agenda();
        a.inicializarTurnosDisponibles(DayOfWeek.MONDAY,LocalDateTime.of(2024,6,1,9,0),LocalTime.of(9,0),LocalTime.of(17,0));
        a.inicializarTurnosDisponibles(DayOfWeek.FRIDAY,LocalDateTime.of(2024,6,1,9,0),LocalTime.of(8,0),LocalTime.of(10,0));
        a.inicializarDiaSemana(DayOfWeek.WEDNESDAY);
        System.out.println(a);
    }
}