package Medico;

import Turno.IGestorTurno;
import Turno.Turno;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Agenda implements Serializable,IGestorTurno {
    private static final long serialVersionUID =  1092635162470550007L;
    private HashMap<DayOfWeek, ArrayList<Turno> >turnos;
    //
    public Agenda() {
        this.turnos=new HashMap<>();
    }

    public Agenda(HashMap<DayOfWeek, ArrayList<Turno>> turnos) {
        this.turnos = turnos;
    }

    public HashMap<DayOfWeek, ArrayList<Turno>> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<DayOfWeek, ArrayList<Turno>> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "AgendaMedico{" +
                ", turnos=" + turnos + "\n"
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
    public void inicializarTurnosDisponibles(DayOfWeek diaSemana,LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal,int matricula) {
        ArrayList<Turno> turnosDispo;
        LocalDateTime fechaActual=diaInicio;
        LocalDateTime fechaFin=fechaActual.plusMonths(3);//Cada vez que se generan turnos disponibles, se hace desde una fecha especifica hasta 3 meses depsues, a pedido del cliente
        if(turnos.containsKey(diaSemana)){
            turnosDispo=turnos.get(diaSemana);
            turnosDispo.clear();
        }else{
           turnosDispo=new ArrayList<>();
           turnos.put(diaSemana,turnosDispo);
        }
        while(fechaActual.isBefore(fechaFin)){
            if(fechaActual.getDayOfWeek()==diaSemana){
                LocalDateTime horaActual=fechaActual.with(horaInicio);
                LocalDateTime limiteHora=fechaActual.with(horaFinal);
                while(horaActual.isBefore(limiteHora)){
                    turnosDispo.add(new Turno(horaActual,matricula));
                    horaActual=horaActual.plusMinutes(15);
                }
            }
            fechaActual=fechaActual.plusDays(1);
        }
    }
}
