/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Usuario;

/**
 *
 * @author Ferran
 */
public interface IUsuarioDatos {

    Usuario Autenticar(Usuario u) throws Exception;
    
}
