/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import Beans.Estadio;
import Beans.Partido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ferran
 */
public class PartidosDatos implements IPartidosDatos {
    private static final String URL_CONEXION = AccesoBD.URL_CONEXION;
    private static final String NOMBRE_USUARIO_BASE_DATOS = AccesoBD.NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS = AccesoBD.contrasena_BD;
    
    private static PartidosDatos instancia = null;
    
    public static IPartidosDatos getInstancia()
    {
        if (instancia == null) {
            instancia = new PartidosDatos();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private PartidosDatos () {}
    
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
    public ArrayList<Partido> ListarPartido() throws Exception
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
               st = con.prepareStatement("SELECT p.IdPartido,p.Fecha,p.IdEstadio,p.IdEquipo1,p.IdEquipo2," +
                       "e1.Pais as Pais1,e2.Pais as Pais2" +
                       " from partido p " +
                       "INNER JOIN Equipo e1 on e1.IdEquipo = p.IdEquipo1 " +
                       "INNER JOIN Equipo e2 on e2.IdEquipo = p.IdEquipo2 ");
               //EJECUTO CONSULTA
               rs = st.executeQuery();
                
                ArrayList<Partido> partidos = new ArrayList();
                Partido p;
                
                int idEstadio,idPartido,IdEquipo1,IdEquipo2;
                Date fecha;
                String pais1,pais2;
                
                while (rs.next()) {
                    idEstadio = rs.getInt("IdEstadio");
                    idPartido = rs.getInt("IdPartido");
                    IdEquipo1 = rs.getInt("IdEquipo1");
                    IdEquipo2 = rs.getInt("IdEquipo2");
                    pais1 = rs.getString("Pais1");
                    pais2 = rs.getString("Pais2");
                    Equipo[] equipos = new Equipo[2];
                    equipos[0] = new Equipo(IdEquipo1,pais1);
                    equipos[1] = new Equipo(IdEquipo2,pais2);
                    fecha = rs.getDate("Fecha");
                    
                    Estadio e = new Estadio();
                    e.setIdEstadio(idEstadio);
                    p = new Partido(idPartido,equipos,fecha,e);
                    
                    partidos.add(p);
                }
                
                return partidos;
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
}
