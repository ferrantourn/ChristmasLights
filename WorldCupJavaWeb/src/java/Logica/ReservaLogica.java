/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IReservaDatos;
import Beans.Reserva;

/**
 *
 * @author Ferran
 */
public class ReservaLogica implements IReservaLogica {
     private static ReservaLogica instancia = null;
    
    public static IReservaLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new ReservaLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private ReservaLogica () {}
    
    
    @Override
    public Reserva NuevoReserva(Reserva r) throws Exception
    {
        try
        {
            IReservaDatos datos = FabricaDatos.getReservaDatos();
            return datos.NuevoReserva(r);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
     @Override
    public Reserva BuscarReserva(Reserva r) throws Exception
    {
        try
        {
            IReservaDatos datos = FabricaDatos.getReservaDatos();
            return datos.BuscarReserva(r);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
