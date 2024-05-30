package Gestor;

import Usuario.Usuario;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class GestorUsuario {
    private HashSet<Usuario> usuarios;
    //
    public GestorUsuario() {
        usuarios = new HashSet<>();
    }
    public HashSet<Usuario> getUsuarios() {
        return usuarios;
    }
    public void agregarUsuario(Usuario user){
        usuarios.add(user);
    }
    public void mostrarUsuarios(){
        Iterator<Usuario>iterator=usuarios.iterator();
        while (iterator.hasNext()){
            Usuario aux=iterator.next();
            System.out.println(aux.toString());
        }
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
                    usuarios.add(aux);
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
        Iterator<Usuario> usuarioIterator = usuarios.iterator();
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

}
