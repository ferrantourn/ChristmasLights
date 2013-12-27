/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Equipo;
import Beans.Estadio;
import Beans.ModeloFormBasico;
import Beans.Partido;
import Logica.FabricaLogica;
import Logica.IEquiposLogica;
import Logica.IEstadioLogica;
import Logica.IPartidosLogica;
import java.io.IOException;
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
public class PartidoControlador extends HttpServlet {

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
            
            Partido p = new Partido();
            request.setAttribute("partido",p);
            IEstadioLogica logicaestadio = FabricaLogica.getEstadioLogica();
            request.setAttribute("estadios",logicaestadio.ListarEstadios());
            
            IEquiposLogica equiposLogica = FabricaLogica.getEquiposLogica();
            ArrayList<Equipo> equipos = equiposLogica.ListarEquipos();
            request.setAttribute("equipos1",equipos);
            request.setAttribute("equipos2",equipos);

            //LoadmvEquipo(request);
                        
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            metodo = request.getMethod().toUpperCase();
            
            switch (accion) {
                case "guardar":
                    if (metodo.equals("POST")) {
                        AgregarPartido(request, response);
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
            vista += ("".equals(accion)) ? "ListarPartidos.jsp" : "Partido.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
    }
    
    private void AgregarPartido (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Date fecha;
            Integer idestadio;
            Integer idequipo1,idequipo2;
        
            try {
                SimpleDateFormat f = new SimpleDateFormat("d/M/yyyy");
                //SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fecha = f.parse(request.getParameter("txtFecha"));
            }
            catch (ParseException ex) {
                throw new Exception ("La fecha especificada no es valida.");
            }
            
             try {
                idequipo1 = Integer.parseInt(request.getParameter("ddlEquipo1"));
                idequipo2 = Integer.parseInt(request.getParameter("ddlEquipo2"));
                
            }
            catch (NumberFormatException ex) {
                throw new Exception("El equipo seleccionado no es válido.");
            }
             try {
                idestadio = Integer.parseInt(request.getParameter("ddlEstadio"));
                
            }
            catch (NumberFormatException ex) {
                throw new Exception("El estadio seleccionado no es válido.");
            }
             
             if (idequipo1 == idequipo2)
             {
                 throw new Exception("Equipo 1 y Equipo 2 deben ser paises distintos.");
             }

            Partido p = new Partido();
            Equipo[] equipos = new Equipo[2];
            Equipo e1 = new Equipo();
            e1.setIdEquipo(idequipo1);
            Equipo e2 = new Equipo();
            e2.setIdEquipo(idequipo2);
            equipos[0] = e1;equipos[1] = e2;
            p.setEquipos(equipos);
            
            Estadio est = new Estadio();
            est.setIdEstadio(idestadio);
            
            p.setFecha(fecha);
            p.setEstadio(est);
            partidosLogica.NuevoPartido(p);

            ModeloFormBasico modelo = new ModeloFormBasico("Partido agregado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("partido",new Partido()); 
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
