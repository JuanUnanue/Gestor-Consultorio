package Usuario;

public class UsuarioJson {
    private String tipo;
    private String username;
    private String contraseña;
    private int dni;
    private int nroMatricula;
    //

    public UsuarioJson() {
    }

    protected void setTipo(String tipo) {
        this.tipo = tipo;
    }
    protected void setUsername(String username) {
        this.username = username;
    }
    protected void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    protected void setDni(int dni) {
        this.dni = dni;
    }

    protected void setNroMatricula(int nroMatricula) {
        this.nroMatricula = nroMatricula;
    }

    public String getTipo() {
        return tipo;
    }
    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getDni() {
        return dni;
    }

    public int getNroMatricula() {
        return nroMatricula;
    }
}
