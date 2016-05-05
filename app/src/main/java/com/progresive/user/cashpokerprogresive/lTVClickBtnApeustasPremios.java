package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 02/05/2016.
 */
public class lTVClickBtnApeustasPremios implements View.OnClickListener {



    public int posicion (int id) {
        switch (id) {
            case R.id.tvPremioApuesta1:
                return (1);
            case R.id.tvPremioApuesta2:
                return (2);
            case R.id.tvPremioApuesta3:
                return (5);
            case R.id.tvPremioApuesta4:
                return (10);
            case R.id.tvPremioApuesta5:
                return (15);
            case R.id.tvPremioApuesta6:
                return (20);
            default:
                return(-1);
        }

    }


    @Override
    public void onClick(View v)
    {
        int i=posicion(v.getId());
        switch (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego())
           {
               case 1: // fase pagar
                   tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 2: //jugar

               break;
               case 3: //apostar
                   tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               case 4: //retirar

               break;
           }
    }
}
