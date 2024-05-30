import Gestor.GestorUsuario;
import Medico.Agenda;
import Modelo.Direccion;
import Modelo.Persona;
import Usuario.Usuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
     /*   Usuario aux=new Usuario(1,"j","12");
        Usuario aux2=new Usuario(3,"ju","12");
        Usuario aux3=new Usuario(4,"ja","12");
        Usuario aux4=new Usuario(2,"j","12");
        GestorUsuario g=new GestorUsuario();
        g.agregarUsuario(aux);
        g.agregarUsuario(aux2);
        g.agregarUsuario(aux3);
        g.agregarUsuario(aux4);

        g.guardarUsuarios();*/
        GestorUsuario g=new GestorUsuario();
        g.leerUsuarios();
        g.mostrarUsuarios();



    }
}