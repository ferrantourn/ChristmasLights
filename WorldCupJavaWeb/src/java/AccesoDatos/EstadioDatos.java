/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Estadio;
import Beans.Estadio.TipoCesped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public class EstadioDatos implements IEstadioDatos {
    private static final String URL_CONEXION = AccesoBD.URL_CONEXION;
    private static final String NOMBRE_USUARIO_BASE_DATOS = AccesoBD.NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS = AccesoBD.contrasena_BD;
    
    private static EstadioDatos instancia = null;
    
    public static IEstadioDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new EstadioDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private EstadioDatos () {}
    
    private Connection GetConnection () throws ClassNotFoundException, SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //DEFINO CONEXION
            //---------------
            return DriverManager.getConnection(URL_CONEXION, NOMBRE_USUARIO_BASE_DATOS, CONTRASENA_BASE_DATOS);
        }
        catch (SQLException ex){
            throw ex;
        }
    }
    
    @Override
    public void NuevoEstadio(Estadio e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("INSERT INTO estadio ( NombreEstadio , Capacidad, TipoCesped, FotoUrl) VALUES (?,?,?,?)");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getNombreEstadio());
            st.setInt(2, e.getCapacidad());
            st.setString(3, e.getCesped().toString()) ;
            st.setString(4, e.getFotoUrl());
            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate(); 
            if (rowsModified != 1)
            {
                throw new Exception("Error al crear el estadio.");
            }
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();
                }
                
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException ex) {
                throw ex;
            }
        }
    }
    
    @Override
    public void EliminarEstadio(Estadio e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = GetConnection();
            statement = con.prepareStatement("DELETE FROM estadio WHERE IdEstadio = ?;");
            statement.setInt(1, e.getIdEstadio());
            Integer r = statement.executeUpdate();
            
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException ex) {
                throw ex;
            }
        }
    }
    
    @Override
    public Estadio BuscarEstadio(Estadio e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("SELECT IdEstadio, NombreEstadio,Capacidad,TipoCesped, FotoUrl FROM estadio WHERE IdEstadio = ?;");
            
            statement.setInt(1, e.getIdEstadio());
            resultadoConsulta = statement.executeQuery();
            
            Estadio estadio = null;
            
            String nombre,tipoCesped, fotoUrl;
            int capacidad,idEstadio;
            
            if (resultadoConsulta.next()) {
                nombre = resultadoConsulta.getString("NombreEstadio");
                tipoCesped = resultadoConsulta.getString("TipoCesped");
                capacidad = resultadoConsulta.getInt("Capacidad");
                idEstadio = resultadoConsulta.getInt("IdEstadio");
                fotoUrl = resultadoConsulta.getString("FotoUrl");
                estadio = new Estadio(idEstadio,nombre, capacidad, TipoCesped.valueOf(tipoCesped),fotoUrl);
            }
            
            return estadio;
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally {
            try {
                if (resultadoConsulta != null) {
                    resultadoConsulta.close();
                }
                
                if (statement != null) {
                    statement.close();
                }
                
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException ex) {
                throw ex;
            }
        }
    }
    
    @Override
    public ArrayList<Estadio> ListarEstadios() throws Exception
    {
        try
        {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            try
            {
               con = GetConnection();
               
                //DEFINO CONSULTA
               //---------------
               st = con.prepareStatement("SELECT IdEstadio, NombreEstadio,Capacidad,TipoCesped, FotoUrl FROM estadio");
               //EJECUTO CONSULTA
               rs = st.executeQuery();
               
               ArrayList<Estadio> estadios = new ArrayList();
               Estadio e;
               
               String nombre,tipoCesped,fotoUrl;
               int capacidad,idestadio;
               
               while (rs.next()) {
                   nombre = rs.getString("NombreEstadio");
                   tipoCesped = rs.getString("TipoCesped");
                   capacidad = rs.getInt("Capacidad");
                   idestadio = rs.getInt("IdEstadio");
                   fotoUrl = rs.getString("FotoUrl");
                   estadios.add(new Estadio(idestadio,nombre, capacidad, TipoCesped.valueOf(tipoCesped),fotoUrl));
               }
               
               return estadios;
            }
            catch (SQLException ex){
                throw ex;
            }
            finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    
                    if (st != null) {
                        st.close();
                    }
                    
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException ex) {
                    throw ex;
                }
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
       
    @Override
    public void ActualizarEstadio(Estadio e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("UPDATE estadio SET NombreEstadio = ?, Capacidad = ?, TipoCesped = ?, FotoUrl = ? "
                    + "WHERE IdEstadio = ?");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getNombreEstadio());
            st.setInt(2, e.getCapacidad());
            st.setString(3, e.getCesped().toString());
            st.setString(4, e.getFotoUrl());
            st.setInt(5, e.getIdEstadio());
             
             
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate();
            
            if (rowsModified != 1)
            {
                throw new Exception("Error al actualizar el estadio.");
            }
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();
                }
                
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException ex) {
                throw ex;
            }
        }
    }
}
