package AccesoDatos;

import Beans.Jugador;
import java.util.ArrayList;


public interface IJugadorDatos {

    void ActualizarJugador(Jugador e) throws Exception;

    Jugador BuscarJugador(Jugador e) throws Exception;

    void EliminarJugador(Jugador e) throws Exception;

    ArrayList<Jugador> ListarJugadores() throws Exception;

    void NuevoJugador(Jugador e) throws Exception;
    
}
