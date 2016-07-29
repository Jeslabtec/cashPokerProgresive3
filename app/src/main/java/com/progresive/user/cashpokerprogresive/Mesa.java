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
    //Objetos que representa c/u de los jugadores
    Jugador[] jugador=new Jugador[7];
    //Objetos de Los botones de apuestas premios
    ClaseApuestaPremio[] ApuestaPremio=new ClaseApuestaPremio[6];
    //Textview que me dice en que fase esta el juego
    TextView AvisoTV;
    ControlesJuego pagarTV;
    ControlesJuego jugarTV;
    ControlesJuego apostarTV;
    ControlesJuego retirarseTV;
    ClaseDelProgresivo ProgresivoTV;
    MensajesAlerta mensaje;

//variable que dice si se necesita el supervisor o no
    public boolean necesariosupervisor = false;
    private int ApuPreSeleccionado=-1;
    private int EstadoJuego=3;
// constructor de la clase Mesa:  el programa
    public Mesa(TextView[] v)    {
//Creacion de los objetos jugadores que son 7
        for (int i=0; i<jugador.length;i++){
            jugador[i]=new Jugador(v[i]);
        }
        //Creacion de los objetos ApuestaPremio que son 6
        for (int i=0; i<ApuestaPremio.length;i++){
            ApuestaPremio[i]=new ClaseApuestaPremio(v[i+7],i);
        }
       //Creacion de los 4 objetos de control
        pagarTV = new ControlesJuego(v[13]);
        jugarTV = new ControlesJuego(v[14]);
        apostarTV = new ControlesJuego(v[15]);
        retirarseTV = new ControlesJuego(v[16]);
        //Seteo del long click listener de la configuracion
        AvisoTV = v[17];
        AvisoTV.setOnLongClickListener(new longclickconfiguracion());
        //Creacion del objeto progresivo
        ProgresivoTV = new ClaseDelProgresivo(v[18]);

        cambiarBotones();
        //Objeto que contiene los mensajes de alerta
        mensaje=new MensajesAlerta();
    }

   //---------------------------------------------------------------------------------

    //funcion que cambia el textview mientras es undido

    //Funcion que pregunta quienes estan en cero y los bloquea
    public void restringirJugadores(){
        for(int i=0;i<jugador.length;i++){
            if(jugador[i].verapuesta()==0){
                jugador[i].Bloquear();

            }
        }
    }
    public void  restringirJugador(int i){
        jugador[i].Bloquear();
    }
    //Funcion que habilita a los jugadores en la fase de apuesta
    public void habilitarJugadores()
    {
        {
            for (int i = 0; i < jugador.length; i++) {
                jugador[i].Habilitar();
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
    //Funcion que permite saber que jugadro esta seleccionado
    public int JugadorSeleccionado(){
        for (int i=0;i<jugador.length;i++){
            if(jugador[i].EstoySeleccionado()){
                return i;
            }
        }
        return -1;
    }

    public void SeleccionarJugador(int j){
        for (int i=0;i<jugador.length;i++) {
            if(i==j) {
                jugador[i].Seleccionar();
            }else if(jugador[i].jugadortv.isEnabled()) {
                jugador[i].Habilitar();
            }
        }
    }
    public int ApuPreSeleccionado(){
        return ApuPreSeleccionado;
    }
    public void SeleccionarApuPre(int i){
        ApuPreSeleccionado=i;
    }
    //Funcion para iniciar el juego
    public void PonerAJugar() {
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            if (tablero.mesaJuego.jugador[i].verapuesta() > 0) {
                tablero.mesaJuego.jugador[i].cargarSuperApuesta();
                tablero.mesaJuego.jugador[i].apostemos();
            }
        }
        ProgresivoTV.setAumentoPremio();
        progresivoLoco();
    }

    //Timer***********************************************************************************************
    //Funcion que se realiza iterativamente durante toda la fase de juego
    final Handler handler = new Handler();
    Timer t = new Timer();

    public void progresivoLoco() {
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (EstadoJuego == 2) {
                            ProgresivoTV.aumentoAleatorio();
                            progresivoLoco();
                        }
                    }
                });
            }
        }, 90);
    }
//*************************************************************************************************************************
//Funcion que devuelve el estado del juego
    int verElEstadoDelJuego() {
        return (EstadoJuego);
    }
//Funcion que cambia el estado de juego
    void cambiarElEstadoDelJuego(int NuevoEstado) {
        EstadoJuego = NuevoEstado;
    }
//*********************************************************************************************************************
    //Que pasa con los textview cuando se une cualquiera de los controles//
        private void BotonesdeApuesta(){
        ApuestaPremio[5].ponerSumando();

        retirarseTV.Habilitar();
        pagarTV.Bloquear();
        jugarTV.Habilitar();
        apostarTV.Seleccionar();

        for (int i=0;i<ApuestaPremio.length;i++)
        {
            ApuestaPremio[i].BotonesApuesta();
        }
        AvisoTV.setText(R.string.Apostemos);
        habilitarJugadores();
    }
    //----------------------------------------------------------------------------------------//
    private void BotonesdePago(){
        retirarseTV.Bloquear();
        pagarTV.Seleccionar();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i=0; i<ApuestaPremio.length; i++)
        {
           ApuestaPremio[i].BotonesPremio();
        }
        AvisoTV.setText(R.string.Pago);
    }
    //--------------------------------------------------------------------------------------------------//
    private void BotonesdeJuego(){
        retirarseTV.Bloquear();
        pagarTV.Habilitar();
        jugarTV.Seleccionar();
        apostarTV.Habilitar();

        for (int i=0;i<ApuestaPremio.length;i++)
        {
           ApuestaPremio[i].BotonesPremio();
        }

        AvisoTV.setText(R.string.jogo);
        SeleccionarJugador(-1);
        restringirJugadores();
    }
    //---------------------------------------------------------------------------------------------------------------//
    private void BotonesdeRetiro(){
        retirarseTV.Seleccionar();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i=0;i<ApuestaPremio.length;i++)
        {
            ApuestaPremio[i].BotonesDesaparecer();
        }

        AvisoTV.setText(R.string.Retirando);
        SeleccionarJugador(-1);
        restringirJugadores();
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

    //Acciones que permiten confirmar el pago, es valida cuando el codigo ingresado en codigoaut pertenece a un dealer o supervisor
   public int AccionesConfirmarPago() {
        double premio = ProgresivoTV.ValorDelPremio();
        double porcentaje = ApuestaPremio[ApuPreSeleccionado()].ValorNumerico();
        int pago=(int) (porcentaje * premio/CPPLogin.manip.verValorFicha());
        int nuevoProgresivo=Integer.parseInt((String) tablero.mesaJuego.ProgresivoTV.ProgresivoTV.getText())-pago*CPPLogin.manip.verValorFicha();
        CPPLogin.manip.setDineroEnProgresivo((nuevoProgresivo<1000000)?(1000000):(nuevoProgresivo));
        tablero.mesaJuego.ProgresivoTV.ProgresivoTV.setText(Integer.toString(CPPLogin.manip.verDineroProgresivo())); // hacer cambio aqui e ingresar nueva columna llamada valorMinimoProgresivo
        jugador[JugadorSeleccionado()].cargarapuesta(pago);
        jugador[JugadorSeleccionado()].cargarSuperApuesta();
        restringirJugador(JugadorSeleccionado());
        return (pago);
    }

}
