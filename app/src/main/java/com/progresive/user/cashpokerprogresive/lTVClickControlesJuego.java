package com.progresive.user.cashpokerprogresive;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by JuanEsteban on 03/05/2016.
 */
public class lTVClickControlesJuego implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (tablero.mesaJuego.hayAlguienJugando() || tablero.mesaJuego.verElEstadoDelJuego()==2) {
            switch (v.getId()) {
                case R.id.tvPagar:
                    tablero.mesaJuego.cambiarElEstadoDelJuego(1);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                case R.id.tvJugar:
                    tablero.mesaJuego.cambiarElEstadoDelJuego(2);
                    tablero.mesaJuego.cambiarBotones();
                    tablero.mesaJuego.PonerAJugar();
                    break;
                case R.id.tvApostar:
                    tablero.mesaJuego.cambiarElEstadoDelJuego(3);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                case R.id.tvRetiroTotal:
                    tablero.mesaJuego.cambiarElEstadoDelJuego(4);
                    tablero.mesaJuego.cambiarBotones();
                    break;
                default:
                    break;
            }
        } else {
            if (R.id.tvApostar!=v.getId()) {
                tablero.mesaJuego.mensaje.msgPedirALgunaApuesta().show();
                tablero.mesaJuego.cambiarElEstadoDelJuego(3);
                tablero.mesaJuego.cambiarBotones();
            }
        }
    }
}
