import Menu.Menu;
import Paciente.GestorPaciente;

public class Main {
    public static void main(String[] args) {
       /*Menu m=new Menu();
       m.menuPrincipal();*/
        GestorPaciente g=new GestorPaciente();
        g.crearPaciente();
        g.crearPaciente();
        g.guardarPacientes();
        g.leerPaciente();
        g.mostrarPacientes();

    }
}