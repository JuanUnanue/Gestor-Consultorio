package Modelo;

import java.time.LocalDate;

public class Paciente extends Persona{
    private int historiaClinica;
    private String antecedentes;
    //
    public Paciente() {
    }
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, int historiaClinica, String antecedentes) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.historiaClinica = historiaClinica;
        this.antecedentes = antecedentes;
    }

    public int getHistoriaClinica() {
        return historiaClinica;
    }

    public String getAntecedentes() {
        return antecedentes;
    }
    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }
    @Override
    public String toString() {
        return "Paciente{" +
                "historiaClinica=" + historiaClinica +
                ", antecedentes='" + antecedentes + '\'' +
                '}';
    }
}
