package com.progresive.user.cashpokerprogresive;

import android.view.View;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 02/05/2016.
 */
public class lTVClickBtnApeustasPremios implements View.OnClickListener   {



    public int Apuesta (int id, TextView[] datos) {
        switch (id) {
            case R.id.tvPremioApuesta1:
                return (Integer.parseInt((String) datos[0].getText()));
            case R.id.tvPremioApuesta2:
                return (Integer.parseInt((String) datos[1].getText()));
            case R.id.tvPremioApuesta3:
                return (Integer.parseInt((String) datos[2].getText()));
            case R.id.tvPremioApuesta4:
                return (Integer.parseInt((String) datos[3].getText()));
            case R.id.tvPremioApuesta5:
                return (Integer.parseInt((String) datos[4].getText()));
            case R.id.tvPremioApuesta6:
                return (Integer.parseInt((String) datos[5].getText()));
            default:
                return(-1);
        }

    }


    @Override
    public void onClick(View v)
    {
        int i=Apuesta(v.getId(),tablero.mesaJuego.dealerJuego.apuestaPremio);
        switch (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego())
           {
               case 1: // fase pagar
                   if (lTVClickJugadores.jugadorSeleccionado<7&&lTVClickJugadores.jugadorSeleccionado>=0) {
                       tablero.mesaJuego.dealerJuego.msgConfirmarPago(i).show();
                   }
                   else
                   {
                       tablero.mesaJuego.dealerJuego.msgErrorApuesta.show();
                   }
                   // tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 2: //jugar

               break;
               case 3: //apostar
                   if (lTVClickJugadores.jugadorSeleccionado<7&&lTVClickJugadores.jugadorSeleccionado>=0) {
                       tablero.mesaJuego.jugador[lTVClickJugadores.jugadorSeleccionado].cargarapuesta(i);
                       tablero.mesaJuego.jugador[lTVClickJugadores.jugadorSeleccionado].jugadortv.setText(Integer.toString(tablero.mesaJuego.jugador[lTVClickJugadores.jugadorSeleccionado].verapuesta()));
                   }
                   else
                   {
                        tablero.mesaJuego.dealerJuego.msgErrorApuesta.show();
                   }
                   //tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 4: //retirar

               break;
           }
    }
}
