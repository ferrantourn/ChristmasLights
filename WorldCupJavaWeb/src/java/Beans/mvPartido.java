/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import java.util.Date;

/**
 *
 * @author Ferran
 */
public class mvPartido extends ModeloFormBasico {
    private String equipo1;
    private String equipo2;
    private String nombreEstadio;
    private Date fechaPartido;
    private int golesEquipo1;
    private int golesEquipo2;
    private int idPartido;

    /**
     * @return the equipo1
     */
    public String getEquipo1() {
        return equipo1;
    }

    /**
     * @param equipo1 the equipo1 to set
     */
    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    /**
     * @return the equipo2
     */
    public String getEquipo2() {
        return equipo2;
    }

    /**
     * @param equipo2 the equipo2 to set
     */
    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    /**
     * @return the nombreEstadio
     */
    public String getNombreEstadio() {
        return nombreEstadio;
    }

    /**
     * @param nombreEstadio the nombreEstadio to set
     */
    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }

    /**
     * @return the fechaPartido
     */
    public Date getFechaPartido() {
        return fechaPartido;
    }

    /**
     * @param fechaPartido the fechaPartido to set
     */
    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    /**
     * @return the golesEquipo1
     */
    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    /**
     * @param golesEquipo1 the golesEquipo1 to set
     */
    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    /**
     * @return the golesEquipo2
     */
    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    /**
     * @param golesEquipo2 the golesEquipo2 to set
     */
    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    /**
     * @return the idPartido
     */
    public int getIdPartido() {
        return idPartido;
    }

    /**
     * @param idPartido the idPartido to set
     */
    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    
}
