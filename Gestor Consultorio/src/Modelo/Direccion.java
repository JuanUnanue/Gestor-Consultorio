package Modelo;

import java.util.Objects;

public class Direccion {
    private String calle;
    private int numero;
    private String ciudad;
    private int codigoPostal;
    ///
    public Direccion() {
    }
    public Direccion(String calle, int numero, String ciudad, int codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
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
    public int getCodigoPostal() {
        return codigoPostal;
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
    private void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direccion direccion)) return false;
        return numero == direccion.numero && codigoPostal == direccion.codigoPostal && Objects.equals(calle, direccion.calle) && Objects.equals(ciudad, direccion.ciudad);
    }
*/
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
                ", codigoPostal=" + codigoPostal +
                '}';
    }
}
