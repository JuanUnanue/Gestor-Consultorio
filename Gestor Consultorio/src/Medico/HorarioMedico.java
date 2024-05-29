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
        this.turnos = new ArrayList<>();
        this.diasLaborables = diasLaborables;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public HashSet<DayOfWeek> getDiasLaborables() {
        return diasLaborables;
    }

    public void setDiasLaborables(HashSet<DayOfWeek> diasLaborables) {
        this.diasLaborables = diasLaborables;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
    @Override
    public String toString() {
        return "HorarioMedico{" +
                "medico=" + medico +
                ", turnos=" + turnos +
                ", diasLaborables=" + diasLaborables +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                '}';
    }

}
