package com.progresive.user.cashpokerprogresive;

import android.widget.TextView;

/**
 * Created by JuanEsteban on 25/04/2016.
 */

public class Jugador {

    // atributos
    private int Apuesta=0;
    private boolean Enmesa=true;
    public TextView jugadortv;

    // constructor
    public Jugador (TextView v)    {
       jugadortv=v;
       jugadortv.setOnClickListener(new lTVClickJugadores());
    }



    public void apostar(){
        Apuesta--;
    }




    public void cargarapuesta(int fichas){
        Apuesta+=fichas;
    }
    public void reiniciarApuesta(){
        Apuesta=0;
    }
    public boolean verSiPausado()
    {
        return(Enmesa);
    }
    public void ponerPausado()
    {
        Enmesa=!(Enmesa);
    }
    public int verapuesta()
    {
        return Apuesta;
    }


}
