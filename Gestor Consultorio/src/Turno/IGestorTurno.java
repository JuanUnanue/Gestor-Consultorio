package Turno;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public interface IGestorTurno {
    HashSet<Turno> inicializarTurnosDisponibles(DayOfWeek diaSemana, LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal, Object obejt);
    void inicializarDiaSemana(DayOfWeek diaSemana);
}
