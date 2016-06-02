package com.progresive.user.cashpokerprogresive;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by user on 02/06/2016.
 */
public class MensajesAlerta {

    AlertDialog msgConfirmarPago(final int Premio) {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Confirma el pago de este premio?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                PorcentajePremio = (float) Premio;
                tablero.mesaJuego.AbrirCodiaut();
                dialog.cancel();
            }
        });

        creaMensajes.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.mesaJuego.dealerJuego.jugadorSeleccionado = -1;
                dialog.cancel();
            }
        });

        return creaMensajes.create();

    }

    //Acciones que permiten confirmar el pago, es valida cuando el codigo ingresado en codigoaut pertenece a un dealer o supervisor
    void AccionesConfirmarPago() {
        double i = Double.parseDouble((String) ProgresivoTV.getText());
        tablero.mesaJuego.jugador[jugadorSeleccionado].cargarapuesta((int) (((double) PorcentajePremio) / 100 * i));
        tablero.mesaJuego.restringirJugador(jugadorSeleccionado);
        tablero.mesaJuego.jugador[jugadorSeleccionado].cargarSuperApuesta();
        jugadorSeleccionado = -1;
        tablero.mesaJuego.jugadorSeleccionadoColor(-1);
    }

    //------------------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarRetiro() {

        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("¿Seguro qué desea retirarse?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                msgConfirmarDinero().show();
                dialog.cancel();
            }
        });
        creaMensajes.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return creaMensajes.create();
    }

    //-----------------------------------------------------------------------------------------------------------//

    AlertDialog msgErrorApuesta() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage(R.string.DebSelJug);
        creaMensajes.setCancelable(true);
        creaMensajes.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return creaMensajes.create();

    }

    //-----------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarDinero() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Pagar al Jugador " + tablero.mesaJuego.jugador[jugadorSeleccionado].jugadortv.getText() + " fichas");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tablero.mesaJuego.jugador[jugadorSeleccionado].reiniciarApuesta();
                tablero.mesaJuego.restringirJugador(jugadorSeleccionado);
                jugadorSeleccionado = -1;
                if (!tablero.mesaJuego.hayAlguienJugando()) {
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    tablero.mesaJuego.cambiarBotones();
                }
                dialog.cancel();
            }
        });

        return creaMensajes.create();
    }

    //--------------------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgPedirALgunaApuesta() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage(R.string.Paracontinuar);
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return creaMensajes.create();
    }
    //-----------------------------------------------------------------------------------------------------------//
}
