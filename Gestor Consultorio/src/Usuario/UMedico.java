package Usuario;

import Medico.Medico;

import java.io.Serializable;

public class UMedico extends Usuario implements Serializable {
    private Medico medico;

    public UMedico(String username, String contraseña, Medico medico) {
        super(username, contraseña);
        this.medico = medico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "UMedico{" +
                "medico=" + medico.getApellido() + super.toString()+
                '}';
    }
}
