package Menu;

import Usuario.GestorUsuario;
import Usuario.Usuario;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    public Menu() {
        this.scanner=new Scanner(System.in);
    }
    public void menuPrincipal() {

        String menu = "\n \t1- Iniciar Sesion\n\t2- Registrarse\n\t 3-Mostrar todos users \n\t0- Finalizar Programa\n";
        int opc;
        GestorUsuario usuarios=new GestorUsuario();
        usuarios.leerUsuarios();
        do {
            System.out.println(menu);

            opc = this.scanner.nextInt();
            switch (opc) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    Usuario user=inicioSesion(usuarios);
                    if(user instanceof Usuario){
                        MenuAdmin aux=new MenuAdmin();
                        aux.menuPrincipal(usuarios);
                    }
                    break;
                case 2:
                    usuarios.crearUsuario();
                    break;
                case 3:
                    usuarios.mostrarUsuarios();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opc != 0);
        usuarios.guardarUsuarios();
        scanner.close();
    }
    public Usuario inicioSesion(GestorUsuario usuarios) {
        System.out.println("Ingrese usuario: ");
        String user = scanner.next();
        System.out.println("Ingrese Contraseña: ");
        String contraseña = scanner.next();
        Usuario aux=usuarios.buscarUsuario(user,contraseña);
        if(aux!=null){
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + aux.getUsername());
        }
        return aux;
    }

}

