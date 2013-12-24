/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filtros;

import Beans.ModeloFormBasico;
import Beans.Usuario;
import Logica.FabricaLogica;
import Logica.IUsuarioLogica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ferran
 */
public class LoginFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public LoginFilter() {
    }    
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        ModeloFormBasico  modelo = new ModeloFormBasico();
        PrintWriter out= response.getWriter();  
        try
        {
            HttpServletRequest req = (HttpServletRequest) request;
            //HttpServletResponse res = (HttpServletResponse) response;
            HttpSession s = req.getSession();
                  
            Usuario u = (Usuario)s.getAttribute("usuario");
            
            request.setAttribute("modelo", modelo);
            
            if (u == null) 
            {
                //No hay un usuario autenticado, Autenticamos el usuario
                //------------------------------------------------------
                String usuario = request.getParameter("NombreUsuario");
                String pass = request.getParameter("Contrasena");
                if (usuario != null && pass != null)
                {
                    IUsuarioLogica logicaUsuarios = FabricaLogica.getUsuarioLogica();
                    Usuario user = new Usuario();
                    user.setUsuario(usuario);
                    user.setPassword(pass);
                    user = logicaUsuarios.Autenticar(user);
                    
                    if (user != null)
                    {
                        //AGREGAMOS EL USUARIO A LA SESSION
                        //---------------------------------
                        request.setAttribute("usuario", user);
                        
                        //sigue ejecucion normal.
                        chain.doFilter(request, response);
                    }
                    else
                    {
                        //modelo.setMensaje("Usuario o contraseña incorrectos.");
                        request.setAttribute("error", "Usuario o contraseña incorrectos.");
                        
                        //out.print("Usuario o contraseña incorrectos.");
                        RequestDispatcher despachador = request.getRequestDispatcher("");
                        
                        despachador.include(request, response); 
                    }
                }
            }
            
        }
        catch (Exception ex)
        {
            //modelo = (ModeloFormBasico)request.getAttribute("modelo");
            modelo.setMensaje("Error: Se produjo un error al hacer login.");
            modelo.setDescErrorInterno(ex.getMessage());
        }
        finally
        {
           //request.setAttribute("modelo", modelo);
        }
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
//            if (debug) {                
//                log("LoginFilter:Initializing filter");
//            }
        }
    }

    
    
}
