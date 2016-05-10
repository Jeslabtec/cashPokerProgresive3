package com.progresive.user.cashpokerprogresive;


import android.widget.TextView;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    Jugador[] jugador=new Jugador[7];
    Dealer dealerJuego;


// constructor de la clase Mesa:  el programa
    public Mesa(TextView[] v)    {
        TextView[] atributosDealer={v[7],v[8],v[9],v[10],v[11],v[12],v[13],v[14],v[15],v[16]};
        for (int i=0; i<jugador.length;i++){
            jugador[i]=new Jugador(v[i]);
        }
        dealerJuego=new Dealer(atributosDealer);
    }





}
