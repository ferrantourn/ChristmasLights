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
public class Gol {
    private Integer IdGol;
    private Partido partido;
    private Jugador jugador;
    private Integer minuto;

    public Gol (Integer idgol,Partido p, Jugador j, Integer min)
    {
        setIdGol(idgol);
        setPartido(p);
        setJugador(j);
        setMinuto(min);
    }
    
    public Gol ()
    {
        
    }
    
    /**
     * @return the IdGol
     */
    public Integer getIdGol() {
        return IdGol;
    }

    /**
     * @param IdGol the IdGol to set
     */
    public void setIdGol(Integer IdGol) {
        this.IdGol = IdGol;
    }

    /**
     * @return the partido
     */
    public Partido getPartido() {
        return partido;
    }

    /**
     * @param partido the partido to set
     */
    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    /**
     * @return the jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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
}
