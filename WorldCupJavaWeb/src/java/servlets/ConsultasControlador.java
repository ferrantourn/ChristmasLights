/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Equipo;
import Beans.Gol;
import Beans.Jugador;
import Beans.ModeloFormBasico;
import Beans.Partido;
import Beans.mvGoles;
import Beans.mvPartido;
import Logica.FabricaLogica;
import Logica.IConsultasLogica;
import Logica.IEquiposLogica;
import Logica.IPartidosLogica;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferran
 */
public class ConsultasControlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion= null,metodo;
        try
        {
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            metodo = request.getMethod().toUpperCase();
            
            ModeloFormBasico m = new ModeloFormBasico();
            request.setAttribute("modelo",m);
            
            switch (accion) {
                case "tabla":
                    if (metodo.equals("GET")) {
                        TablaGoleadores(request, response);
                    }
                    break;
                case "buscar":
                    ProximosPartidos(request, response);
                    break;
                case "jugados":
                    PartidosJugados(request, response);
                    break;
                    case "goles":
                    GolesPartido(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ModeloFormBasico modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al realizar la consulta.");
            modelo.setDescErrorInterno(ex.getMessage());
            request.setAttribute("modelo", modelo);
            
            RequestDispatcher despachador = request.getRequestDispatcher("error.jsp");
                        
            despachador.forward(request, response);
        }
    }
    
    private void TablaGoleadores(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            IConsultasLogica lc = FabricaLogica.getConsultasLogica();
            ArrayList<Jugador> goleadores = lc.TablaGoleadores();
            request.setAttribute("jugadores", goleadores);
            
            ModeloFormBasico form = new ModeloFormBasico();
            request.setAttribute("modelo",form);
            
            String vista = "WEB-INF/Vistas/Consultas.jsp";
            //vista += ("".equals(accion)) ? "ListarJugadores.jsp" : "Jugador.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }
    
    private void ProximosPartidos(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            //Agregar un atributo equipos
            IEquiposLogica lequipos = FabricaLogica.getEquiposLogica();
            request.setAttribute("equipos", lequipos.ListarEquipos());
            
            
            
            //Obtenemos los partidos
            IPartidosLogica lpartidos = FabricaLogica.getPartidosLogica();
            Integer idequipo;
            
            if (request.getParameter("ddlEquipo") != null){
                try {
                    idequipo = Integer.parseInt(request.getParameter("ddlEquipo"));
                }
                catch (NumberFormatException ex) {
                    throw new Exception("El identificador de equipo no es válido.");
                }
            }
            else
            {
                idequipo = 0;
            }
            
            Equipo e ;
            if (idequipo != 0)
            {
                e = new Equipo();
                e.setIdEquipo(idequipo);
            }
            else
            {
                e = null;
            }
            
            ArrayList<Partido> proxPartidos = lpartidos.ListarProximosPartidos(e);
            
            //CARGAMOS EL MODELO DE LA VISTA
            ArrayList<mvPartido> partidosVista = new ArrayList();
            
            for (Partido p : proxPartidos)
            {
                mvPartido mv= new mvPartido();
                
                mv.setEquipo1(p.getEquipos()[0].getPais());
                mv.setEquipo2(p.getEquipos()[1].getPais());
                mv.setFechaPartido(p.getFecha());
                mv.setNombreEstadio(p.getEstadio().getNombreEstadio());
                partidosVista.add(mv);
            }
            
            request.setAttribute("partidos",partidosVista);
            
            String vista = "WEB-INF/Vistas/ProximosPartidos.jsp";
            //vista += ("".equals(accion)) ? "ListarJugadores.jsp" : "Jugador.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }
    
    private void PartidosJugados(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            //Obtenemos los partidos
            IPartidosLogica lpartidos = FabricaLogica.getPartidosLogica();
            ArrayList<Partido> proxPartidos = lpartidos.ListarPartidosJugados();
            
            //CARGAMOS EL MODELO DE LA VISTA
            ArrayList<mvPartido> partidosVista = new ArrayList();
            
            for (Partido p : proxPartidos)
            {
                mvPartido mv= new mvPartido();
                
                mv.setEquipo1(p.getEquipos()[0].getPais());
                mv.setEquipo2(p.getEquipos()[1].getPais());
                mv.setFechaPartido(p.getFecha());
                mv.setNombreEstadio(p.getEstadio().getNombreEstadio());
                mv.setGolesEquipo1(p.getEquipos()[0].getGoles());
                mv.setGolesEquipo2(p.getEquipos()[1].getGoles());
                mv.setIdPartido(p.getIdPartido());

                partidosVista.add(mv);
            }
           
            request.setAttribute("partidos",partidosVista);
            
            String vista = "WEB-INF/Vistas/PartidosJugados.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }
    
    private void GolesPartido(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            //Obtenemos los partidos
            IPartidosLogica lpartidos = FabricaLogica.getPartidosLogica();
            int idPartido = Integer.parseInt(request.getParameter("idPartido"));
            Partido p = new Partido();
            p.setIdPartido(idPartido);
            ArrayList<Gol> golesPartido = lpartidos.ListarGolesPartido(p);
            
            ArrayList<mvGoles> goles = new ArrayList();
            for (Gol g : golesPartido)
            {
                mvGoles golVista = new mvGoles();
                golVista.setJugadorApellido(g.getJugador().getApellido());
                golVista.setJugadorNombre(g.getJugador().getNombre());
                golVista.setMinuto(g.getMinuto());
                golVista.setNombreEquipo(g.getJugador().getEquipoPertenece().getPais());
                
                goles.add(golVista);
            }
            
            request.setAttribute("goles",goles);
            
            String vista = "WEB-INF/Vistas/GolesPartido.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
