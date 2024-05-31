package Usuario;

import Paciente.Paciente;

public class UPaciente extends Usuario{
    private Paciente paciente;
    //
    public UPaciente(int id, String username, String contraseña, Paciente paciente) {
        super(username, contraseña);
        this.paciente = paciente;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    @Override
    public String toString() {
        return "UPaciente{" +
                "paciente=" + paciente +
                '}';
    }
}
