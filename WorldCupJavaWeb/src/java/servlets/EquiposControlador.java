/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Equipo;
import Beans.ModeloFormBasico;
import Logica.FabricaLogica;
import Logica.IEquiposLogica;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferran
 */
@WebServlet(name = "EquiposControlador", urlPatterns = {"/Equipo"})
public class EquiposControlador extends HttpServlet {

    IEquiposLogica equipoLogica;
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
            
            equipoLogica = FabricaLogica.getEquiposLogica();
            
            LoadmvEquipo(request);
                        
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            
            
            metodo = request.getMethod().toUpperCase();
            
            switch (accion) {
                case "guardar":
                    if (metodo.equals("POST")) {
                        AgregarEquipo(request, response);
                    }
                    break;
                case "buscar":
                    BuscarEquipo(request, response);
                    break;
                case "actualizar":
                    if (metodo.equals("POST")) {
                        ActualizarEquipo(request, response);
                    }
                    
                    break;
                case "eliminar":
                    if (metodo.equals("POST")) {
                        EliminarEquipo(request, response);
                    }
                    break;
                case "nuevo":
                        break;
                default:
                    ListarEquipos(request, response);
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
            vista += ("".equals(accion)) ? "ListarEquipos.jsp" : "Equipo.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
    }
    
    
    protected void LoadmvEquipo(HttpServletRequest request) {
        String paramPais, paramEntrenador;
        boolean paramCabezaSerie;
        
        paramPais = request.getParameter("txtSeleccion");
        paramEntrenador = request.getParameter("txtEntrenador");
        paramCabezaSerie = Boolean.parseBoolean(request.getParameter("chkCabezaSerie"));
        
        Equipo e = new Equipo();
        
        if (paramPais != null) {
            e.setPais(paramPais);
        }
        
        if (paramEntrenador != null) {
            e.setEntrenador(paramEntrenador);
        }
        
        //cargo el bean equipo
        request.setAttribute("equipo", e);
        
        //cargo el bean modelo.
        ModeloFormBasico m = new ModeloFormBasico();
        request.setAttribute("modelo", m);
    }
    
    
    private void AgregarEquipo (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, entrenador;
            boolean cabezaSerie;
        
            nombre = request.getParameter("txtSeleccion");
            entrenador = request.getParameter("txtEntrenador");
            
            try {
                String[] values = request.getParameterValues("chkCabezaSerie");
                cabezaSerie = "on".equalsIgnoreCase(values[0]);
            }
            catch (Exception ex) {
                throw ex;
            }

            Equipo e = new Equipo(0,nombre, entrenador, cabezaSerie);
            equipoLogica.NuevoEquipo(e);

            ModeloFormBasico modelo = new ModeloFormBasico("Equipo agregado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("equipo",new Equipo()); 
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ActualizarEquipo (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, entrenador;
            boolean cabezaSerie;
            int idEstadio;
        
            nombre = request.getParameter("txtSeleccion");
            entrenador = request.getParameter("txtEntrenador");
            idEstadio = Integer.parseInt(request.getParameter("hiddenIdEquipo"));
            
            try {
                String[] values = request.getParameterValues("chkCabezaSerie");
                if (values != null)
                    cabezaSerie = "on".equalsIgnoreCase(values[0]);
                else
                    cabezaSerie = false;
            }
            catch (Exception ex) {
                throw ex;
            }

            Equipo e = new Equipo(idEstadio,nombre, entrenador, cabezaSerie);
            equipoLogica.ActualizarEquipo(e);

            ModeloFormBasico modelo = new ModeloFormBasico("Equipo actualizado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //volvemos a enviar el objeto a la vista.
            request.setAttribute("equipo",e); 
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ListarEquipos (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {         
            ArrayList<Equipo> equipos = equipoLogica.ListarEquipos(); 
            request.setAttribute("equipos", equipos);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void BuscarEquipo (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
//            String pais;
//
//            pais = request.getParameter("pais");
            
            Equipo e = new Equipo();
            e.setIdEquipo(idEquipo);
            e = equipoLogica.BuscarEquipo(e);
            
            //cargamos el bean para que se muestren los datos en la vista
            request.setAttribute("equipo", e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void EliminarEquipo (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer idEquipo = Integer.parseInt(request.getParameter("hiddenIdEquipo"));
            
            Equipo e = new Equipo();
            e.setIdEquipo(idEquipo);
            equipoLogica.EliminarEquipo(e);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Equipo eliminado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //Limpiamos el objeto equipo
            request.setAttribute("equipo",new Equipo());
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
