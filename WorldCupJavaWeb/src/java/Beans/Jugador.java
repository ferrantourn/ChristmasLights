/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;

/**
 *
 * @author Ferran
 */
public class Jugador {
    private int IdJugador;
    private String Nombre;
    private String Apellido;
    private String Posicion;
    private Equipo EquipoPertenece;
    private int totalGoles;
    
    public Jugador(int IdJugador, String nombre, String apellido, String posicion, Equipo equipo) {
        this.Nombre=nombre;
        this.IdJugador=IdJugador;
        this.Apellido=apellido;
        this.EquipoPertenece=equipo;
        this.Posicion=posicion;
    }
    
    public Jugador()
        {}
  
   

    /**
     * @return the IdJugador
     */
    public int getIdJugador() {
        return IdJugador;
    }

    /**
     * @param IdJugador the IdJugador to set
     */
    public void setIdJugador(int IdJugador) {
        this.IdJugador = IdJugador;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    

    /**
     * @return the Posicion
     */
    public String getPosicion() {
        return Posicion;
    }

    /**
     * @param Posicion the Posicion to set
     */
    public void setPosicion(String Posicion) {
        this.Posicion = Posicion;
    }

   

    /**
     * @return the EquipoProcedencia
     */
    public Equipo getEquipoPertenece() {
        return EquipoPertenece;
    }

    /**
     * @param EquipoProcedencia the EquipoProcedencia to set
     */
    public void setEquipoPertenece(Equipo EquipoPertenece) {
        this.EquipoPertenece = EquipoPertenece;
    }

    /**
     * @return the totalGoles
     */
    public int getTotalGoles() {
        return totalGoles;
    }

    /**
     * @param totalGoles the totalGoles to set
     */
    public void setTotalGoles(int totalGoles) {
        this.totalGoles = totalGoles;
    }
       
}
