/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Equipo;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public interface IEquiposDatos {

    public void ActualizarEquipo(Equipo e) throws Exception;

    public Equipo BuscarEquipo(Equipo e) throws Exception;

    public void EliminarEquipo(Equipo e) throws Exception;

    public ArrayList<Equipo> ListarEquipos() throws Exception;

    void NuevoEquipo(Equipo e) throws Exception;
    
}
