/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IEquiposDatos;
import Beans.Equipo;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public class EquiposLogica implements IEquiposLogica {
    
    private static EquiposLogica instancia = null;
    
    public static IEquiposLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new EquiposLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private EquiposLogica () {}
    
    
    @Override
    public void NuevoEquipo(Equipo e) throws Exception
    {
        try
        {
            IEquiposDatos datos = FabricaDatos.getEquiposDatos();
            datos.NuevoEquipo(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public void EliminarEquipo(Equipo e) throws Exception
    {
        try
        {
             IEquiposDatos datos = FabricaDatos.getEquiposDatos();
            datos.EliminarEquipo(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public Equipo BuscarEquipo(Equipo e) throws Exception
    {
        try
        {
            IEquiposDatos datos = FabricaDatos.getEquiposDatos();
            return datos.BuscarEquipo(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<Equipo> ListarEquipos() throws Exception
    {
        try
        {
           IEquiposDatos datos = FabricaDatos.getEquiposDatos();
           return datos.ListarEquipos();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
       
    @Override
    public void ActualizarEquipo(Equipo e) throws Exception
    {
        try
        {
            IEquiposDatos datos = FabricaDatos.getEquiposDatos();
            datos.ActualizarEquipo(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
}

