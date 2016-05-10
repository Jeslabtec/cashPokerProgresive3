package com.progresive.user.cashpokerprogresive;

import android.view.View;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 02/05/2016.
 */
public class lTVClickBtnApeustasPremios implements View.OnClickListener   {



    public int posicion (int id, TextView[] datos) {
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
        int i=posicion(v.getId(),tablero.mesaJuego.dealerJuego.apuestaPremio);
        switch (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego())
           {
               case 1: // fase pagar

                   // tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 2: //jugar

               break;
               case 3: //apostar
                   //tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 4: //retirar

               break;
           }
    }
}
