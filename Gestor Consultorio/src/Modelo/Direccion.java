package Modelo;

import java.util.Objects;

public class Direccion {
    private String calle;
    private int numero;
    private String ciudad;
    ///
    public Direccion() {
    }
    public Direccion(String calle, int numero, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }
    public String getCalle() {
        return calle;
    }
    public int getNumero() {
        return numero;
    }
    public String getCiudad() {
        return ciudad;
    }
    private void setCalle(String calle) {
        this.calle = calle;
    }
    private void setNumero(int numero) {
        this.numero = numero;
    }
    private void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direccion direccion)) return false;
        return numero == direccion.numero && Objects.equals(calle, direccion.calle) && Objects.equals(ciudad, direccion.ciudad);
    }
    @Override
    public int hashCode() {
        return 10;
    }
    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }

}
