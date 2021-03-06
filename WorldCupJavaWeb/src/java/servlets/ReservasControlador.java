/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Estadio;
import Beans.ModeloFormBasico;
import Beans.Partido;
import Beans.Reserva;
import Beans.Usuario;
import Logica.FabricaLogica;
import Logica.IPartidosLogica;
import Logica.IReservaLogica;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ferran
 */
public class ReservasControlador extends HttpServlet {
    IReservaLogica reservaLogica;

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
        
        String accion = "";
        String vista = "";
        try
        {
            Reserva r = new Reserva();
            ModeloFormBasico modelo = new ModeloFormBasico();
            request.setAttribute("reserva", r);
            request.setAttribute("estadio", new Estadio());
            request.setAttribute("partido", new Partido());
            request.setAttribute("modelo", modelo);
            
            
                       
            accion = request.getParameter("accion");
            
            if (accion != null && accion.equalsIgnoreCase("RESERVAR"))
            {
                reservaLogica = FabricaLogica.getReservaLogica();
                
                Long ci;
                Integer idPartido;
                
                try {
                    ci = Long.parseLong(request.getParameter("txtCedula"));
                }
                catch (NumberFormatException ex) {
                    throw new Exception("El valor de la cedula no es válido.");
                }
                
                try {
                    idPartido = Integer.parseInt(request.getParameter("ddlPartidos"));
                }
                catch (NumberFormatException ex) {
                    throw new Exception("El identificador de partido no es válido.");
                }
                
                Partido p = new Partido();
                p.setIdPartido(idPartido);
                
                Usuario u = new Usuario();
                u.setCi(ci);
                r.setUsuario(u);
                r.setPartido(p);
                
                r = reservaLogica.NuevoReserva(r);
                
                request.setAttribute("reserva", r);
                vista = "ReservarEntradas.jsp";
            }
            else if (accion != null && accion.equalsIgnoreCase("BUSCAR"))
            {
                reservaLogica = FabricaLogica.getReservaLogica();
                
                Integer idreserva;
                
                try {
                    idreserva = Integer.parseInt(request.getParameter("txtReserva"));
                }
                catch (NumberFormatException ex) {
                    throw new Exception("El identificador de reserva no es válido.");
                }
                
                r.setIdReserva(idreserva);
                Reserva result = reservaLogica.BuscarReserva(r);
                
                if (result != null)
                {
                    request.setAttribute("reserva", result);
                    request.setAttribute("estadio", result.getPartido().getEstadio());
                    request.setAttribute("partido", result.getPartido());
                }
                else
                {
                    ModeloFormBasico m = (ModeloFormBasico)request.getAttribute("modelo");
                    m.setMensaje("No se encontro una reserva con ese número.");
                    request.setAttribute("modelo", m);
                    request.setAttribute("reserva", new Reserva());
                }
                
                vista = "ConsultaReserva.jsp";
            }
            
            
                //accion llego en null
                //------------------
                HttpSession session = request.getSession();
                Usuario u = (Usuario)session.getAttribute("usuario");
                if (u.getTipoUsuario().equalsIgnoreCase("VENDEDOR"))
                {
                    vista = "ConsultaReserva.jsp";
                }
                else
                {
                    IPartidosLogica partidosLogica = FabricaLogica.getPartidosLogica();
ArrayList partidos=                    partidosLogica.ListarPartidos();
request.setAttribute("partidos", partidos);
                    vista = "ReservaEntradas.jsp";
                }
            
        }
        catch (Exception ex)
        {
            ModeloFormBasico modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al realizar la reserva.");
            modelo.setDescErrorInterno(ex.getMessage());
            request.setAttribute("modelo", modelo);
        }
        finally {
            RequestDispatcher despachador = request.getRequestDispatcher("WEB-INF/Vistas/" + vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
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
