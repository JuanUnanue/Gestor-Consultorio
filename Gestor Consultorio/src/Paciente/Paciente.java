package Paciente;

import Modelo.Direccion;
import Modelo.Especialidad;
import Modelo.Persona;
import Turno.Turno;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Paciente extends Persona implements Serializable {
    private HashMap<Especialidad,String> historiaClinica;  //Un mismo paciente puede atenderse con distintos medicos de distntias especialidades
    private ArrayList<Turno> historialTurnos;
    //
    public Paciente() {
        this.historiaClinica = new HashMap<>();
        this.historialTurnos=new ArrayList<>();
    }
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.historiaClinica = new HashMap<>();
        this.historialTurnos=new ArrayList<>();

    }
    public void agregarTurno(Turno turno){
        this.historialTurnos.add(turno);
    }
    public HashMap<Especialidad, String> getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return "Paciente{" + super.toString()+
                "historiaClinica=" + historiaClinica +
                '}';
    }
}
