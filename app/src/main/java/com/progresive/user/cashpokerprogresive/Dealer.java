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
//Textviews de la vista tablero_______________________
    public TextView pagarTV;
    public TextView jugarTV;
    public TextView apostarTV;
    public TextView retirarTV;
    public TextView[] apuestaPremio = new TextView[6];
    public TextView AvisoTV;
    public TextView ProgresivoTV;
//____________________________________________________________
    private float PorcentajePremio; //Variable que gusrda el porcentaje del premio.
    public double valorficha=1000;  //Valor de la ficha
    private double ValorProgresivoLoco = 0;//Auxiliar para aumentar el progresivo
    public boolean necesariosupervisor=false; //Sirve para preguntar quien es el encargado de habilitar el pago, el supervisor o el deales
    //False:Dealer, True:supervisor.
    private int estadoJuego = 3; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    //private boolean AlgunaApuesta=false; //variable que permite al programa saber que hay por lo menos un jugador en la mesa
    public double AumentoPremio=0; //Cuanto aumento el premio
    public int jugadorSeleccionado=-1; //Es una variable que guarda que jugadors a sido seleccionado
    private boolean Restando=true; //booleano que permit saber si se esta restando o no
    public double Rand1=0;
    public double Rand2=0;
    // atributos de paso de informacion
//______________________________________________________________________________
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
        ValorProgresivoLoco=Double.parseDouble((String) (ProgresivoTV.getText()));
        AumentoPremio=Double.parseDouble((String)ProgresivoTV.getText());
}
//Sirve para saber si se esta restando o sumando. retorna el valor de restando.
    boolean verSiRestando(){
        return Restando;
    }
//Pone a sumar el dispositivo, me asugura que cada vez que se inicie la fase de apostar este configurado en suma
    void ponerSumando(){
        Restando = false;
        apuestaPremio[5].setText("+");
    }
//Cambia a restar o sumar cuando se presiona ese boton
    void cambiarRestando(){
        if(Restando) {
            Restando = false;
            apuestaPremio[5].setText("+");

        }else{
            Restando=true;
            apuestaPremio[5].setText("-");
        }
    }
//Devuelve la cantidad de jugadores en la mesa.
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
                tablero.mesaJuego.jugador[i].cargarSuperApuesta();
                tablero.mesaJuego.jugador[i].apostemos();
            }
        }
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
                        double intermedio;
                        if (estadoJuego==2) {
                            intermedio=AumentoPremio-ValorProgresivoLoco;
                            ValorProgresivoLoco= ((0.0337)*(double)(AumentoPremio)+(0.9663)*(ValorProgresivoLoco))+(Math.random()*(2*intermedio/3)-intermedio/3);
                            //ValorProgresivoLoco= (int) ((0.0337)*(double)(tablero.u)+(0.9663)*(double)(ValorProgresivoLoco)+Math.random()*(6-0));
                            ProgresivoTV.setText(Integer.toString((int) ValorProgresivoLoco));
                            progresivoLoco();
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
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                PorcentajePremio=(float)Premio;
                tablero.mesaJuego.AbrirCodiaut();
                dialog.cancel();
            }
        });

        creaMensajes.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.mesaJuego.dealerJuego.jugadorSeleccionado=-1;
                dialog.cancel();
            }
        });

        return creaMensajes.create();

    }
    //Acciones que permiten confirmar el pago, es valida cuando el codigo ingresado en codigoaut pertenece a un dealer o supervisor
   void AccionesConfirmarPago (){
        double i=Double.parseDouble((String)ProgresivoTV.getText());
        tablero.mesaJuego.jugador[jugadorSeleccionado].cargarapuesta((int) (((double)PorcentajePremio)/100*i));
        tablero.mesaJuego.restringirJugador(jugadorSeleccionado);
        tablero.mesaJuego.jugador[jugadorSeleccionado].cargarSuperApuesta();
        jugadorSeleccionado=-1;
        tablero.mesaJuego.jugadorSeleccionadoColor(-1);
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

