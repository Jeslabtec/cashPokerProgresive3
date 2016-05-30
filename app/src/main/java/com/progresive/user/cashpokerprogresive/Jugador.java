package com.progresive.user.cashpokerprogresive;

import android.view.View;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 25/04/2016.
 */

public class Jugador {

    // atributos
    private int Apuesta=0;
    //Evita en la fase de apostar se retiren fichas ya jugadas en la fase de juego
    private int SuperApuesta=0;
    private boolean Enmesa=true;
    public TextView jugadortv;

    // constructor
    public Jugador (TextView v)    {
       jugadortv=v;
       jugadortv.setOnClickListener(new lTVClickJugadores());
        jugadortv.setOnLongClickListener(new LongclickPausarJugador());
    }

    public void cargarapuesta(int fichas){
        if(Enmesa) {
            Apuesta += fichas;
            if (Apuesta < SuperApuesta) {
                Apuesta = SuperApuesta;
            }
            jugadortv.setText(Integer.toString(Apuesta));
        }
    }
    public void apostemos(){
        if(Enmesa) {
            Apuesta--;
            SuperApuesta--;
            jugadortv.setText(Integer.toString(Apuesta));
        }
    }
    public void cargarSuperApuesta(){
        if(Enmesa) {
            SuperApuesta = Apuesta;
        }
    }


    public void reiniciarApuesta(){
        Apuesta=0;
        SuperApuesta=0;
        Enmesa=true;
        jugadortv.setText(Integer.toString(Apuesta));
    }
    public boolean verSiPausado()
    {
        return(Enmesa);
    }
    public void ponerPausado()
    {if(Apuesta>0) {
        Enmesa = !(Enmesa);
        if (!Enmesa) {
            jugadortv.setBackgroundColor(0xFFCD1C1C);
            jugadortv.setTextColor(0xffe0e0e0);
        } else {
            jugadortv.setBackgroundColor(0x00000000);
            jugadortv.setTextColor(0xff000000);
        }
    }
    }
    public  void despausar(){
        jugadortv.setBackgroundColor(0x00000000);
        Enmesa=true;
    }
    public int verapuesta()
    {
        return Apuesta;
    }
}
