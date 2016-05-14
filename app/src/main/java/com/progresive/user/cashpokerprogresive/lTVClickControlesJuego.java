package com.progresive.user.cashpokerprogresive;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by JuanEsteban on 03/05/2016.
 */
public class lTVClickControlesJuego implements View.OnClickListener {



    //Funcion que sirve para saber si alguien esta jugando

    @Override
    public void onClick(View v) {
        if (tablero.mesaJuego.hayAlguienJugando()) {
            switch (v.getId()) {
                case R.id.tvPagar:

                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(1);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                case R.id.tvJugar:

                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(2);
                    tablero.mesaJuego.cambiarBotones();
                    tablero.mesaJuego.dealerJuego.PonerAJugar();
                    break;
                case R.id.tvApostar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                case R.id.tvRetiroTotal:

                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(4);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                default:
                    break;
            }
            tablero.mesaJuego.dealerJuego.jugadorSeleccionado = -1;
        } else {
            if (R.id.tvApostar!=v.getId()) {
                tablero.mesaJuego.dealerJuego.msgPedirALgunaApuesta().show();
            }else{
                tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                tablero.mesaJuego.cambiarBotones();
            }
        }

    }
}
