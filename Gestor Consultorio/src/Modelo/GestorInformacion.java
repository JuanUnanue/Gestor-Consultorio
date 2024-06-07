package Modelo;

public abstract class GestorInformacion {
    public abstract void leerListado();
    public abstract void guardarListado();
    public abstract boolean eliminar(Object obj);
}
