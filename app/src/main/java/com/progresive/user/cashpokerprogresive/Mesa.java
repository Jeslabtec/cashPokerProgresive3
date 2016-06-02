package com.progresive.user.cashpokerprogresive;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    Jugador[] jugador=new Jugador[7];
    ClaseApuestaPremio[] ApuestaPremio=new ClaseApuestaPremio[6];

    ControlesJuego pagarTV;
    ControlesJuego jugarTV;
    ControlesJuego apostarTV;
    ControlesJuego retirarseTV;

    ClaseDelProgresivo ProgresivoTV;

    private int jugadorSeleccionado; //Es una variable que guarda que jugadors a sido seleccionado
    private int EstadoJuego=3;
// constructor de la clase Mesa:  el programa
    public Mesa(TextView[] v)    {

        for (int i=0; i<jugador.length;i++){
            jugador[i]=new Jugador(v[i]);
        }
        for (int i=0; i<ApuestaPremio.length;i++){
            ApuestaPremio[i]=new ClaseApuestaPremio(v[i+7],i);
        }

        pagarTV = new ControlesJuego(v[13]);
        jugarTV = new ControlesJuego(v[14]);
        apostarTV = new ControlesJuego(v[15]);
        retirarseTV = new ControlesJuego(v[16]);

        ProgresivoTV = new ClaseDelProgresivo(v[18]);
        cambiarBotones();
    }

   //---------------------------------------------------------------------------------
//funcion que cambia el textview mientras es undido
    public void jugadorSeleccionadoColor(int i){
        for (int j=0;j<jugador.length;j++) {
            if(i==j) {
                jugador[j].jugadortv.setTextColor(0xffffffff);
            }else if(jugador[j].jugadortv.isEnabled() && jugador[j].verSiPausado()){
                jugador[j].jugadortv.setTextColor(0xff000000);
            }
        }
    }
//Funcion que pregunta quienes estan en cero y los bloquea
    public void restringirJugadores(){
        for(int i=0;i<jugador.length;i++){
            if(jugador[i].verapuesta()==0){
                jugador[i].jugadortv.setTextColor(0xffe0e0e0);
                jugador[i].jugadortv.setEnabled(false);
            }
        }
    }
    public void  restringirJugador(int i){
        jugador[i].jugadortv.setTextColor(0xffe0e0e0);
        jugador[i].jugadortv.setEnabled(false);
    }
    //Funcion que habilita a los jugadores en la fase de apuesta
    public void habilitarJugadores()
    {
        {
            for (int i = 0; i < jugador.length; i++) {
                jugador[i].jugadortv.setTextColor(0xFF000000);
                jugador[i].despausar();
                jugador[i].jugadortv.setEnabled(true);
            }
        }
    }
    //Funcion que pregunta si hay alguien jugando si lo hay responde con true
    public boolean hayAlguienJugando(){
        for(int i = 0; i < jugador.length; i++){
           if (jugador[i].verapuesta()!=0 &&jugador[i].verSiPausado()) {
             return true;
           }
        }
        return false;
    }
    //Funcion que me dice cuantos jugadores hay en mesa
    public int cuantosJugando() {
        int jugadores = 0;
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            if (tablero.mesaJuego.jugador[i].verapuesta() > 0 && tablero.mesaJuego.jugador[i].verSiPausado()) {
                jugadores++;
            }
        }
        return jugadores;
    }
//Funcion para iniciar el juego
    public void PonerAJugar() {
        progresivoLoco();
        cambiarElEstadoDelJuego(2);
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            if (tablero.mesaJuego.jugador[i].verapuesta() > 0) {
                tablero.mesaJuego.jugador[i].cargarSuperApuesta();
                tablero.mesaJuego.jugador[i].apostemos();
            }
        }
    }

    //Timer***********************************************************************************************
    final Handler handler = new Handler();
    Timer t = new Timer();

    public void progresivoLoco() {
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        double intermedio;
                        if (EstadoJuego == 2) {
                            ProgresivoTV.aumentoAleatorio();
                            progresivoLoco();
                        }
                    }
                });
            }
        }, 100);
    }
