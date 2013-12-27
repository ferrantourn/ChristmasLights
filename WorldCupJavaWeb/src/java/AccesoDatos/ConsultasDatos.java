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
public class ConsultasDatos implements IConsultasDatos {
     private static final String URL_CONEXION = AccesoBD.URL_CONEXION;
    private static final String NOMBRE_USUARIO_BASE_DATOS = AccesoBD.NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS = AccesoBD.contrasena_BD;
    
    private static ConsultasDatos instancia = null;
    
    public static IConsultasDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new ConsultasDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private ConsultasDatos () {}
    
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
    public ArrayList<Jugador> TablaGoleadores () throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("select count(*) as Total,goles.IdJugador, jugador.Nombre, "
                    + "jugador.Apellido from goles " +
                    "inner join jugador on jugador.IdJugador = goles.IdJugador " +
                    "group by goles.IdJugador " +
                    "order by Total desc");
            
            resultadoConsulta = statement.executeQuery();
            
            ArrayList<Jugador> jugadores = new ArrayList();
            
            String nombre, apellido;
            int IdJugador,totalGoles;

            while (resultadoConsulta.next()) {
                Jugador jugador;
                IdJugador = resultadoConsulta.getInt("IdJugador");
                nombre = resultadoConsulta.getString("Nombre");
                apellido = resultadoConsulta.getString("Apellido");
                //equipoAux.setIdEquipo(resultadoConsulta.getInt("IdEquipo"));//cargo equipoAux con IdEquipo
                totalGoles = resultadoConsulta.getInt("Total");
                
                //IEquiposDatos datosE = FabricaDatos.getEquiposDatos(); //la fabrica instanciar.
                
                //equipoAux = datosE.BuscarEquipo(equipoAux);//cargo equipoAux con todos los datos.*/
                
                //jugador = new Jugador(nombre, apellido, posicion, equipoAux.IdEquipo);
                jugador = new Jugador(IdJugador,nombre, apellido, "", null);
                jugador.setTotalGoles(totalGoles);
                jugadores.add(jugador);
            }
            
            return jugadores;
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
