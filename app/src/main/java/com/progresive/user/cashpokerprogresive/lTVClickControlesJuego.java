package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 03/05/2016.
 */
public class lTVClickControlesJuego implements View.OnClickListener {

    private void PonerAJugar(){
        for(int i=0;i<tablero.mesaJuego.jugador.length;i++){
            if (tablero.mesaJuego.jugador[i].verapuesta()>0){
                tablero.mesaJuego.jugador[i].cargarapuesta(-1);
                tablero.mesaJuego.jugador[i].jugadortv.setText(Integer.toString(tablero.mesaJuego.jugador[i].verapuesta()));
            }

        }
    }
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
                    PonerAJugar();
                    break;
                case R.id.tvApostar:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    break;
                case R.id.tvRetiroTotal:
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(4);
                    break;
               default:
                break;
            }
        lTVClickJugadores.jugadorSeleccionado=-1;
    }
}
