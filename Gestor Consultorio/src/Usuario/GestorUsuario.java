package Usuario;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GestorUsuario {
    private HashSet<Usuario> listadoUsuarios;
    //
    public GestorUsuario() {
        listadoUsuarios = new HashSet<>();
    }
    public HashSet<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }
    public void mostrarUsuarios(){
        Iterator<Usuario>iterator= listadoUsuarios.iterator();
        while (iterator.hasNext()){
            Usuario aux=iterator.next();
            System.out.println(aux.toString());
        }
    }
    public String agregarUsuario(Usuario user){
        listadoUsuarios.add(user);
        return "Se creo el usuario correctamente!";
    }
    //
    public void leerUsuarios() { //Trae del archivo los usaurios a un HashSet
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Usuarios.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Usuario aux;
            while (true) {
                try {
                    aux = (Usuario) objectInputStream.readObject();
                    listadoUsuarios.add(aux);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void guardarUsuarios(){
        ObjectOutputStream objectOutputStream=null;
        Iterator<Usuario> usuarioIterator = listadoUsuarios.iterator();
        try{
            FileOutputStream fileOutputStream=new FileOutputStream("Usuarios.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            while (usuarioIterator.hasNext())
            {
                Usuario aux = usuarioIterator.next();
                objectOutputStream.writeObject(aux);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }
    public Usuario buscarUsuario(String user, String contraseña){
        Iterator<Usuario>iterator= listadoUsuarios.iterator();
        Usuario rta=null;
        while (iterator.hasNext()){
            Usuario aux= iterator.next();
            if(aux.getUsername().equals(user) && aux.getContraseña().equals(contraseña)){
                rta=aux;
            }
        }
        return rta;
    }
    public boolean buscarUserName(String user) //Devuele true si existe usuario en el sistema
    {
        Iterator<Usuario>iterator= listadoUsuarios.iterator();
        boolean rta=false;
        while (iterator.hasNext()){
            Usuario aux=iterator.next();
            if(aux.getUsername().equals(user)){
                rta=true;
            }
        }
        return rta;
    }

}
