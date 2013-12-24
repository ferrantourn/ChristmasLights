/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccesoDatos;

import Beans.Estadio;
import java.util.ArrayList;

/**
 *
 * @author Ferran
 */
public interface IEstadioDatos {

    void ActualizarEstadio(Estadio e) throws Exception;

    Estadio BuscarEstadio(Estadio e) throws Exception;

    void EliminarEstadio(Estadio e) throws Exception;

    ArrayList<Estadio> ListarEstadios() throws Exception;

    void NuevoEstadio(Estadio e) throws Exception;
    
}
