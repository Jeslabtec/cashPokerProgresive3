package com.progresive.user.cashpokerprogresive;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;

/**
 * Created by JuanEsteban on 30/04/2016.
 */
public class lTVClickJugadores implements View.OnClickListener {
    private int posicion (int id) {
        switch (id) {
            case R.id.tvJugador1:
                return (0);
            case R.id.tvJugador2:
                return (1);
            case R.id.tvJugador3:
                return (2);
            case R.id.tvJugador4:
                return (3);
            case R.id.tvJugador5:
                return (4);
            case R.id.tvJugador6:
                return (5);
            case R.id.tvJugador7:
                return (6);
            default:
                return(-1);
        }

    }




    @Override
    public void onClick(View v)
    {
        int i=posicion(v.getId());
        tablero.mesaJuego.dealerJuego.jugadorSeleccionado=i;
        switch (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego())
            {
                case 1:     // fase de pago
                    tablero.mesaJuego.jugadorSeleccionadoColor(i);
                    break;
                case 2:     // fase de juego... en este caso no pasa nada porque solo se da click en iniciar juego, pero nunca se toca el jugador
                    break;
                case 3:     // fase de apuestas
                    tablero.mesaJuego.dealerJuego.jugadorSeleccionado=i;
                    tablero.mesaJuego.jugadorSeleccionadoColor(i);
                    break;
                case 4:     // fase de retiros.... esta es la mas complicada pues reqiero el nombre del jugador
                    tablero.mesaJuego.jugadorSeleccionadoColor(i);
                    tablero.mesaJuego.dealerJuego.msgConfirmarRetiro().show();
                    break;
            }

    }





}
