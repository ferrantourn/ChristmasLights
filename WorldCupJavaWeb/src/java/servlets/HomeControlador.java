/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Usuario;
import java.io.IOException;
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
public class HomeControlador extends HttpServlet {

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
        String form = "";
        String accion = "";
        String vista = "";
       try
       {
           accion = request.getParameter("accion");
           
           if (accion != null && accion.equalsIgnoreCase("SALIR"))
           {
               //BORRAMOS EL USUARIO DE LA SESSION
               //---------------------------------
               HttpSession session = request.getSession();
               session.setAttribute("usuario",null);
               vista = "index.jsp";
               
               response.sendRedirect("index.jsp");
               
           }
           else{
               //AGREGAMOS EL USUARIO A LA SESSION
               //---------------------------------
               HttpSession session = request.getSession();
               if ((Usuario)session.getAttribute("usuario") == null){
                   Usuario u = (Usuario)request.getAttribute("usuario");
                   session.setAttribute("usuario",u);
               }
               vista = "WEB-INF/Vistas/Home.jsp";
                //String vista = "WEB-INF/Vistas/Home.jsp";
            //vista += ("".equals(accion)) ? "ListarEquipos.jsp" : "Home.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
//           if (u.getTipoUsuario().equalsIgnoreCase("VENDEDOR"))
//           {
//               form = "HomeVendedor";
//           }
           }
       }
       catch (Exception ex)
       {
           
       }
//       finally {
//            
//           
//        }
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
