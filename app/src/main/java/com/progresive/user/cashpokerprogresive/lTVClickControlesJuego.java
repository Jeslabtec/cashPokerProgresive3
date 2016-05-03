package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 03/05/2016.
 */
public class lTVClickControlesJuego implements View.OnClickListener {
    public Mesa mesaDelJuego;

    public lTVClickControlesJuego(Mesa mesaJuego)
    {
        mesaDelJuego=mesaJuego;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.tvPagar:
                mesaDelJuego.dealerJuego.cambiarElEstadoDelJuego(1);
                tablero.jugadortv[1].setText("putamadre");
            break;
            case R.id.tvJugar:
                mesaDelJuego.dealerJuego.cambiarElEstadoDelJuego(2);
            break;
            case R.id.tvApostar:
                mesaDelJuego.dealerJuego.cambiarElEstadoDelJuego(3);
            break;
            case R.id.tvRetiroTotal:
                mesaDelJuego.dealerJuego.cambiarElEstadoDelJuego(4);
            break;
        }
    }
}
