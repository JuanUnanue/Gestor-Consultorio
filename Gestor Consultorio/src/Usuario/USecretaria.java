package Usuario;

import Empleado.Secretaria;

public class USecretaria extends Usuario{
    private Secretaria secretaria;

    public USecretaria(int id, String username, String contraseña, Secretaria secretaria) {
        super(id, username, contraseña);
        this.secretaria = secretaria;
    }
}
