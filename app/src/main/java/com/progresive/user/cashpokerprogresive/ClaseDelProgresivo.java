package com.progresive.user.cashpokerprogresive;

import android.widget.TextView;

/**
 * Created by user on 02/06/2016.
 * Clase que controla todo lo relacionado con el valor del progresivo
 */

public class ClaseDelProgresivo {
    public TextView ProgresivoTV;

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
        AumentoPremio=(double) CPPLogin.manip.verDineroProgresivo();
        Dinero=AumentoPremio;
        ProgresivoTV.setText(Integer.toString((CPPLogin.manip.verDineroProgresivo())));

    public void cambiePremio(int nuevoValor)
    {
        AumentoPremio=nuevoValor;
    }

    public void setAumentoPremio(){
        //AumentoPremio+=tablero.mesaJuego.cuantosJugando()*ValorFicha*CPPLogin.manip.verAumentoPremio();
        AumentoPremio+=tablero.mesaJuego.cuantosJugando()*ValorFicha*(Rand1+Rand2)*CPPLogin.manip.verAumentoPremio();
        //CPPLogin.manip.verBoteBonus();
        Rand2=1-Rand1;
        Rand1=Math.random();
    }
    public void aumentoAleatorio(){
        double Intermedio;
        Intermedio = AumentoPremio - Dinero;
        Dinero =(((0.0337) *  (AumentoPremio) + (0.9663) * (Dinero)) + (Math.random() *
        (2 * Intermedio / 3) - Intermedio / 3));

        ProgresivoTV.setText(Integer.toString((int) Dinero));
    }
    public double ValorDelPremio(){
       return Double.parseDouble((String)ProgresivoTV.getText());
    }
}
