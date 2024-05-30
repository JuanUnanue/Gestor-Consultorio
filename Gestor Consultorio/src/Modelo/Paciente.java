package Modelo;

import java.time.LocalDate;

public class Paciente extends Persona{
    private String historiaClinica;
    //
    public Paciente() {
    }
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, String historiaClinica) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.historiaClinica = historiaClinica;
    }

    public String getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "historiaClinica=" + historiaClinica +
                '}';
    }
}
