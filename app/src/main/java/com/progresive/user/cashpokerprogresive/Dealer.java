package com.progresive.user.cashpokerprogresive;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Dealer {

    public TextView pagarTV;
    public TextView jugarTV;
    public TextView apostarTV;
    public TextView retirarTV;
    public TextView[] apuestaPremio = new TextView[6];
    public TextView AvisoTV;
    public TextView ProgresivoTV;

    private int ValorProgresivoLoco = 0;
    private int estadoJuego = 3; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    //private boolean AlgunaApuesta=false; //variable que permite al programa saber que hay por lo menos un jugador en la mesa

    public int jugadorSeleccionado=-1; //Es una variable que guarda que jugadors a sido seleccionado
    private boolean Restando=true;
    // atributos de paso de informacion
    // atributos Administrativos: no se si crearlos aquí o en el tipo de datos mesa

    public Dealer(TextView[] v) {
        for (int i = 0; i < apuestaPremio.length; i++) {
            apuestaPremio[i] = v[i];
            apuestaPremio[i].setOnClickListener(new lTVClickBtnApeustasPremios());
        }
        pagarTV = v[6];
        pagarTV.setOnClickListener(new lTVClickControlesJuego());
        jugarTV = v[7];
        jugarTV.setOnClickListener(new lTVClickControlesJuego());
        apostarTV = v[8];
        apostarTV.setOnClickListener(new lTVClickControlesJuego());
        retirarTV = v[9];
        retirarTV.setOnClickListener(new lTVClickControlesJuego());

        AvisoTV = v[10];
        ProgresivoTV=v[11];
    }
    boolean verSiRestando(){
        return Restando;
    }
    void ponerSumando(){
        Restando = false;
        apuestaPremio[5].setText("+");
    }
    void cambiarRestando(){
        if(Restando) {
            Restando = false;
            apuestaPremio[5].setText("+");

        }else{
            Restando=true;
            apuestaPremio[5].setText("-");
        }
    }
    public int cuantosJugando(){
        int jugadores=0;
        for(int i = 0; i < tablero.mesaJuego.jugador.length; i++){
            if(tablero.mesaJuego.jugador[i].verapuesta() > 0){
                jugadores++;
            }
        }
        return jugadores;
    }


    public void PonerAJugar() {
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            if (tablero.mesaJuego.jugador[i].verapuesta() > 0) {
                tablero.mesaJuego.jugador[i].cargarapuesta(-1);
                tablero.mesaJuego.jugador[i].cargarSuperApuesta();

            }
        }               // SLEEP 2 SECONDS HERE ...
        progresivoLoco();
        }



    int verElEstadoDelJuego() {
        return (estadoJuego);
    }
    void cambiarElEstadoDelJuego(int NuevoEstado) {
        estadoJuego = NuevoEstado;
    }

    //Timer***********************************************************************************************
    final Handler handler = new Handler();
    Timer t = new Timer();

    public void progresivoLoco() {
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (estadoJuego==2) {
                            progresivoLoco();
                            ValorProgresivoLoco++;
                            ProgresivoTV.setText(Integer.toString(ValorProgresivoLoco));
                        }
                    }
                });
            }
        }, 100);
    }
//***********************************************************************************************************

    // mensajes a ser mostrados para las confirmaciones en los juegos hay que cambiar parametros para ser lo mas universales posibles
//-----------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarPago(final int Premio) {

        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Confirma el pago de este premio?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                double i=Double.parseDouble((String)ProgresivoTV.getText());
                tablero.mesaJuego.jugador[jugadorSeleccionado].cargarapuesta((int) (((double)Premio)/100*i));
                tablero.mesaJuego.restringirJugador(jugadorSeleccionado);
                jugadorSeleccionado=-1;
                tablero.mesaJuego.jugadorSeleccionadoColor(-1);
                dialog.cancel();
            }
        });

        creaMensajes.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.mesaJuego.dealerJuego.jugadorSeleccionado=-1;
                dialog.cancel();
            }
        });

        return creaMensajes.create();

    }

    //------------------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarRetiro() {


        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("¿Seguro qué desea retirarse?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                msgConfirmarDinero().show();
                dialog.cancel();
            }
        });
        creaMensajes.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return creaMensajes.create();
    }

    //-----------------------------------------------------------------------------------------------------------//

    AlertDialog msgErrorApuesta() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Antes debe Seleccionar un Jugador.");
        creaMensajes.setCancelable(true);
        creaMensajes.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

       return creaMensajes.create();

    }

    //-----------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarDinero() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Pagar al Jugador " + tablero.mesaJuego.jugador[jugadorSeleccionado].jugadortv.getText() + " fichas");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tablero.mesaJuego.jugador[jugadorSeleccionado].reiniciarApuesta();
                tablero.mesaJuego.restringirJugador(jugadorSeleccionado);
                tablero.mesaJuego.jugador[jugadorSeleccionado].reiniciarSuperApuesta();
                jugadorSeleccionado=-1;
                if(!tablero.mesaJuego.hayAlguienJugando()){
                    tablero.mesaJuego.dealerJuego.cambiarElEstadoDelJuego(3);
                    tablero.mesaJuego.cambiarBotones();
                }
                dialog.cancel();
            }
        });

       return creaMensajes.create();
    }
    //--------------------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgPedirALgunaApuesta() {
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Para continuar necesita mínimo una apuesta.");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return creaMensajes.create();
    }
        //-----------------------------------------------------------------------------------------------------------//

        // Métodos de paso de informacion

}


        // Métodos de paso de informacion

