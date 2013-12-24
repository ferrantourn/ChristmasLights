/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Beans.Partido;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public interface IPartidosLogica {

    ArrayList<Partido> ListarPartidos() throws Exception;
    
}
