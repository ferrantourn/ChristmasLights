/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Ferran
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String tipoUsuario;
    private long ci;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

   
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the ci
     */
    public long getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(long ci) {
        this.ci = ci;
    }
    
    public Usuario ()
    {
       
    }
    
    public Usuario (String _nombre, String _apellido, String _usuario, String _password, String _tipoUsuario)
    {
        setTipoUsuario(_tipoUsuario);
        setApellido(_apellido);
        setNombre(_nombre);
        setUsuario(_usuario);
        setPassword(_password);
    }
    
}
