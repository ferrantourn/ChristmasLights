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
public interface IJugadorDatos {

    void ActualizarJugador(Jugador e) throws Exception;

    Jugador BuscarJugador(Jugador e) throws Exception;

    void EliminarJugador(Jugador e) throws Exception;

    ArrayList<Jugador> ListarJugadores() throws Exception;

    void NuevoJugador(Jugador e) throws Exception;
    
}
