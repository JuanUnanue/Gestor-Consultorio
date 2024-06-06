import Menu.Menu;
import Modelo.Direccion;
import Paciente.Paciente;
import Paciente.GestorPaciente;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuPrincipal();
       /* Paciente j=new Paciente("Juan","Unanue", LocalDate.of(1999,12,28),42289213,new Direccion("Alvarado",1487,"Mar del Plata"));
        GestorPaciente g=new GestorPaciente();
        g.agregarPaciente(j);
        g.guardarPacientes();*/
    }
}
