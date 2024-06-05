package Usuario;

import Medico.Medico;
import Modelo.Especialidad;
import Turno.Turno;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<Usuario> iterator = listadoUsuarios.iterator();
                while (iterator.hasNext()) {
                    Usuario usuario = iterator.next();
                    JSONObject jo = new JSONObject();
                    if(!(usuario instanceof UAdmin)){

                        jo.put("apellido",);
                    }

                }

        }catch (JSONException exception) {
            exception.printStackTrace();
        }
    }
    public boolean buscarUsuario(String user, String contraseña){ //devuelve verdadero o falso si esta correcto el usuario y contraseña
        Iterator<Usuario>iterator= listadoUsuarios.iterator();
        boolean rta=false;
        while (iterator.hasNext()){
            Usuario aux= iterator.next();
            if(aux.getUsername().equals(user) && aux.getContraseña().equals(contraseña)){
                rta=true;
            }
        }
        return rta;
    }
    public Usuario obtenerUsuario(String user, String contraseña){ //devuelve el usuario
        Iterator<Usuario>iterator= listadoUsuarios.iterator();
        Usuario rta=new Usuario();
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
    private UsuarioJson convertirAUsuarioJSON(Usuario usuario) {
    UsuarioJson usuarioJSON = new UsuarioJson();
    usuarioJSON.setUsername(usuario.getUsername());
    usuarioJSON.setContraseña(usuario.getContraseña());

    if (usuario instanceof USecretaria) {
        usuarioJSON.setTipo("Secretaria");
        USecretaria u=(USecretaria) usuario;
        usuarioJSON.setDni(u.getSecretaria().getDni());
    } else if (usuario instanceof UMedico) {
        usuarioJSON.setTipo("Medico");
        UMedico m=(UMedico) usuario;
        usuarioJSON.setNroMatricula(m.getMedico().getMatricula());
    } else if (usuario instanceof UAdmin) {
        usuarioJSON.setTipo("Admin");
    } else if (usuario instanceof UPaciente){
        usuarioJSON.setTipo("Paciente");
        UPaciente u=(UPaciente) usuario;
        usuarioJSON.setDni(u.getPaciente().getDni());
    }
    return usuarioJSON;
    }
}
