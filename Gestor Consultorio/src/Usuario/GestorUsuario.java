package Usuario;

import Modelo.GestorInformacion;
import Modelo.Json;
import Paciente.GestorPaciente;
import Secretaria.GestorSecretaria;
import Secretaria.Secretaria;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Medico.GestorMedico;

import java.util.*;

public class GestorUsuario extends GestorInformacion {
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
    public void leerUsuarios(GestorPaciente pacientes, GestorMedico medicos, GestorSecretaria secretarias) { //Trae del archivo los usuarios a un HashSet
        try {
            JSONObject object  = new JSONObject(Json.leer("usuarios"));
            JSONArray jsonArray = object.getJSONArray("usuarios");

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject usuarioJSON = jsonArray.getJSONObject(i);
                String tipo = usuarioJSON.getString("tipo");
                String username = usuarioJSON.getString("username");
                String contraseña = usuarioJSON.getString("contraseña");
                int dni=0;
                switch (tipo) {
                    case "Secretaria":
                        dni= usuarioJSON.getInt("dni");
                        USecretaria uSecretaria=new USecretaria(username,contraseña,secretarias.buscarSecretaria(dni));
                        listadoUsuarios.add(uSecretaria);
                        break;
                    case "Paciente":
                        dni= usuarioJSON.getInt("dni");
                        UPaciente user=new UPaciente(username,contraseña,pacientes.buscarPaciente(dni));
                        listadoUsuarios.add(user);
                        break;
                    case "Medico":
                        int matricula= usuarioJSON.getInt("nroMatricula");
                        UMedico uMedico=new UMedico(username,contraseña,medicos.buscarMedico(matricula));
                        listadoUsuarios.add(uMedico);
                        break;
                    case "Admin":
                        listadoUsuarios.add(new UAdmin(username, contraseña));
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void guardarListado(){
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<Usuario> iterator = listadoUsuarios.iterator();
                while (iterator.hasNext()) {
                    Usuario usuario = iterator.next();
                    UsuarioJson usuarioJson=convertirAUsuarioJSON(usuario);
                    JSONObject jo = new JSONObject(usuarioJson);
                    jsonArray.put(jo);
                }
            JSONObject jsonObject=new JSONObject();
                jsonObject.put("usuarios",jsonArray);
            Json.grabar(jsonObject,"usuarios");
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
            USecretaria u = (USecretaria) usuario;
            Secretaria secretaria = u.getSecretaria();
            usuarioJSON.setDni(secretaria.getDni());
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
    public void borrarUsuariosMedicos() {
        HashSet<Usuario> copiaListadoUsuarios = new HashSet<>(listadoUsuarios);
        for (Usuario usuario : copiaListadoUsuarios) {
            if (usuario instanceof UAdmin) {
            }else {
                listadoUsuarios.remove(usuario);
            }
        }
    }
    public void eliminarUPacientes(){
        Iterator<Usuario> iterator = listadoUsuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario instanceof UPaciente) {
                iterator.remove();
            }
        }
    }

    @Override
    public void leerListado() {

    }
}
