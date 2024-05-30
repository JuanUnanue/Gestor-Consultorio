package Usuario;

import Medico.Medico;

public class UMedico extends Usuario{
    private Medico medico;

    public UMedico(int id, String username, String contraseña, Medico medico) {
        super(username, contraseña);
        this.medico = medico;
    }

}
