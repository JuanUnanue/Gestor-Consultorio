package Usuario;

import Empleado.Secretaria;

import java.io.Serializable;

public class USecretaria extends Usuario implements Serializable {
    private Secretaria secretaria;

    public USecretaria(int id, String username, String contraseña, Secretaria secretaria) {
        super(username, contraseña);
        this.secretaria = secretaria;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }
}
