package com.progresive.user.cashpokerprogresive;

import android.widget.TextView;

/**
 * Created by user on 02/06/2016.
 * Clase que controla todo lo relacionado con el valor del progresivo
 */

public class ClaseDelProgresivo {
    private TextView ProgresivoTV;

    private int ValorFicha;
    private double Rand1;
    private double Rand2;
    private double AumentoPremio;
    private double Dinero;

    public ClaseDelProgresivo(TextView v) {
        ProgresivoTV = v;
        ValorFicha=CPPLogin.manip.verValorFicha();
        Rand1=Math.random();
        Rand2=0;
        AumentoPremio=CPPLogin.manip.verDineroProgresivo();
        Dinero=CPPLogin.manip.verDineroProgresivo();
        ProgresivoTV.setText(Double.toString(Dinero));
    }
    public void setAumentoPremio(){
        AumentoPremio+=tablero.mesaJuego.cuantosJugando()*ValorFicha*(Rand1+Rand2);
        Rand2=1-Rand1;
        Rand1=Math.random();
    }
    public void aumentoAleatorio(){
        double Intermedio;
        Intermedio = AumentoPremio - Dinero;
        Dinero = ((0.0337) * (double) (AumentoPremio) + (0.9663) * (Dinero)) + (Math.random() * (2 * Intermedio / 3) - Intermedio / 3);
        ProgresivoTV.setText(Integer.toString((int) Dinero));
    }
}
