/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
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
public class EquipoDatos implements IEquiposDatos{
    
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/2014worldcup";
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "1qaz2WSX";
    
    private static EquipoDatos instancia = null;
    
    public static IEquiposDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new EquipoDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private EquipoDatos () {}
    
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
    public void NuevoEquipo(Equipo e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("INSERT INTO equipo ( Pais , Entrenador, CabezaSerie) VALUES (?,?,?)");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getPais());
            st.setString(2, e.getEntrenador());
            st.setBoolean(3, e.isCabezaSerie());
            
            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate(); 
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
    public void EliminarEquipo(Equipo e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = GetConnection();
            statement = con.prepareStatement("DELETE FROM equipo WHERE IdEquipo = ?;");
            statement.setInt(1, e.getIdEquipo());
            Integer resultado = statement.executeUpdate();
            
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
    public Equipo BuscarEquipo(Equipo e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("SELECT * FROM equipo WHERE IdEquipo = ?;");
            
            statement.setInt(1, e.getIdEquipo());
            resultadoConsulta = statement.executeQuery();
            
            Equipo equipoResp = null;
            
            String pais,entrenador;
            Boolean cabezaSerie;
            Integer idequipo;
            
            if (resultadoConsulta.next()) {
                pais = resultadoConsulta.getString("Pais");
                entrenador = resultadoConsulta.getString("Entrenador");
                cabezaSerie = resultadoConsulta.getBoolean("CabezaSerie");
                idequipo = resultadoConsulta.getInt("IdEquipo");
                equipoResp = new Equipo(idequipo,pais, entrenador, cabezaSerie);
            }
            
            return equipoResp;
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
    public ArrayList<Equipo> ListarEquipos() throws Exception
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
                st = con.prepareStatement("SELECT IdEquipo,Pais,Entrenador,CabezaSerie from equipo");
                //EJECUTO CONSULTA
                rs = st.executeQuery();
                
                ArrayList<Equipo> equipos = new ArrayList();
                Equipo e;
                
                String pais,entrenador;
                Boolean cabezaSerie;
                Integer idequipo;
                
                while (rs.next()) {
                    pais = rs.getString("Pais");
                    entrenador = rs.getString("Entrenador");
                    cabezaSerie = rs.getBoolean("CabezaSerie");
                    idequipo = rs.getInt("IdEquipo");
                    
                    e = new Equipo(idequipo,pais, entrenador, cabezaSerie);
                    equipos.add(e);
                }
                
                return equipos;
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
    public void ActualizarEquipo(Equipo e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("UPDATE equipo SET Pais = ?, Entrenador = ?, CabezaSerie = ?"
                    + "WHERE IdEquipo = ?");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getPais());
            st.setString(2, e.getEntrenador());
            st.setBoolean(3, e.isCabezaSerie());
            st.setInt(4, e.getIdEquipo());
            
            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate(); 
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
