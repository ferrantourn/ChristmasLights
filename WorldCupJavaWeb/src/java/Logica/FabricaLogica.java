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
    
    public static IJugadorLogica getJugadorLogica ()
    {
        return JugadorLogica.getInstancia();
    }
    
    public static IEstadioLogica getEstadioLogica ()
    {
        return EstadioLogica.getInstancia();
    }
    
    public static IUsuarioLogica getUsuarioLogica ()
    {
        return UsuarioLogica.getInstancia();
    }
    public static IReservaLogica getReservaLogica ()
    {
        return ReservaLogica.getInstancia();
    }
    
     public static IPartidosLogica getPartidosLogica ()
    {
        return PartidosLogica.getInstancia();
    }
     
      public static IConsultasLogica getConsultasLogica ()
    {
        return ConsultasLogica.getInstancia();
    }
}
