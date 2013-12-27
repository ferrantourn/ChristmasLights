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
public class Partido {
    private int idPartido;
    private Equipo[] equipos = new Equipo[2];
    private Date Fecha;
    private Estadio estadio;
    
    public Partido (int idPartido, Equipo[] equipos, Date f, Estadio estadio)
    {
        setIdPartido(idPartido);
        setEquipos(equipos);
        setFecha(f);
        setEstadio(estadio);
    }
    
    public Partido ()
    {
        
    }

    /**
     * @return the equipos
     */
    public Equipo[] getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(Equipo[] equipos) {
        this.equipos= equipos;
    }

    
    /**
     * @return the Fecha
     */
    public Date getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
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

    
    /**
     * @return the idEstadio
     */
    public Estadio getEstadio() {
        return estadio;
    }

    
    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }
}
