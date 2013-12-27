/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Beans.Estadio;
import Beans.Estadio.TipoCesped;
import Beans.ModeloFormBasico;
import Logica.FabricaLogica;
import Logica.IEstadioLogica;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferran
 */
public class EstadiosControlador extends HttpServlet {
    IEstadioLogica estadioLogica;
    
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
            estadioLogica = FabricaLogica.getEstadioLogica();
            
            //cargamos el modelo de la pagina
            request.setAttribute("modelo", new ModeloFormBasico());
            request.setAttribute("estadio", new Estadio());
            
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            metodo = request.getMethod().toUpperCase();
            
            switch (accion) {
                case "guardar":
                    if (metodo.equals("POST")) {
                        AgregarEstadio(request, response);
                    }
                    break;
                case "buscar":
                    BuscarEstadio(request, response);
                    break;
                case "actualizar":
                    if (metodo.equals("POST")) {
                        ActualizarEstadio(request, response);
                    }
                    
                    break;
                case "eliminar":
                    if (metodo.equals("POST")) {
                        EliminarEstadio(request, response);
                    }
                    break;
                case "nuevo":
                    //solo se envio un bean vacio como modelo.
                     
                    break;
                default:
                    ListarEstadios(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ModeloFormBasico modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al realizar el mantenimiento de estadios.");
            modelo.setDescErrorInterno(ex.getMessage());
            request.setAttribute("modelo", modelo);
        }
        finally {
            
            String vista = "WEB-INF/Vistas/";
            vista += ("".equals(accion)) ? "ListarEstadios.jsp" : "Estadio.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
    }
    
    private String ObtenerFoto (HttpServletRequest r, String nombre) throws Exception
    {
        String fotoUrl = "";
        try
        {
            //GUARDAMOS LA IMAGEN
            //-------------------
            if (r.getPart("foto").getSize() > 0 ){
                BufferedImage foto = ImageIO.read(r.getPart("foto").getInputStream());
                if (foto != null)
                {
                    ServletContext aplicacion = getServletContext();
                    
                    File archivo = new File(aplicacion.getRealPath("/images/" + nombre + ".png"));
                    archivo.createNewFile();
                    
                    ImageIO.write(foto, "png", archivo);
                    
                    //request.setAttribute("foto", "images/" + nombre + ".png");
                    fotoUrl = "images/" + nombre + ".png";
                }
            }
        }
        catch (IOException | IllegalStateException | ServletException ex)
        {
            throw new Exception("Error al obtener la imágen.");
        }
        
        return fotoUrl;
    }
    
    private void AgregarEstadio (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, tipoCesped, fotoUrl;
            int capacidad;
        
            nombre = request.getParameter("txtNombreEstadio");
            tipoCesped = request.getParameter("ddlTipoCesped");
            
            //OBTENEMOS LA FOTO
            //-----------------
            fotoUrl = ObtenerFoto(request,nombre);
           
            try {
                capacidad = Integer.parseInt(request.getParameter("txtCapacidad"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El valor de capacidad no es válido.");
            }
            
            Estadio e = new Estadio(0,nombre, capacidad, TipoCesped.valueOf(tipoCesped), fotoUrl);
            estadioLogica.NuevoEstadio(e);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Estadio agregado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("estadio", new Estadio()); 
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ActualizarEstadio (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, tipoCesped, fotoUrl;
            int capacidad,idEstadio;
            
            nombre = request.getParameter("txtNombreEstadio");
            tipoCesped = request.getParameter("ddlTipoCesped");
            idEstadio = Integer.parseInt(request.getParameter("hiddenIdEstadio"));
            
            try {
                capacidad = Integer.parseInt(request.getParameter("txtCapacidad"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El valor de capacidad no es válido.");
            }
            
            //OBTENEMOS LA FOTO
            //-----------------
            fotoUrl = ObtenerFoto(request,nombre);
            Estadio e = new Estadio();
            e.setIdEstadio(idEstadio);
            e =  estadioLogica.BuscarEstadio(e);
            e.setNombreEstadio(nombre);
            e.setCapacidad(capacidad);
            e.setCesped(TipoCesped.valueOf(tipoCesped));
            if (!"".equals(fotoUrl))
                e.setFotoUrl(fotoUrl);
            
            estadioLogica.ActualizarEstadio(e);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Estadio actualizado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //volvemos a enviar el objeto a la vista.
            request.setAttribute("estadio",e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ListarEstadios (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {         
            ArrayList<Estadio> estadios = estadioLogica.ListarEstadios();
            request.setAttribute("estadios", estadios);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void BuscarEstadio (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            //String nombreEstadio;
            Integer idEstadio = Integer.parseInt(request.getParameter("idEstadio"));
            //nombreEstadio = request.getParameter("nombreEstadio");
            
            Estadio e = new Estadio();
            //e.setNombreEstadio(nombreEstadio);
            e.setIdEstadio(idEstadio);
            e = estadioLogica.BuscarEstadio(e);

            //cargamos el bean para que se muestren los datos en la vista
            request.setAttribute("estadio", e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void EliminarEstadio (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer idEstadio = Integer.parseInt(request.getParameter("hiddenIdEstadio"));
//            String nombreEstadio;
//            nombreEstadio = request.getParameter("txtNombreEstadio");
            
            Estadio e = new Estadio();
            e.setIdEstadio(idEstadio);
            estadioLogica.EliminarEstadio(e);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Estadio eliminado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //Limpiamos el objeto equipo
            request.setAttribute("estadio",new Estadio());
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
