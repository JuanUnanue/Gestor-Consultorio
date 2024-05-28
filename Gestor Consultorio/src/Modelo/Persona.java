package Modelo;

import java.time.LocalDate;
import java.time.Period;

public class Persona {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private int edad;
    private int dni;
    private Direccion direccion;
    //
    public Persona() {
    }

    public Persona(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad(fechaNacimiento);
        this.dni = dni;
        this.direccion = direccion;
    }
    private int calcularEdad(LocalDate fechaNacimiento){
        LocalDate fechaActual=LocalDate.now();
        return Period.between(fechaNacimiento,fechaActual).getYears();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    private void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    private void setDni(int dni) {
        this.dni = dni;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    private void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", dni=" + dni +
                ", direccion=" + direccion +
                '}';
    }
}
