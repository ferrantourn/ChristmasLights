/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

/**
 *
 * @author Ferran
 */
public class ModeloFormBasico {
    private String mensaje;
    private String descErrorInterno;

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }
    
    

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public ModeloFormBasico ()
    {
        setMensaje("");
    }
    
    public ModeloFormBasico (String m)
    {
        setMensaje(m);
    }

    /**
     * @return the descErrorInterno
     */
    public String getDescErrorInterno() {
        return descErrorInterno;
    }

    /**
     * @param descErrorInterno the descErrorInterno to set
     */
    public void setDescErrorInterno(String descErrorInterno) {
        this.descErrorInterno = descErrorInterno;
    }
}
