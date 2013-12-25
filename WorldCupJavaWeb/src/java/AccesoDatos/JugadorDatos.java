/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import Beans.Jugador;
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
public class JugadorDatos implements IJugadorDatos {
    private static final String URL_CONEXION = AccesoBD.URL_CONEXION;
    private static final String NOMBRE_USUARIO_BASE_DATOS = AccesoBD.NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS = AccesoBD.contrasena_BD;
    
    private static JugadorDatos instancia = null;
    
    public static IJugadorDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new JugadorDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private JugadorDatos
         () {}
    
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
    public void NuevoJugador(Jugador e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("INSERT INTO jugador ( Nombre, Apellido, Posicion, IdEquipo) VALUES (?,?,?,?)");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getNombre());
            st.setString(2, e.getApellido());
            st.setString(3, e.getPosicion()) ;
            st.setString(4, e.getEquipoPertenece().toString());
            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate(); 
            if (rowsModified != 1)
            {
                throw new Exception("Error al dar de alta a jugador.");
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
    public void EliminarJugador(Jugador e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = GetConnection();
            statement = con.prepareStatement("DELETE FROM jugador WHERE IdJugador = ?;");
            statement.setInt(1, e.getIdJugador());
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
    public Jugador BuscarJugador(Jugador e) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("SELECT IdJugador, Nombre, Apellido, Posicion, IdEquipo FROM jugador WHERE IdJugador = ?;");
            
            statement.setInt(1, e.getIdJugador());
            resultadoConsulta = statement.executeQuery();
            
            Jugador jugador = null;
            
            String nombre, apellido, posicion;
            int IdJugador;
            Equipo equipoAux = new Equipo();

            if (resultadoConsulta.next()) {
                IdJugador = resultadoConsulta.getInt("IdJugador");
                nombre = resultadoConsulta.getString("Nombre");
                apellido = resultadoConsulta.getString("Apellido");
                posicion = resultadoConsulta.getString("Posicion");
                equipoAux.setIdEquipo(resultadoConsulta.getInt("IdEquipo"));//cargo equipoAux con IdEquipo
                
                EquipoDatos datosE = null; //la fabrica instanciar.
                
                equipoAux = datosE.BuscarEquipo(equipoAux);//cargo equipoAux con todos los datos.*/
                
                //jugador = new Jugador(nombre, apellido, posicion, equipoAux.IdEquipo);
                jugador = new Jugador(IdJugador,nombre, apellido, posicion, equipoAux);
            }
            
            return jugador;
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
    public ArrayList<Jugador> ListarJugadores() throws Exception
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
               st = con.prepareStatement("SELECT IdJugador, Nombre, Apellido, Posicion, IdEquipo FROM jugador");
               //EJECUTO CONSULTA
               rs = st.executeQuery();
               
               ArrayList<Jugador> jugadores = new ArrayList();
               Jugador e;
               
               String nombre, apellido, posicion; 
               int IdJugador;
               
               while (rs.next()) {
                   nombre = rs.getString("Nombre");
                   apellido = rs.getString("Apellido");
                   posicion = rs.getString("Posicion");
                   IdJugador = rs.getInt("IdJugador");
                   
                   Equipo equipoAux = new Equipo();
                
                   equipoAux.setIdEquipo(rs.getInt("IdEquipo"));//cargo equipoAux con IdEquipo
                   EquipoDatos datosE = null; //la fabrica instanciar.
                   equipoAux = datosE.BuscarEquipo(equipoAux);//cargo equipoAux con todos los datos.*/

                   jugadores.add(new Jugador(IdJugador, nombre, apellido, posicion, equipoAux));
               }
               
               return jugadores;
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
    public void ActualizarJugador(Jugador e) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("UPDATE jugador SET Nombre = ?, Apellido = ?, Posicion = ?, IdEquipo = ? "
                    + "WHERE IdJugador = ?");
            
            //DEFINO PARAMETROS
            //----------------
            st.setString(1, e.getNombre());
            st.setString(2, e.getApellido());
            st.setString(3, e.getPosicion());
            st.setInt(4, e.getEquipoPertenece().getIdEquipo());
            
             
             
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate();
            
            if (rowsModified != 1)
            {
                throw new Exception("Error al actualizar datos del jugador.");
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
