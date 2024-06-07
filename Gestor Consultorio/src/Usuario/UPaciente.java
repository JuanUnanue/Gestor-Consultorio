package Usuario;

import Paciente.Paciente;

import java.io.Serializable;

public class UPaciente extends Usuario implements Serializable {
    private Paciente paciente;
    //

    public UPaciente() {
    }

    public UPaciente(String username, String contraseña, Paciente paciente) {
        super(username, contraseña);
        this.paciente = paciente;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    @Override
    public String toString() {
        return "UPaciente{" + super.toString()+
                "paciente=" + paciente +
                '}'+"\n";
    }
}
