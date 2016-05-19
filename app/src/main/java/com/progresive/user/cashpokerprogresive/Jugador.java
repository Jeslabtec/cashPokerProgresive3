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
    }
    public void apostar(){
        Apuesta--;
        jugadortv.setText(Integer.toString(Apuesta));
    }
    /*public void cargarapuesta(int fichas){
        Apuesta+=fichas;
        if (Apuesta<0){
            Apuesta=0;
        }
        jugadortv.setText(Integer.toString(Apuesta));
    }*/
    public void cargarapuesta(int fichas){
        Apuesta+=fichas;

        if (Apuesta<SuperApuesta){
            Apuesta=SuperApuesta;
        }
        jugadortv.setText(Integer.toString(Apuesta));
    }
    public void cargarSuperApuesta(){
        SuperApuesta=Apuesta;
    }
    public void reiniciarSuperApuesta(){
        SuperApuesta=0;
    }
    public void reiniciarApuesta(){
        Apuesta=0;
        jugadortv.setText(Integer.toString(Apuesta));
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
