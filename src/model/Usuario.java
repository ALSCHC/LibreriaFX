package model;

/**
 *
 * @author Sosa Luciano
 */
public class Usuario {
    
    private int id;
    private String usuario;
    private String contra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
        @Override
    public String toString()
    {
        return "Usuario [id = "+getId()+", usuario = "+usuario+", contra = "+contra+"]";
    }
}
