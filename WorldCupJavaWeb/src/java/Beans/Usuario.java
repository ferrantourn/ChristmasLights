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
    private boolean administrador;
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
        return usuario;
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

    /**
     * @return the administrador
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
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
    
    public Usuario (String _nombre, String _apellido, String _usuario, String _password, boolean _admin)
    {
        setAdministrador(_admin);
        setApellido(_apellido);
        setNombre(_nombre);
        setUsuario(_usuario);
        setPassword(_password);
    }
    
}
