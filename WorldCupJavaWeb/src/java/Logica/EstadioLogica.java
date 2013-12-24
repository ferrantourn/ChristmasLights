/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IEstadioDatos;
import Beans.Estadio;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public class EstadioLogica implements IEstadioLogica {
     private static EstadioLogica instancia = null;
    
    public static IEstadioLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new EstadioLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private EstadioLogica () {}
    
    
    @Override
    public void NuevoEstadio(Estadio e) throws Exception
    {
        try
        {
            IEstadioDatos datos = FabricaDatos.getEstadioDatos();
            datos.NuevoEstadio(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public void EliminarEstadio(Estadio e) throws Exception
    {
        try
        {
             IEstadioDatos datos = FabricaDatos.getEstadioDatos();
            datos.EliminarEstadio(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public Estadio BuscarEstadio(Estadio e) throws Exception
    {
        try
        {
            IEstadioDatos datos = FabricaDatos.getEstadioDatos();
            return datos.BuscarEstadio(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<Estadio> ListarEstadios() throws Exception
    {
        try
        {
           IEstadioDatos datos = FabricaDatos.getEstadioDatos();
           return datos.ListarEstadios();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }
       
    @Override
    public void ActualizarEstadio(Estadio e) throws Exception
    {
        try
        {
            IEstadioDatos datos = FabricaDatos.getEstadioDatos();
            datos.ActualizarEstadio(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
