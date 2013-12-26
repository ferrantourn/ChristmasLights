package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IJugadorDatos;
import Beans.Jugador;
import java.util.ArrayList;


public class JugadorLogica implements IJugadorLogica {
    private static JugadorLogica instancia = null;
    
    public static IJugadorLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new JugadorLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private JugadorLogica () {}
    
    
    @Override
    public void NuevoJugador(Jugador e) throws Exception
    {
        try
        {
            IJugadorDatos datos = FabricaDatos.getJugadorDatos();
            datos.NuevoJugador(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public void EliminarJugador(Jugador e) throws Exception
    {
        try
        {
            IJugadorDatos datos = FabricaDatos.getJugadorDatos();
            datos.EliminarJugador(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public Jugador BuscarJugador(Jugador e) throws Exception
    {
        try
        {
            IJugadorDatos datos = FabricaDatos.getJugadorDatos();
            return datos.BuscarJugador(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<Jugador> ListarJugadores() throws Exception
    {
        try
        {
           IJugadorDatos datos = FabricaDatos.getJugadorDatos();
           return datos.ListarJugadores();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }
       
    @Override
    public void ActualizarJugador(Jugador e) throws Exception
    {
        try
        {
            IJugadorDatos datos = FabricaDatos.getJugadorDatos();
            datos.ActualizarJugador(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
