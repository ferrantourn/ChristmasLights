/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import Beans.Estadio;
import Beans.Partido;
import Beans.Reserva;
import Beans.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Ferran
 */
public class ReservaDatos implements IReservaDatos {
    private static final String URL_CONEXION = AccesoBD.URL_CONEXION;
    private static final String NOMBRE_USUARIO_BASE_DATOS = AccesoBD.NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS = AccesoBD.contrasena_BD;
    
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
    
    @Override
    public Reserva BuscarReserva(Reserva r) throws Exception
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultadoConsulta = null;
        
        try {
            con = GetConnection();
            
            statement = con.prepareStatement("SELECT r.IdReserva,r.Ci, r.IdPartido," +
                    "p.Fecha,p.IdEstadio,p.IdEquipo1,p.IdEquipo2,e1.Pais as pais1 ,e1.Entrenador as entrenador1 ,"
                    + "e1.CabezaSerie as cabezaSerie1," +
                    "e2.Pais as pais2,e2.Entrenador as entrenador2,e2.CabezaSerie as cabezaSerie2, est.Capacidad, "
                    + "est.FotoUrl,est.NombreEstadio,est.TipoCesped, " +
                    "u.Nombre, u.Apellido, u.Usuario " +
                    "FROM reservas r " +
                    "INNER JOIN Partido p on p.IdPartido = r.IdPartido " +
                    "INNER JOIN Equipo e1 on e1.IdEquipo = p.IdEquipo1 " +
                    "INNER JOIN Equipo e2 on e2.IdEquipo = p.IdEquipo2 " +
                    "INNER JOIN Estadio est on est.IdEstadio = p.IdEstadio " +
                    "INNER JOIN Usuario u on r.Ci = u.Ci " +     
                    "WHERE r.IdReserva = ?");
            
            statement.setInt(1, r.getIdReserva());
            resultadoConsulta = statement.executeQuery();
            
            Reserva reserva = null;
            
            Integer idReserva,ci,idPartido,idEstadio,idEquipo1,idEquipo2, capacidad;
            Date fecha;
            String pais1,entrenador1,pais2,entrenador2, fotoUrl,nombreEstadio,tipoCesped,nombre,apellido,usuario;
            Boolean cabezaSerie1,cabezaSerie2;
            
            if (resultadoConsulta.next()) {
                idReserva = resultadoConsulta.getInt("IdReserva");
                
                pais1 = resultadoConsulta.getString("pais1");
                entrenador1 = resultadoConsulta.getString("entrenador1");
                cabezaSerie1 = resultadoConsulta.getBoolean("cabezaSerie1");
                idEquipo1 = resultadoConsulta.getInt("IdEquipo1");
                
                pais2= resultadoConsulta.getString("pais2");
                entrenador2 = resultadoConsulta.getString("entrenador2");
                cabezaSerie2 = resultadoConsulta.getBoolean("cabezaSerie2");
                idEquipo2 = resultadoConsulta.getInt("IdEquipo2");
                
                ci= resultadoConsulta.getInt("Ci");
                idPartido = resultadoConsulta.getInt("IdPartido");
                idEstadio = resultadoConsulta.getInt("IdEstadio");
                capacidad = resultadoConsulta.getInt("Capacidad");
                
                fecha = resultadoConsulta.getDate("Fecha");
                fotoUrl = resultadoConsulta.getString("FotoUrl");
                nombreEstadio = resultadoConsulta.getString("NombreEstadio");
                tipoCesped = resultadoConsulta.getString("TipoCesped");
                
                nombre = resultadoConsulta.getString("Nombre");
                apellido = resultadoConsulta.getString("Apellido");
                usuario = resultadoConsulta.getString("Usuario");
                
                Equipo equipo1 = new Equipo(idEquipo1,pais1, entrenador1, cabezaSerie1);
                Equipo equipo2 = new Equipo(idEquipo2,pais2, entrenador2, cabezaSerie2);
                Estadio estadio = new Estadio(idEstadio,nombreEstadio,capacidad,Estadio.TipoCesped.valueOf(tipoCesped),fotoUrl);
                
                Equipo[] equipos = new Equipo[2];
                equipos[0] = equipo1;equipos[1]= equipo2;
                Partido p = new Partido(idPartido,equipos, fecha, estadio);
                
                Usuario u = new Usuario(nombre,apellido,usuario,"","");
                u.setCi(ci);
                
                //CREAMOS EL OBJETO RESERVA
                //-------------------------
                reserva = new Reserva();
                reserva.setIdReserva(idReserva);
                reserva.setPartido(p);
                reserva.setUsuario(u);
            }
            
            return reserva;
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
