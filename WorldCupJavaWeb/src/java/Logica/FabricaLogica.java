/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

/**
 *
 * @author Ferran
 */
public class FabricaLogica {
    
    public static IEquiposLogica getEquiposLogica ()
    {
        return EquiposLogica.getInstancia();
    }
    
    public static IEstadioLogica getEstadioLogica ()
    {
        return EstadioLogica.getInstancia();
    }
}