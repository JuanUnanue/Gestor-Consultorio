package Usuario;


import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 5861253745568102989L;
    private int id;
    private String username;
    private String contraseña;
    private boolean activo;
    //

    public Usuario() {
    }

    public Usuario(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
        this.activo=false;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object obj) {
        boolean rta=false;
            if(obj instanceof Usuario){
                Usuario aux=(Usuario) obj;
                    if(aux.getUsername()==getUsername()){
                        rta=true;
                    }
            }
        return rta;
    }
    @Override
    public int hashCode() {
        return 10;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
