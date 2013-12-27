/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IConsultasDatos;
import Beans.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public class ConsultasLogica implements IConsultasLogica {
    private static ConsultasLogica instancia = null;
    
    public static IConsultasLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new ConsultasLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private ConsultasLogica () {}
    
    
    @Override
   public ArrayList<Jugador> TablaGoleadores () throws Exception
    {
        try
        {
            IConsultasDatos datos = FabricaDatos.getConsultasDatos();
            return datos.TablaGoleadores();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
