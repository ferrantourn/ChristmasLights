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



public class Estadio {
    private int idEstadio;
    private String nombreEstadio;
    private int    capacidad;
    private String fotoUrl;

    /**
     * @return the Cesped
     */
    public TipoCesped getCesped() {
        return Cesped;
    }

    /**
     * @param Cesped the Cesped to set
     */
    public void setCesped(TipoCesped Cesped) {
        this.Cesped = Cesped;
    }

    /**
     * @return the idEstadio
     */
    public int getIdEstadio() {
        return idEstadio;
    }

    /**
     * @param idEstadio the idEstadio to set
     */
    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    /**
     * @return the fotoUrl
     */
    public String getFotoUrl() {
        return fotoUrl;
    }

    /**
     * @param fotoUrl the fotoUrl to set
     */
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
    public  enum TipoCesped { Natural, Sintetico }
    private TipoCesped Cesped;

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
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
    public Estadio (int idEstadio,String nombreEstadio, int capacidad, TipoCesped cesped, String fotoUrl)
    {
        setIdEstadio(idEstadio);
        setNombreEstadio(nombreEstadio);
        setCapacidad(capacidad);
        setCesped(cesped);
        setFotoUrl(fotoUrl);
    }
    
    public Estadio ()
    {
    }
}
