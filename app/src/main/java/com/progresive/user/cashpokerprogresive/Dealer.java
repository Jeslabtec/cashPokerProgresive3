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

    public TextView[] apuestaPremio = new TextView[6];

    public TextView AvisoTV;

    //____________________________________________________________
    private float PorcentajePremio; //Variable que gusrda el porcentaje del premio.
    public double valorficha = 1000;  //Valor de la ficha
    private double ValorProgresivoLoco = 0;//Auxiliar para aumentar el progresivo
    public boolean necesariosupervisor = false; //Sirve para preguntar quien es el encargado de habilitar el pago, el supervisor o el deales
    //False:Dealer, True:supervisor.
    private int estadoJuego = 3; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    //private boolean AlgunaApuesta=false; //variable que permite al programa saber que hay por lo menos un jugador en la mesa

    public int jugadorSeleccionado = -1; //Es una variable que guarda que jugadors a sido seleccionado
    private boolean Restando = true; //booleano que permit saber si se esta restando o no


    // atributos de paso de informacion
//______________________________________________________________________________
    public Dealer(TextView[] v) {
        for (int i = 0; i < apuestaPremio.length; i++) {
            apuestaPremio[i] = v[i];
            apuestaPremio[i].setOnClickListener(new lTVClickBtnApeustasPremios());
        }
        AvisoTV = v[10];

    }

    //Sirve para saber si se esta restando o sumando. retorna el valor de restando.
    boolean verSiRestando() {
        return Restando;
    }

    //Pone a sumar el dispositivo, me asugura que cada vez que se inicie la fase de apostar este configurado en suma
    void ponerSumando() {
        Restando = false;
        apuestaPremio[5].setText("+");
    }

    //Cambia a restar o sumar cuando se presiona ese boton
    void cambiarRestando() {
        if (Restando) {
            Restando = false;
            apuestaPremio[5].setText("+");

        } else {
            Restando = true;
            apuestaPremio[5].setText("-");
        }
    }

    //Devuelve la cantidad de jugadores en la mesa.










//***********************************************************************************************************

    // mensajes a ser mostrados para las confirmaciones en los juegos hay que cambiar parametros para ser lo mas universales posibles
//-----------------------------------------------------------------------------------------------------------------------//

}