/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Equipo;
import Beans.Estadio;
import Beans.Gol;
import Beans.Jugador;
import Beans.ModeloFormBasico;
import Beans.Partido;
import Logica.FabricaLogica;
import Logica.IEquiposLogica;
import Logica.IEstadioLogica;
import Logica.IJugadorLogica;
import Logica.IPartidosLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferran
 */
public class GolControlador extends HttpServlet {

     IPartidosLogica partidosLogica;
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
        try {
            partidosLogica = FabricaLogica.getPartidosLogica();
            
            Gol g = new Gol();
            request.setAttribute("gol",g);
        
            ArrayList<Partido> partidos = partidosLogica.ListarPartidos();
            request.setAttribute("partidos",partidos);
            
            IJugadorLogica jugadorLogica = FabricaLogica.getJugadorLogica();
            ArrayList<Jugador> jugadores = jugadorLogica.ListarJugadores();
            request.setAttribute("jugadores",jugadores);
            
                        
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            metodo = request.getMethod().toUpperCase();
            
            switch (accion) {
                case "guardar":
                    if (metodo.equals("POST")) {
                        AgregarGol(request, response);
                    }
                    break;
                case "buscar":
                    break;
                case "actualizar":
                    if (metodo.equals("POST")) {
                    }
                    
                    break;
                case "eliminar":
                    if (metodo.equals("POST")) {
                    }
                    break;
                case "nuevo":
                        break;
                default:
                    break;
            }
        }
        catch (Exception ex) {
            ModeloFormBasico modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al realizar el mantenimiento de equipos.");
            modelo.setDescErrorInterno(ex.getMessage());
            request.setAttribute("modelo", modelo);
        }
        finally {
            String vista = "WEB-INF/Vistas/";
            vista += ("".equals(accion)) ? "ListarGoles.jsp" : "Gol.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
    }
    
    private void AgregarGol (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer idjugador, minuto;
            Integer idpartido;
        
            
            try {
                idjugador = Integer.parseInt(request.getParameter("ddlJugadores"));
                
            }
            catch (NumberFormatException ex) {
                throw new Exception("El jugador seleccionado no es válido.");
            }
            try {
                idpartido = Integer.parseInt(request.getParameter("ddlPartidos"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El partido seleccionado no es válido.");
            }
            
            try {
                minuto = Integer.parseInt(request.getParameter("txtMinuto"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El minuto seleccionado no es válido.");
            }

            Partido p = new Partido();
            p.setIdPartido(idpartido);
            Jugador j = new Jugador();
            j.setIdJugador(idjugador);
            Gol g = new Gol(0,p,j,minuto);
            
            partidosLogica.IngresarGol(g);

            ModeloFormBasico modelo = new ModeloFormBasico("Gol agregado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("gol", g); 
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
