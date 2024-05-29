package Medico;

import Modelo.Direccion;
import Modelo.Especialidad;
import Modelo.Persona;

import java.time.LocalDate;

public class Medico extends Persona {
    private int matricula;
    private Especialidad especialidad;
    private Agenda agenda;
    private boolean pagoAlquiler;
    //
    public Medico() {
    }
    public Medico(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, int matricula, Especialidad especialidad, boolean pagoAlquiler) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.pagoAlquiler = pagoAlquiler;
        this.agenda=new Agenda();
    }
    public int getMatricula() {
        return matricula;
    }
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    public boolean isPagoAlquiler() {
        return pagoAlquiler;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public void setPagoAlquiler(boolean pagoAlquiler) {
        this.pagoAlquiler = pagoAlquiler;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "matricula=" + matricula +
                ", especialidad=" + especialidad +
                ", pagoAlquiler=" + pagoAlquiler +
                '}';
    }
}
