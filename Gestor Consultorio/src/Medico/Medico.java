package Medico;

import Modelo.Direccion;
import Modelo.Especialidad;
import Modelo.Persona;
import Paciente.Paciente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

public class Medico extends Persona implements Serializable {
    private int matricula;
    private Especialidad especialidad;
    private Agenda agenda;;
    //
    public Medico() {
    }
    public Medico(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, int matricula, Especialidad especialidad) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.agenda=new Agenda();

    }
    public int getMatricula() {
        return matricula;
    }
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    protected void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        return "Medico{" + super.toString()+
                "matricula=" + matricula +
                ", especialidad=" + especialidad +
                '}';
    }
}
