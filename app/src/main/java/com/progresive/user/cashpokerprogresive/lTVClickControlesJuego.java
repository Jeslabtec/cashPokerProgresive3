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
                    tablero.mesaJuego.dealerJuego.confirmarTV.setVisibility(View.INVISIBLE);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setClickable(false);
                    break;
                case R.id.tvJugar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(2);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setVisibility(View.INVISIBLE);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setClickable(false);
                    break;
                case R.id.tvApostar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setVisibility(View.VISIBLE);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setClickable(true);
                    break;
                case R.id.tvRetiroTotal:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(4);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setVisibility(View.INVISIBLE);
                    tablero.mesaJuego.dealerJuego.confirmarTV.setClickable(false);
                    break;
                case R.id.confirmarTV:
                    if (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego()==3) {
                        tablero.mesaJuego.dealerJuego.cambiarConfirmacion();
                    }
                break;
            }
    }
}
