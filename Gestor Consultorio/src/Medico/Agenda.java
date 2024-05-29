package Medico;

import Gestor.IGestorTurno;
import Modelo.Turno;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Agenda implements IGestorTurno {
    private HashMap<DayOfWeek, ArrayList<Turno> >turnos;
    //
    public Agenda() {
        this.turnos=new HashMap<>();
    }

    public HashMap<DayOfWeek, ArrayList<Turno>> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<DayOfWeek, ArrayList<Turno>> turnos) {
        this.turnos = turnos;
    }

    /*
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
            }*/
    @Override
    public String toString() {
        return "AgendaMedico{" +
                ", turnos=" + turnos
            /*    ", diasLaborables=" + diasLaborables +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                '}' */;
    }
    @Override
    public void inicializarDiaSemana(DayOfWeek diaSemana){
        if(!turnos.containsKey(diaSemana)){
            turnos.put(diaSemana,new ArrayList<>());
        }
    }
    @Override
    public void inicializarTurnosDisponibles(LocalTime fechaHora,DayOfWeek diaSemana) {
        ArrayList<Turno> turnosDispo=new ArrayList<>();
        if(turnos.containsKey(diaSemana)){
            turnosDispo=turnos.get(diaSemana);
        }else{
            tu
        }
    }


}
