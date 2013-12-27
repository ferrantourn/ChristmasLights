/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public interface IConsultasDatos {

    ArrayList<Jugador> TablaGoleadores() throws Exception;
    
}
