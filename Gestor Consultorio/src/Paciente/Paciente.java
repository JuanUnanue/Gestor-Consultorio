package Paciente;

import Modelo.Direccion;
import Modelo.Especialidad;
import Modelo.Persona;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Paciente extends Persona implements Serializable {
    private HashMap<Especialidad,String> historiaClinica;  //Un mismo paciente puede atenderse con distintos medicos de distntias especialidades
    //
    public Paciente() {
    }
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.historiaClinica = new HashMap<>();
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
