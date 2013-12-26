/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Ferran
 */
public class Equipo {
    private int idEquipo;
    private String pais;
    private String entrenador;
    private boolean cabezaSerie;
 
    public Equipo (){}
    
    public Equipo (int idEquipo, String Ppais,String Pentrenador,boolean PcabezaSerie){
        setIdEquipo(idEquipo);
        setCabezaSerie(PcabezaSerie);
        setEntrenador(Pentrenador);
        setPais(Ppais);   
    }

    public Equipo (int idEquipo, String pais){
        setIdEquipo(idEquipo);
        setPais(pais);
    }
    
    /**
     * @return the nombre
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setPais(String nombre) {
        this.pais = nombre;
    }

   
    /**
     * @return the entrenador
     */
    public String getEntrenador() {
        return entrenador;
    }

    /**
     * @param entrenador the entrenador to set
     */
    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    /**
     * @return the cabezaSerie
     */
    public boolean isCabezaSerie() {
        return cabezaSerie;
    }

    /**
     * @param cabezaSerie the cabezaSerie to set
     */
    public void setCabezaSerie(boolean cabezaSerie) {
        this.cabezaSerie = cabezaSerie;
    }

    /**
     * @return the idEquipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    
    
    
}
