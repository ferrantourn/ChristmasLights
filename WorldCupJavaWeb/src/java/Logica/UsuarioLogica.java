/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import AccesoDatos.FabricaDatos;
import AccesoDatos.IUsuarioDatos;
import Beans.Usuario;


/**
 *
 * @author Ferran
 */
public class UsuarioLogica implements IUsuarioLogica {
     private static UsuarioLogica instancia = null;
    
    public static IUsuarioLogica getInstancia()
    {
        if (instancia == null) {
            instancia = new UsuarioLogica();
        }
        
        return instancia;
    }
    
    //constructor privado por el singleton.
    private UsuarioLogica () {}
    
    
    @Override
    public Usuario Autenticar(Usuario e) throws Exception
    {
        try
        {
            IUsuarioDatos datos = FabricaDatos.getUsuarioDatos();
            return datos.Autenticar(e);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
