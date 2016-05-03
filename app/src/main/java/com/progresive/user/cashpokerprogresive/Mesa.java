package com.progresive.user.cashpokerprogresive;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    Jugador[] jugadores=new Jugador[7];
    Dealer dealerJuego;

    public Mesa()
        {
            //Mesa NuevaMesa=new Mesa();
        for(int i=0;i<jugadores.length;i++) {
            jugadores[i] = new Jugador();
            }
        dealerJuego=new Dealer();
        }

}
