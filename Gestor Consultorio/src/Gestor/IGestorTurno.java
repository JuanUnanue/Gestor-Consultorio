package Gestor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface IGestorTurno {
    void inicializarTurnosDisponibles(LocalTime fechaHora,DayOfWeek diaSemana);
    void inicializarDiaSemana(DayOfWeek diaSemana);
}
