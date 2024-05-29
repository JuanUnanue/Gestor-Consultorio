package Empleado;

import Modelo.Direccion;
import Modelo.Persona;

import java.time.LocalDate;

public class Empleado extends Persona {
    private int cantHsPorDia;
    private int precioHora;
    //
    public Empleado() {
    }
    public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, int dni, Direccion direccion, int cantHsPorDia, int precioHora) {
        super(nombre, apellido, fechaNacimiento, dni, direccion);
        this.cantHsPorDia = cantHsPorDia;
        this.precioHora = precioHora;
    }
    public int getCantHsPorDia() {
        return cantHsPorDia;
    }
    public void setCantHsPorDia(int cantHsPorDia) {
        this.cantHsPorDia = cantHsPorDia;
    }
    public int getPrecioHora() {
        return precioHora;
    }
    public void setPrecioHora(int precioHora) {
        this.precioHora = precioHora;
    }
    @Override
    public String toString() {
        return "Empleado{" +
                "cantHsPorDia=" + cantHsPorDia +
                ", precioHora=" + precioHora +
                '}';
    }
}
