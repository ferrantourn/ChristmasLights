/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

/**
 *
 * @author Ferran
 */
public class FabricaDatos  {
    
    public static IEquiposDatos getEquiposDatos ()
    {
        return EquipoDatos.getInstancia();
    }
    
    public static IEstadioDatos getEstadioDatos ()
    {
        return EstadioDatos.getInstancia();
    }
    
    public static IJugadorDatos getJugadorDatos ()
    {
        return JugadorDatos.getInstancia();
    }
    
    public static IUsuarioDatos getUsuarioDatos ()
    {
        return UsuarioDatos.getInstancia();
    }
   
    public static IReservaDatos getReservaDatos ()
    {
        return ReservaDatos.getInstancia();
    }
    
    public static IPartidosDatos getPartidosDatos ()
    {
        return PartidosDatos.getInstancia();
    }
}
