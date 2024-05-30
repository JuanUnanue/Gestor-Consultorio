package Gestor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IGestorTurno {
    void inicializarTurnosDisponibles(DayOfWeek diaSemana, LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal);
    void inicializarDiaSemana(DayOfWeek diaSemana);
}
