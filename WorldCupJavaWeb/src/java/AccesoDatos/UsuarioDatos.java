/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import Beans.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ferran
 */
public class UsuarioDatos implements IUsuarioDatos {
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/2014worldcup";
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "1qaz2WSX";
    
    private static UsuarioDatos instancia = null;
    
    public static IUsuarioDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new UsuarioDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private UsuarioDatos () {}
    
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
    public Usuario Autenticar(Usuario u) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and password = ?;");
            
            statement.setString(1, u.getUsuario());
            statement.setString(2, u.getPassword());
            resultadoConsulta = statement.executeQuery();
            
            Usuario user = null;
            
            String nombre, apellido, usuario, password,tipoUsuario ;
            Integer ci;
            
            if (resultadoConsulta.next()) {
                nombre = resultadoConsulta.getString("Nombre");
                apellido = resultadoConsulta.getString("Apellido");
                usuario = resultadoConsulta.getString("Usuario");
                password = resultadoConsulta.getString("Password");
                tipoUsuario = resultadoConsulta.getString("TipoUsuario");
                ci = resultadoConsulta.getInt("Ci");
                user = new Usuario(nombre,apellido,usuario,password,tipoUsuario);
            }
            
            return user;
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
}
