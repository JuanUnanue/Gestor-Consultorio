package Usuario;

import java.io.Serializable;

public class UAdmin extends Usuario implements Serializable {
    public UAdmin(String username, String contraseña) {
        super(username, contraseña);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "UAdmin{}"+"\n";
    }
}
