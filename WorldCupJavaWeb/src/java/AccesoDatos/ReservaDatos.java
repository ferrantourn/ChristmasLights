/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Reserva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ferran
 */
public class ReservaDatos implements IReservaDatos {
     private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/2014worldcup";
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "1qaz2WSX";
    
    private static ReservaDatos instancia = null;
    
    public static IReservaDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new ReservaDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private ReservaDatos () {}
    
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
    public Reserva NuevoReserva(Reserva r) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet claves = null;
        try
        {
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("INSERT INTO reservas ( IdPartido , ci) VALUES (?,?)",
                    st.RETURN_GENERATED_KEYS);
            
            //DEFINO PARAMETROS
            //----------------
            st.setInt(1, r.getPartido().getIdPartido());
            st.setInt(2, (int)r.getUsuario().getCi());
            
            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate();
            if (rowsModified == 0) {
                throw new SQLException("No se pudo generar la reserva.");
            }
            
            //OBTENGO CLAVE AUTOGENERADA
            claves = st.getGeneratedKeys();
            if (claves.next()) {
                r.setIdReserva(claves.getInt(1));
            } else {
                throw new SQLException("Error al obtener el numero de reserva.");
            }
            
            return r;
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            try {
                if (claves != null) {
                    claves.close();
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
