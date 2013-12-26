package servlets;

import Beans.Equipo;
import Beans.Estadio;
import Beans.Jugador;
import Beans.ModeloFormBasico;
import Logica.FabricaLogica;
import Logica.IEquiposLogica;
import Logica.IJugadorLogica;
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
public class JugadorControlador extends HttpServlet {
    IJugadorLogica jugadorLogica;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion= null,metodo;
        try {
            jugadorLogica = FabricaLogica.getJugadorLogica();
            
            //cargamos el modelo de la pagina
            request.setAttribute("modelo", new ModeloFormBasico());
            request.setAttribute("jugador", new Jugador());
            IEquiposLogica equiposLogica = FabricaLogica.getEquiposLogica();
            request.setAttribute("equipos",equiposLogica.ListarEquipos());
            
            accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase().replace(" ", "") : "";
            metodo = request.getMethod().toUpperCase();
            
            switch (accion) {
                case "guardar":
                    if (metodo.equals("POST")) {
                        AgregarJugador(request, response);
                    }
                    break;
                case "buscar":
                    BuscarJugador(request, response);
                    break;
                case "actualizar":
                    if (metodo.equals("POST")) {
                        ActualizarJugador(request, response);
                    }
                    
                    break;
                case "eliminar":
                    if (metodo.equals("POST")) {
                        EliminarJugador(request, response);
                    }
                    break;
                case "nuevo":
                    //solo se envio un bean vacio como modelo.
                     
                    break;
                default:
                    ListarJugadores(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ModeloFormBasico modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al realizar el mantenimiento de jugadores.");
            modelo.setDescErrorInterno(ex.getMessage());
            request.setAttribute("modelo", modelo);
        }
        finally {
            
            String vista = "WEB-INF/Vistas/";
            vista += ("".equals(accion)) ? "ListarJugadores.jsp" : "Jugador.jsp";
            RequestDispatcher despachador = request.getRequestDispatcher(vista);
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        }
    }
    
    
    
    private void AgregarJugador (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, apellido, posicion;
            Integer idequipo;
            nombre = request.getParameter("txtNombre");
            apellido = request.getParameter("txtApellido");
            posicion = request.getParameter("txtPosicion");
            
            try {
                idequipo = Integer.parseInt(request.getParameter("ddlEquipo"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El equipo seleccionado no es válido.");
            }
            //**** EQUIPO: cargarlo desde una lista
            //equipo=null;
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(idequipo);
            
            Jugador e = new Jugador(0,nombre, apellido, posicion, equipo);
            
            jugadorLogica.NuevoJugador(e);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Jugador agregado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("jugador", new Jugador()); 
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ActualizarJugador (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            String nombre, apellido, posicion;
            Integer idequipo = null,idJugador;
        
            nombre = request.getParameter("txtNombre");
            apellido = request.getParameter("txtApellido");
            posicion = request.getParameter("txtPosicion");
            idJugador = Integer.parseInt(request.getParameter("hiddenIdJugador"));
            
            try {
                idequipo = Integer.parseInt(request.getParameter("ddlEquipo"));
            }
            catch (NumberFormatException ex) {
                throw new Exception("El equipo seleccionado no es válido.");
            }
            
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(idequipo);
            
            Jugador player = new Jugador(idJugador, nombre, apellido, posicion, equipo);
           
            jugadorLogica.ActualizarJugador(player);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Jugador actualizado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //creamos un objeto vacio para que lleguen los valores limpios a la vista
            request.setAttribute("jugador", player); 
            
            //enviamos tambien el id del equipo seleccionado
            request.setAttribute("selectedEquipoId",player.getEquipoPertenece().getIdEquipo());
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void ListarJugadores (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {         
            ArrayList<Jugador> jugadores = jugadorLogica.ListarJugadores();
            request.setAttribute("jugadores", jugadores);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void BuscarJugador (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer IdJugador = Integer.parseInt(request.getParameter("idJugador"));
            
            Jugador player = new Jugador();
            player.setIdJugador(IdJugador);
            player = jugadorLogica.BuscarJugador(player);

            //cargamos el bean para que se muestren los datos en la vista
            request.setAttribute("jugador", player);
            
            //enviamos tambien el id del equipo seleccionado
            request.setAttribute("selectedEquipoId",player.getEquipoPertenece().getIdEquipo());
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    private void EliminarJugador (HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            Integer idJugador = Integer.parseInt(request.getParameter("hiddenIdJugador"));
            
            Jugador player = new Jugador();
           player.setIdJugador(idJugador);
            jugadorLogica.EliminarJugador(player);
            
            ModeloFormBasico modelo = new ModeloFormBasico("Jugador eliminado correctamente!");
            
            request.setAttribute("modelo", modelo);
            
            //Limpiamos el objeto jugador
            request.setAttribute("jugador",new Jugador());
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
