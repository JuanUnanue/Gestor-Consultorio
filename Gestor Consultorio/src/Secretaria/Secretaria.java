package Secretaria;

import Modelo.Direccion;
import Modelo.Persona;

import java.io.Serializable;
import java.time.LocalDate;

public class Secretaria extends Persona implements Serializable {

    public Secretaria(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
    }

    public Secretaria() {
    }

    @Override
    public String toString() {
        return "Secretaria{"+ super.getApellido()+ " "+ super.getNombre()+ " "+super.getDni()+"}";
    }
}
