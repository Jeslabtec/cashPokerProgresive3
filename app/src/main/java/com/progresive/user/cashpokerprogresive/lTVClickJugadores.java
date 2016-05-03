package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 30/04/2016.
 */
public class lTVClickJugadores implements View.OnClickListener {

    public Jugador jugador;
    public Dealer  dealerJuego;

    public lTVClickJugadores(Jugador jugadorElegido,Dealer dealer)
    {
        jugador=jugadorElegido;
        dealerJuego=dealer;
    }

    @Override
    public void onClick(View v)
    {
        switch (dealerJuego.verElEstadoDelJuego())
        {
            case 1:     // fase de pago
                jugador.cargarapuesta(dealerJuego.ponerFicha());
            break;
            case 2:     // fase de juego... en este caso no pasa nada porque solo se da click en iniciar juego, pero nunca se toca el jugador

            break;
            case 3:     // fase de apuestas
                jugador.cargarapuesta(dealerJuego.ponerFicha());
            break;
            case 4:     // fase de retiros.... esta es la mas complicada pues reqiero el nombre del jugador

            break;
        }

    }
}
