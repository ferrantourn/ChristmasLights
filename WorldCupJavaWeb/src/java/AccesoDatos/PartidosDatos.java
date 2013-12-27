/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import Beans.Estadio;
import Beans.Gol;
import Beans.Jugador;
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
    public void NuevoPartido(Partido p) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            
            con = GetConnection();
            //DEFINO CONSULTA
            //---------------
            st = con.prepareStatement("INSERT INTO partido ( Fecha,IdEstadio,IdEquipo1,IdEquipo2) "
                    + "VALUES (?,?,?,?)");
            
            //DEFINO PARAMETROS
            //----------------
            java.sql.Date d = new java.sql.Date(p.getFecha().getTime());
            st.setDate(1, d);
            st.setInt(2, p.getEstadio().getIdEstadio());
            st.setInt(3, p.getEquipos()[0].getIdEquipo());
            st.setInt(4, p.getEquipos()[1].getIdEquipo());

            
            //EJECUTO CONSULTA
            int rowsModified = st.executeUpdate(); 
            if (rowsModified != 1)
            {
                throw new Exception("Error al crear el partido.");
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
    
    
    @Override
    public ArrayList<Partido> ListarProximosPartidos(Equipo e) throws Exception
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
               String consulta = "SELECT p.IdPartido,p.Fecha,p.IdEstadio,p.IdEquipo1,p.IdEquipo2, " +
                       "e1.Pais as Pais1,e2.Pais as Pais2, est.NombreEstadio" +
                       " from partido p " +
                       " INNER JOIN Equipo e1 on e1.IdEquipo = p.IdEquipo1 " +
                       " INNER JOIN Equipo e2 on e2.IdEquipo = p.IdEquipo2 " + 
                       " INNER JOIN Estadio est on est.IdEstadio = p.IdEstadio " +
                       " WHERE p.Fecha > NOW() ";
               
               //si se quiere filtrar
               if (e != null)
               {
                   consulta += " AND p.IdEquipo1 = " + e.getIdEquipo() + " OR p.IdEquipo2 = " + e.getIdEquipo();
               }
               
               st = con.prepareStatement(consulta);         
                       
               //EJECUTO CONSULTA
               rs = st.executeQuery();
                
                ArrayList<Partido> partidos = new ArrayList();
                Partido p;
                
                int idEstadio,idPartido,IdEquipo1,IdEquipo2;
                Date fecha;
                String pais1,pais2,nombreEstadio;
                
                while (rs.next()) {
                    //estadio
                    idEstadio = rs.getInt("IdEstadio");
                    nombreEstadio = rs.getString("NombreEstadio");
                    
                    
                    idPartido = rs.getInt("IdPartido");
                    IdEquipo1 = rs.getInt("IdEquipo1");
                    IdEquipo2 = rs.getInt("IdEquipo2");
                    pais1 = rs.getString("Pais1");
                    pais2 = rs.getString("Pais2");
                    Equipo[] equipos = new Equipo[2];
                    equipos[0] = new Equipo(IdEquipo1,pais1);
                    equipos[1] = new Equipo(IdEquipo2,pais2);
                    fecha = rs.getDate("Fecha");
                    
                    Estadio est = new Estadio();
                    est.setIdEstadio(idEstadio);
                    est.setNombreEstadio(nombreEstadio);
                    p = new Partido(idPartido,equipos,fecha,est);
                    
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
    
    
     @Override
    public ArrayList<Partido> ListarPartidosJugados() throws Exception
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
               String consulta = "SELECT p.IdPartido,p.Fecha,p.IdEstadio,p.IdEquipo1,p.IdEquipo2, " +
                       "e1.Pais as Pais1,e2.Pais as Pais2, est.NombreEstadio" +
                       " from partido p " +
                       " INNER JOIN Equipo e1 on e1.IdEquipo = p.IdEquipo1 " +
                       " INNER JOIN Equipo e2 on e2.IdEquipo = p.IdEquipo2 " + 
                       " INNER JOIN Estadio est on est.IdEstadio = p.IdEstadio " +
                       " WHERE p.Fecha < NOW() ";
               
               st = con.prepareStatement(consulta);         
                       
               //EJECUTO CONSULTA
               rs = st.executeQuery();
                
                ArrayList<Partido> partidos = new ArrayList();
                Partido p;
                
                int idEstadio,idPartido,IdEquipo1,IdEquipo2, golesEquipo1, golesEquipo2;
                Date fecha;
                String pais1,pais2,nombreEstadio;
                
                while (rs.next()) {
                    //estadio
                    idEstadio = rs.getInt("IdEstadio");
                    nombreEstadio = rs.getString("NombreEstadio");
                    
                    
                    idPartido = rs.getInt("IdPartido");
                    IdEquipo1 = rs.getInt("IdEquipo1");
                    IdEquipo2 = rs.getInt("IdEquipo2");
                    pais1 = rs.getString("Pais1");
                    pais2 = rs.getString("Pais2");
                    Equipo[] equipos = new Equipo[2];
                   
                    
                    //OBTENGO LOS GOLES
                    //-----------------
                    golesEquipo1= ObtenerGolesEquipoPartido(idPartido, IdEquipo1);
                    golesEquipo2= ObtenerGolesEquipoPartido(idPartido, IdEquipo2);
                    
                    equipos[0] = new Equipo(IdEquipo1,pais1);
                    equipos[0].setGoles(golesEquipo1);
                    equipos[1] = new Equipo(IdEquipo2,pais2);
                    equipos[1].setGoles(golesEquipo2);

                    fecha = rs.getDate("Fecha");
                    
                    Estadio est = new Estadio();
                    est.setIdEstadio(idEstadio);
                    est.setNombreEstadio(nombreEstadio);
                    p = new Partido(idPartido,equipos,fecha,est);
                    
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
    
    
    @Override
    public Gol IngresarGol(Gol g) throws Exception
    {
        try
        {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet claves = null;
            try
            {
                con = GetConnection();
                
                //DEFINO CONSULTA
                //---------------
                st = con.prepareStatement("INSERT INTO goles ( IdPartido,IdJugador,Minuto) "
                        + "VALUES (?,?,?)",st.RETURN_GENERATED_KEYS);
                
                st.setInt(1, g.getPartido().getIdPartido());
                st.setInt(2, g.getJugador().getIdJugador());
                st.setInt(3, g.getMinuto());
                
                
                //EJECUTO CONSULTA
                int rowsModified = st.executeUpdate();
                
                if (rowsModified != 1)
                {
                    throw new Exception("Error al crear el gol.");
                }
                
                //OBTENGO CLAVE AUTOGENERADA
                claves = st.getGeneratedKeys();
                if (claves.next()) {
                    g.setIdGol(claves.getInt(1));
                } else {
                    throw new SQLException("Error al obtener el identificador del gol.");
                }
                
                return g;
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
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    
    public int ObtenerGolesEquipoPartido (int idpartido, int idequipo) throws Exception
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
               st = con.prepareStatement("select count(*) as goles, goles.IdPartido,Equipo.Pais,Equipo.IdEquipo from goles " +
                       "inner join Jugador on Jugador.IdJugador = goles.IdJugador " +
                       "inner join Equipo on Jugador.IdEquipo = Equipo.IdEquipo " +
                       "inner join Partido on Partido.IdPartido = goles.IdPartido " +
                       "WHERE Partido.Fecha < curdate() and goles.IdPartido = ? and Jugador.IdEquipo = ? " +
                       "group by goles.IdPartido,Equipo.Pais,Equipo.IdEquipo ");
               
               
               st.setInt(1, idpartido);
               st.setInt(2, idequipo);
               
                //EJECUTO CONSULTA
                rs = st.executeQuery();
                
                int goles = 0;
                
                if (rs.next()) {
                    goles = rs.getInt("goles");
                    
                }
                
                return goles;
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
    public ArrayList<Gol> ListarGolesPartido (Partido p) throws Exception
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
               st = con.prepareStatement("SELECT goles.IdPartido,goles.IdGol,goles.IdPartido,goles.IdJugador,goles.Minuto,"
                       + " Jugador.Nombre, Jugador.Apellido, Jugador.Posicion,"
                       + " Equipo.Pais,Equipo.IdEquipo from goles " +
                       "inner join Jugador on Jugador.IdJugador = goles.IdJugador " +
                       "inner join Equipo on Jugador.IdEquipo = Equipo.IdEquipo " +
                       "inner join Partido on Partido.IdPartido = goles.IdPartido " +
                       "WHERE goles.IdPartido = ? "
                       + "ORDER BY goles.Minuto ASC");
               
               
               st.setInt(1, p.getIdPartido());
               
                //EJECUTO CONSULTA
                rs = st.executeQuery();
                
                ArrayList<Gol> goles = new ArrayList();
                
                while (rs.next()) {
                    Gol g = new Gol();
                    g.setIdGol(rs.getInt("IdGol"));
                    Jugador j = new Jugador();
                    j.setIdJugador(rs.getInt("IdJugador"));
                    j.setNombre(rs.getString("Nombre"));
                    j.setApellido(rs.getString("Apellido"));
                    j.setPosicion(rs.getString("Posicion"));
                    Equipo e = new Equipo();
                    e.setIdEquipo(rs.getInt("IdEquipo"));
                    e.setPais(rs.getString("Pais"));
                    j.setEquipoPertenece(e);
                    
                    g.setJugador(j);
                    g.setMinuto(rs.getInt("Minuto"));
                    g.setPartido(p);
                    
                    goles.add(g);
                }
                
                return goles;
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
