/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IPartidosDatos;
import Beans.Equipo;
import Beans.Gol;
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
    
    @Override
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
    
     @Override
    public ArrayList<Partido> ListarProximosPartidos(Equipo e) throws Exception
    {
        try
        {
           IPartidosDatos datos = FabricaDatos.getPartidosDatos();
           return datos.ListarProximosPartidos(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<Partido> ListarPartidosJugados() throws Exception
    {
        try
        {
            IPartidosDatos datos = FabricaDatos.getPartidosDatos();
           return datos.ListarPartidosJugados();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<Gol> ListarGolesPartido (Partido p) throws Exception
    {
        try
        {
            IPartidosDatos datos = FabricaDatos.getPartidosDatos();
            return datos.ListarGolesPartido(p);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
