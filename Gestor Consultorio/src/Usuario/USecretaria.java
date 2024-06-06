package Usuario;

import Secretaria.Secretaria;

import java.io.Serializable;

public class USecretaria extends Usuario implements Serializable {
    private Secretaria secretaria;

    public USecretaria(String username, String contraseña, Secretaria secretaria) {
        super(username, contraseña);
        this.secretaria = secretaria;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    @Override
    public String toString() {
        return "USecretaria{" + super.toString()+
                "secretaria=" + secretaria +
                '}';
    }
}
