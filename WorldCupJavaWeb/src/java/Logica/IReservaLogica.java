/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Beans.Reserva;

/**
 *
 * @author Ferran
 */
public interface IReservaLogica {

    Reserva NuevoReserva(Reserva r) throws Exception;
    Reserva BuscarReserva(Reserva r) throws Exception;
}
