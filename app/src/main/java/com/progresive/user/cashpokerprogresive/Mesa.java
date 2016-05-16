package com.progresive.user.cashpokerprogresive;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    Jugador[] jugador=new Jugador[7];
    Dealer dealerJuego;


// constructor de la clase Mesa:  el programa
    public Mesa(TextView[] v)    {

        TextView[] atributosDealer={v[7],v[8],v[9],v[10],v[11],v[12],v[13],v[14],v[15],v[16],v[17],v[18]};

        for (int i=0; i<jugador.length;i++){
            jugador[i]=new Jugador(v[i]);
        }
        dealerJuego=new Dealer(atributosDealer);
        cambiarBotones();
    }
   //---------------------------------------------------------------------------------
//funcion que cambia el textview mientras es undido
    public void jugadorSeleccionadoColor(int i){
        for (int j=0;j<jugador.length;j++) {
            if(i==j) {
                jugador[j].jugadortv.setTextColor(0xffffffff);
            }else{
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
    //Funcion que habilita a los jugadores en la fase de apuesta
    public void habilitarJugadores()
    {
        {
            for (int i = 0; i < jugador.length; i++) {
                jugador[i].jugadortv.setTextColor(0xFF000000);
                jugador[i].jugadortv.setEnabled(true);
            }
        }
    }
    //Funcion que pregunta si hay alguien jugando si lo hay responde con true
    public boolean hayAlguienJugando(){
        for(int i = 0; i < jugador.length; i++){
           if (jugador[i].verapuesta()!=0) {
             return true;
           }
        }
        return false;
    }

    //Que pasa con los textview cuando se unde cualquiera de los controles//
    private void BotonesdeApuesta(){
        dealerJuego.apuestaPremio[0].setText(R.string.Apuesta1);
        dealerJuego.apuestaPremio[1].setText(R.string.Apuesta2);
        dealerJuego.apuestaPremio[2].setText(R.string.Apuesta3);
        dealerJuego.apuestaPremio[3].setText(R.string.Apuesta4);
        dealerJuego.apuestaPremio[4].setText(R.string.Apuesta5);
        dealerJuego.apuestaPremio[5].setText(R.string.Apuesta6);

        dealerJuego.retirarTV.setBackgroundColor(0x00000000);
        dealerJuego.retirarTV.setTextColor(0xff000000);
        dealerJuego.retirarTV.setEnabled(true);

        dealerJuego.pagarTV.setBackgroundColor(0xffE0E0E0);
        dealerJuego.pagarTV.setTextColor(0xff000000);
        dealerJuego.pagarTV.setEnabled(false);

        dealerJuego.jugarTV.setBackgroundColor(0x00000000);
        dealerJuego.jugarTV.setTextColor(0xff000000);
        dealerJuego.jugarTV.setEnabled(true);

        dealerJuego.apostarTV.setBackgroundColor(0xff000000);
        dealerJuego.apostarTV.setTextColor(0xffffffff);
        dealerJuego.apostarTV.setEnabled(false);

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

        dealerJuego.apostarTV.setBackgroundColor(0x00000000);
        dealerJuego.apostarTV.setTextColor(0xff000000);
        dealerJuego.apostarTV.setEnabled(true);

        dealerJuego.retirarTV.setBackgroundColor(0x00000000);
        dealerJuego.retirarTV.setTextColor(0xff000000);
        dealerJuego.retirarTV.setEnabled(true);

        dealerJuego.jugarTV.setBackgroundColor(0xffe0e0e0);
        dealerJuego.jugarTV.setTextColor(0xff000000);
        dealerJuego.jugarTV.setEnabled(false);

        dealerJuego.pagarTV.setBackgroundColor(0xff000000);
        dealerJuego.pagarTV.setTextColor(0xffffffff);
        dealerJuego.pagarTV.setEnabled(false);

        for (int i=0; i<dealerJuego.apuestaPremio.length; i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.VISIBLE);
            dealerJuego.apuestaPremio[i].setBackgroundResource(R.drawable.descarga);
        }
        dealerJuego.AvisoTV.setText(R.string.Pago);
        jugadorSeleccionadoColor(-1);
        restringirJugadores();
    }
    //--------------------------------------------------------------------------------------------------//
    private void BotonesdeJuego(){
        dealerJuego.apuestaPremio[0].setText("1");
        dealerJuego.apuestaPremio[1].setText("3");
        dealerJuego.apuestaPremio[2].setText("v9");
        dealerJuego.apuestaPremio[3].setText("27");
        dealerJuego.apuestaPremio[4].setText("81");
        dealerJuego.apuestaPremio[5].setText("100");

        dealerJuego.apostarTV.setBackgroundColor(0x00000000);
        dealerJuego.apostarTV.setTextColor(0xff000000);
        dealerJuego.apostarTV.setEnabled(true);

        dealerJuego.retirarTV.setBackgroundColor(0x00000000);
        dealerJuego.retirarTV.setTextColor(0xff000000);
        dealerJuego.retirarTV.setEnabled(true);

        dealerJuego.pagarTV.setBackgroundColor(0x00000000);
        dealerJuego.pagarTV.setTextColor(0xff000000);
        dealerJuego.pagarTV.setEnabled(true);

        dealerJuego.jugarTV.setBackgroundColor(0xff000000);
        dealerJuego.jugarTV.setTextColor(0xffffffff);
        dealerJuego.jugarTV.setEnabled(false);

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

        dealerJuego.apostarTV.setBackgroundColor(0x00000000);
        dealerJuego.apostarTV.setTextColor(0xff000000);
        dealerJuego.apostarTV.setEnabled(true);

        dealerJuego.pagarTV.setBackgroundColor(0xffe0e0e0);
        dealerJuego.pagarTV.setTextColor(0xff000000);
        dealerJuego.pagarTV.setEnabled(false);

        dealerJuego.jugarTV.setBackgroundColor(0xffe0e0e0);
        dealerJuego.jugarTV.setTextColor(0xff000000);
        dealerJuego.jugarTV.setEnabled(false);

        dealerJuego.retirarTV.setBackgroundColor(0xff000000);
        dealerJuego.retirarTV.setTextColor(0xffffffff);
        dealerJuego.retirarTV.setEnabled(true);

        for (int i=0;i<dealerJuego.apuestaPremio.length;i++)
        {
            dealerJuego.apuestaPremio[i].setVisibility(View.INVISIBLE);
        }

        dealerJuego.AvisoTV.setText(R.string.Retirando);
        jugadorSeleccionadoColor(-1);
        restringirJugadores();
    }
    //-------------------------------------------------------------------------------------------------------------------//

        // metodos
    //dependiendo del estado del juego se habilitaran o desabilitaran algunos botones

    public void cambiarBotones(){
        switch (dealerJuego.verElEstadoDelJuego())
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

    public void retardo(int miliSegundos)
    {
        try{
           Thread.sleep(miliSegundos);
           }
        catch(InterruptedException ex) {

            }

    }

}
