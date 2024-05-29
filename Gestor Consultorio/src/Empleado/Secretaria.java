package Empleado;

import Modelo.Direccion;

import java.time.LocalDate;

public class Secretaria extends Empleado{
    public Secretaria(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, int cantHsPorDia, int precioHora) {
        super(nombre, apellido, fechaNacimiento, dni, direccion, cantHsPorDia, precioHora);
    }
}
