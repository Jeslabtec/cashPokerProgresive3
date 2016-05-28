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
                if (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego() == 3) {
                    tablero.mesaJuego.dealerJuego.cambiarRestando();
                    return 0;
                }else{
                    return (Integer.parseInt((String) datos[5].getText()));
                }
            default:
                return 0;
        }

    }

    @Override
    public void onClick(View v)
    {
        int i=Apuesta(v.getId(),tablero.mesaJuego.dealerJuego.apuestaPremio);
        switch (tablero.mesaJuego.dealerJuego.verElEstadoDelJuego())
           {//----------------------------------------------------------------------------------------------------//
               case 1: // fase pagar
                   if (i>9) {tablero.mesaJuego.dealerJuego.necesariosupervisor = true;}//Configura si se necesita supervisor o no.

                   if (tablero.mesaJuego.dealerJuego.jugadorSeleccionado>=0) {

                       tablero.mesaJuego.dealerJuego.msgConfirmarPago(i).show();
                   }
                   else
                   {
                       tablero.mesaJuego.dealerJuego.msgErrorApuesta().show();
                   }

               break;
               //-----------------------------------------------------------------------------------------------------------------------//
               case 2: //jugar

               break;
               //-----------------------------------------------------------------------------------------------------------------//
               case 3: //apostar

                   if (tablero.mesaJuego.dealerJuego.jugadorSeleccionado>=0) {
                       if (tablero.mesaJuego.dealerJuego.verSiRestando()) {
                           tablero.mesaJuego.jugador[tablero.mesaJuego.dealerJuego.jugadorSeleccionado].cargarapuesta(-i);
                       }else{
                           tablero.mesaJuego.jugador[tablero.mesaJuego.dealerJuego.jugadorSeleccionado].cargarapuesta(i);
                       }
                   }
                   else
                   {
                        tablero.mesaJuego.dealerJuego.msgErrorApuesta().show();
                   }
                   //tablero.mesaJuego.dealerJuego.tomarFicha(i);
               break;
               //---------------------------------------------------------------------------------------------------------------------------------------//
               case 4: //retirar

               break;
           }
    }
}
