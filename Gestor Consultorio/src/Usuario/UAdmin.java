package Usuario;

import Modelo.Admin;

public class UAdmin extends Usuario{
    private Admin admin;

    public UAdmin(int id, String username, String contraseña, Admin admin) {
        super(id, username, contraseña);
        this.admin = admin;
    }
}
