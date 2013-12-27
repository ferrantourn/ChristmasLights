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
public class mvGoles extends ModeloFormBasico {
    private String jugadorNombre;
    private String jugadorApellido;
    private Integer minuto;
    private String nombreEquipo;

    /**
     * @return the jugadorNombre
     */
    public String getJugadorNombre() {
        return jugadorNombre;
    }

    /**
     * @param jugadorNombre the jugadorNombre to set
     */
    public void setJugadorNombre(String jugadorNombre) {
        this.jugadorNombre = jugadorNombre;
    }

    /**
     * @return the jugadorApellido
     */
    public String getJugadorApellido() {
        return jugadorApellido;
    }

    /**
     * @param jugadorApellido the jugadorApellido to set
     */
    public void setJugadorApellido(String jugadorApellido) {
        this.jugadorApellido = jugadorApellido;
    }

    /**
     * @return the minuto
     */
    public Integer getMinuto() {
        return minuto;
    }

    /**
     * @param minuto the minuto to set
     */
    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    /**
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * @param nombreEquipo the nombreEquipo to set
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
