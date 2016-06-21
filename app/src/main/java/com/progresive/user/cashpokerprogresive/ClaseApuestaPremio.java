package com.progresive.user.cashpokerprogresive;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Santigo Lopez on 02/06/2016.
 * Clase que crea los objetos apuesta premio.
 * Estos objetos tienen la respnsabilidad de ademas de poner el valor de la apuesta
 * en los jugadores, guarda cada uno de los 6 premios disponibles en el juego.
 * Esta clase permite cambiar las características físicas de dchos botones y guardar los valores
 * del porcentaje y los valores de apuesta
 */
public class ClaseApuestaPremio {

    private TextView ApuestaPremioTV;
    //String que guarda el porcentaje
    private String Porcentaje;
    //String que guarda el valor de la suma
    private String valoresSuma;
    //Solo se usa en el apuesta premio del mas.
    private boolean Restando=false;

    public  ClaseApuestaPremio(TextView v,int i){
        //i es un valor que indica cual es el numero del boton
        //configuracion del textview
        ApuestaPremioTV=v;
        ApuestaPremioTV.setOnClickListener(new lTVClickBtnApeustasPremios());

        Porcentaje=CPPLogin.manip.verPorcentajePremio(i);
        valoresSuma=ApuestaPremioTV.getText().toString();
    }
    //Pone los botones en forma de premio
    public void BotonesPremio(){
        ApuestaPremioTV.setText(Porcentaje);
        ApuestaPremioTV.setVisibility(View.VISIBLE);
        ApuestaPremioTV.setBackgroundResource(R.drawable.descarga);
    }
    //pone a los botones en forma de apuesta
    public void BotonesApuesta(){
        ApuestaPremioTV.setText(valoresSuma);
        ApuestaPremioTV.setVisibility(View.VISIBLE);
        ApuestaPremioTV.setBackgroundResource(R.drawable.coins);
    }
    //desaparece los botones
    public void BotonesDesaparecer(){
        ApuestaPremioTV.setVisibility(View.INVISIBLE);
    }
    //me devuelve el valor numerico del icono

    public double ValorNumerico(){
        if(tablero.mesaJuego.verElEstadoDelJuego()==3){
            return Double.parseDouble(valoresSuma);
        }else if(tablero.mesaJuego.verElEstadoDelJuego()==1){
            return  Double.parseDouble(Porcentaje);
        }
        return 0;
    }
    boolean verSiRestando() {
        return Restando;
    }

    //Pone a sumar el dispositivo, me asugura que cada vez que se inicie la fase de apostar este configurado en suma
    void ponerSumando() {
        Restando = false;
        ApuestaPremioTV.setText("+");
    }

    //Cambia a restar o sumar cuando se presiona ese boton
    void cambiarRestando() {
        if (Restando) {
            Restando = false;
            ApuestaPremioTV.setText("+");

        } else {
            Restando = true;
            ApuestaPremioTV.setText("-");
        }
    }

}