//*************************************************************************************************************************
    int verElEstadoDelJuego() {
        return (EstadoJuego);
    }

    void cambiarElEstadoDelJuego(int NuevoEstado) {
        EstadoJuego = NuevoEstado;
    }
    //Que pasa con los textview cuando se unde cualquiera de los controles//
    private void BotonesdeApuesta(){

        dealerJuego.apuestaPremio[0].setText(R.string.Apuesta1);
        dealerJuego.apuestaPremio[1].setText(R.string.Apuesta2);
        dealerJuego.apuestaPremio[2].setText(R.string.Apuesta3);
        dealerJuego.apuestaPremio[3].setText(R.string.Apuesta4);
        dealerJuego.apuestaPremio[4].setText(R.string.Apuesta5);
        dealerJuego.ponerSumando();

        retirarseTV.Bloquear();
        pagarTV.Bloquear();
        jugarTV.Habilitar();
        apostarTV.Seleccionar();

        for (int i=0;i<dealerJuego.apuestaPremio.length;i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.VISIBLE);
            dealerJuego.apuestaPremio[i].setBackgroundResource(R.drawable.coins);
        }
        dealerJuego.AvisoTV.setText(R.string.Apostemos);
        habilitarJugadores();
    }
    //----------------------------------------------------------------------------------------//
    private void BotonesdePago(){
        dealerJuego.apuestaPremio[0].setText("1");
        dealerJuego.apuestaPremio[1].setText("3");
        dealerJuego.apuestaPremio[2].setText("9");
        dealerJuego.apuestaPremio[3].setText("27");
        dealerJuego.apuestaPremio[4].setText("81");
        dealerJuego.apuestaPremio[5].setText("100");

        retirarseTV.Bloquear();
        pagarTV.Seleccionar();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i=0; i<dealerJuego.apuestaPremio.length; i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.VISIBLE);
            dealerJuego.apuestaPremio[i].setBackgroundResource(R.drawable.descarga);
        }
        dealerJuego.AvisoTV.setText(R.string.Pago);
        jugadorSeleccionadoColor(-1);
    }
    //--------------------------------------------------------------------------------------------------//
    private void BotonesdeJuego(){
        dealerJuego.apuestaPremio[0].setText("1");
        dealerJuego.apuestaPremio[1].setText("3");
        dealerJuego.apuestaPremio[2].setText("9");
        dealerJuego.apuestaPremio[3].setText("27");
        dealerJuego.apuestaPremio[4].setText("81");
        dealerJuego.apuestaPremio[5].setText("100");


        retirarseTV.Bloquear();
        pagarTV.Habilitar();
        jugarTV.Seleccionar();
        apostarTV.Habilitar();


        for (int i=0;i<dealerJuego.apuestaPremio.length;i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.VISIBLE);
            dealerJuego.apuestaPremio[i].setBackgroundResource(R.drawable.descarga);
        }
        dealerJuego.AvisoTV.setText(R.string.jogo);
        jugadorSeleccionadoColor(-1);
        restringirJugadores();
    }
    //---------------------------------------------------------------------------------------------------------------//
    private void BotonesdeRetiro(){


        retirarseTV.Seleccionar();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i=0;i<dealerJuego.apuestaPremio.length;i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.INVISIBLE);
        }

        dealerJuego.AvisoTV.setText(R.string.Retirando);
        restringirJugadores();
        jugadorSeleccionadoColor(-1);

    }
    //-------------------------------------------------------------------------------------------------------------------//
        // metodos
    //dependiendo del estado del juego se habilitaran o desabilitaran algunos botones

    public void cambiarBotones(){
        switch (EstadoJuego)
        {//----------------------------------------------------------------------------------------------
            case 1:
                BotonesdePago();
                break;
            //-------------------------------------------------------------------------------------------------------------------//
            case 2:
                BotonesdeJuego();
                break;
            case 3:
                BotonesdeApuesta();
                break;
            case 4:
                BotonesdeRetiro();
                break;
        }
    }
    public void AbrirCodiaut()
    {
        Intent orden=new Intent(tablero.dato,Codigoaut.class);
        tablero.dato.startActivity(orden);
    }
}
