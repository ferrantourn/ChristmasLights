/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IPartidosDatos;
import Beans.Partido;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public class PartidosLogica implements IPartidosLogica {
     private static PartidosLogica instancia = null;
    
    public static IPartidosLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new PartidosLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private PartidosLogica () {}
    
    @Override
    public ArrayList<Partido> ListarPartidos() throws Exception
    {
        try
        {
           IPartidosDatos datos = FabricaDatos.getPartidosDatos();
           return datos.ListarPartido();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
