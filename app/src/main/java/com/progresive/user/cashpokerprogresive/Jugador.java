package com.progresive.user.cashpokerprogresive;

import android.view.View;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 25/04/2016.
 */

public class Jugador {

    // atributos
    //Apuesta Guarda la apuesta que tiene la persona disponible, es el valor que s emuestra en pantalla
    private int Apuesta=0;
    //SuperApuesta Evita en la fase de apostar se retiren fichas ya jugadas en la fase de juego
    private int SuperApuesta=0;
    //Es un valor que dice si la persona esta en la mesa o pidio una pausa
    private boolean Enmesa=true;

    public TextView jugadortv;
    //Variable que dice si el jugador fue seleccionado
    private boolean seleccionado=false;

    // constructor
    public Jugador (TextView v)    {
        jugadortv=v;
        jugadortv.setOnClickListener(new lTVClickJugadores());
        jugadortv.setOnLongClickListener(new LongclickPausarJugador());
    }
//cargar la apuesta en el textview
    public void cargarapuesta(int fichas){
        if(Enmesa) {
            Apuesta += fichas;
            if (Apuesta < SuperApuesta) {
                Apuesta = SuperApuesta;
            }
            jugadortv.setText(Integer.toString(Apuesta));
        }
    }
    //Se llama cuando se inicia el juego
    public void apostemos(){
        if(Enmesa) {
            Apuesta--;
            SuperApuesta--;
            jugadortv.setText(Integer.toString(Apuesta));
        }
    }//Permite igualar la superapuesta a la apuesta cada vez que se juega
    public void cargarSuperApuesta(){
        if(Enmesa) {
            SuperApuesta = Apuesta;
        }
    }
// Permite reiniciar las apuestas cuando un jugaador se retire
    public void reiniciarApuesta(){
        Apuesta=0;
        SuperApuesta=0;
        Enmesa=true;
        seleccionado=false;
        jugadortv.setText(Integer.toString(Apuesta));
    }
    //pregunta si la persona esta en la mesa o no
    public boolean verSiPausado()
    {
        return(Enmesa);
    }
    // mientras la apuesta se mayor que cero permite poner o quitar la pausa
    public void ponerPausado()
    {
        if(SuperApuesta>0) {
            Enmesa = !(Enmesa);
            if (!Enmesa) {
                Pausar();
            }
            else {
                Habilitar();
            }
        }
    }
    //  Despausa la mesa
    public  void despausar(){
        Habilitar();
        Enmesa=true;
    }
//Retorna la apuesta
    public int verapuesta()
    {
        return Apuesta;
    }
// funciones graficas
    public void Bloquear() {
        if (Enmesa) {
            jugadortv.setBackgroundColor(0x00000000);
            jugadortv.setTextColor(0xffe0e0e0);
            jugadortv.setEnabled(false);
            seleccionado = false;
        }
    }

    public void Habilitar(){
        if (Enmesa) {
            jugadortv.setBackgroundColor(0x00000000);
            jugadortv.setTextColor(0xff000000);
            jugadortv.setEnabled(true);
            seleccionado = false;
        }
    }
    public void Seleccionar(){
        if (Enmesa) {
            jugadortv.setBackgroundColor(0x00000000);
            jugadortv.setTextColor(0xffffffff);
            jugadortv.setEnabled(true);
            seleccionado = true;
        }
    }
    public void Pausar(){
        jugadortv.setBackgroundColor(0xffDCDCDC);
        jugadortv.setTextColor(0xffffffff);
        jugadortv.setEnabled(true);
        seleccionado=false;
    }
    //Cuando es el jugador seleccionado retorna 1 sino retorna 0
    public boolean EstoySeleccionado(){
        return seleccionado;
    }
}
