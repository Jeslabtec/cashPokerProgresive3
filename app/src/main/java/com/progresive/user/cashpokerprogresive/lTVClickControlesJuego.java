package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 03/05/2016.
 */
public class lTVClickControlesJuego implements View.OnClickListener {
    @Override
    public void onClick(View v)
    {
            switch(v.getId())
            {
                case R.id.tvPagar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(1);
                    break;
                case R.id.tvJugar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(2);
                    break;
                case R.id.tvApostar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    break;
                case R.id.tvRetiroTotal:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(4);
                    break;
            }
    }
}
