package Modelo;

import java.time.LocalDate;

public class Admin extends Persona{
    public Admin() {
    }
    public Admin(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
    }

}
