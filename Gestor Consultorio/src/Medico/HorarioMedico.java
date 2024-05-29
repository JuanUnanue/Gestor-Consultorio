package Medico;

import Modelo.Turno;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class HorarioMedico {
    private Medico medico;
    private ArrayList<Turno> turnos;
    private HashSet<DayOfWeek> diasLaborables;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    //

    public HorarioMedico(Medico medico, ArrayList<Turno> turnos, HashSet<DayOfWeek> diasLaborables, LocalTime horaInicio, LocalTime horaFinal) {
        this.medico = medico;
        this.turnos = turnos;
        this.diasLaborables = diasLaborables;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
}
