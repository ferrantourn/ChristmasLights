/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Beans.Gol;
import Beans.Partido;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public interface IPartidosLogica {

    void NuevoPartido(Partido p) throws Exception;
    ArrayList<Partido> ListarPartidos() throws Exception;
    Gol IngresarGol (Gol g) throws Exception;
}
