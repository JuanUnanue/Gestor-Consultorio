package Medico;

import Turno.IGestorTurno;
import Turno.Turno;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Agenda implements Serializable,IGestorTurno {
    private static final long serialVersionUID =  1092635162470550007L;
    private HashMap<DayOfWeek, HashSet<Turno>>turnos;
    //
    public Agenda() {
        this.turnos=new HashMap<>();
    }

    public Agenda(HashMap<DayOfWeek,HashSet<Turno>> turnos) {
        this.turnos = turnos;
    }

    public HashMap<DayOfWeek, HashSet<Turno>> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<DayOfWeek,  HashSet<Turno>> turnos) {
        this.turnos = turnos;
    }

    public void borrarTurno(DayOfWeek dia,Turno turno){
        if(turnos.containsKey(dia)){
            HashSet<Turno>hashSetTurnos=turnos.get(dia);
            Iterator<Turno>iterator= hashSetTurnos.iterator();
            while (iterator.hasNext()){
                Turno aux=iterator.next();
                if(aux.equals(turno)){
                    iterator.remove();
                }
            }
        }
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
    public void generadorTurnosDisponibles(DayOfWeek diaSemana,LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal,Object object){
        HashSet<Turno>turnosDispo=inicializarTurnosDisponibles(diaSemana,diaInicio,horaInicio,horaFinal,object);
        turnos.put(diaSemana,turnosDispo);
    }
    @Override
    public void inicializarDiaSemana(DayOfWeek diaSemana){
        if(!turnos.containsKey(diaSemana)){
            turnos.put(diaSemana,new HashSet<>());
        }
    }
    @Override
    public HashSet<Turno> inicializarTurnosDisponibles(DayOfWeek diaSemana,LocalDateTime diaInicio, LocalTime horaInicio, LocalTime horaFinal,Object object) {
        HashSet<Turno> turnosDispo=new  HashSet<>();
        LocalDateTime fechaActual=diaInicio;
        LocalDateTime fechaFin=fechaActual.plusMonths(2);//Cada vez que se generan turnos disponibles, se hace desde una fecha especifica hasta 3 meses depsues, a pedido del cliente
        while(fechaActual.isBefore(fechaFin)){
            if(fechaActual.getDayOfWeek()==diaSemana){
                LocalDateTime horaActual=fechaActual.with(horaInicio);
                LocalDateTime limiteHora=fechaActual.with(horaFinal);
                while(horaActual.isBefore(limiteHora)){
                    int matricula=(int) object;
                    turnosDispo.add(new Turno(horaActual,matricula));
                    horaActual=horaActual.plusMinutes(30);
                }
            }
            fechaActual=fechaActual.plusDays(1);
        }
        return turnosDispo;
    }
    public String borrarAgenda(){
        turnos.clear();
        String rta="";
        if(turnos.isEmpty()){
            rta+="Se borro la agenda correctamente!";
        }
        return rta;
    }
}
