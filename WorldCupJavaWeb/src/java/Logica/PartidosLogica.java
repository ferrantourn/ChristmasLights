/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IJugadorDatos;
import AccesoDatos.IPartidosDatos;
import Beans.Gol;
import Beans.Jugador;
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
    public void NuevoPartido(Partido p) throws Exception
    {
        try
        {
            IPartidosDatos datos = FabricaDatos.getPartidosDatos();
            datos.NuevoPartido(p);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
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
    
    public Gol IngresarGol (Gol g) throws Exception
    {
        try
        {
           IPartidosDatos datos = FabricaDatos.getPartidosDatos();
           return datos.IngresarGol(g);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    
}
