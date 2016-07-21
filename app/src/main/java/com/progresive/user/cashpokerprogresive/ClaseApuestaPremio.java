package com.progresive.user.cashpokerprogresive;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.BounceInterpolator;
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

    private Double[] VectorValSuma = {1.0,2.0,5.0,10.0,20.0,0.0} ;
    private TextView ApuestaPremioTV;
    //String que guarda el porcentaje
    private String Porcentaje;
    //String que guarda el valor de la suma
    private Double valoresSuma;
    //Solo se usa en el apuesta premio del mas.
    private boolean Restando=false;
    //Permite saber al objeto cual boton es
    private int QuienSoy;
    public  ClaseApuestaPremio(TextView v,int i){
        //i es un valor que indica cual es el numero del boton
        //configuracion del textview
        ApuestaPremioTV=v;
        ApuestaPremioTV.setOnClickListener(new lTVClickBtnApeustasPremios());
        QuienSoy=i;
        Porcentaje=Double.toString(CPPLogin.manip.verPorcentajePremio(i));
        valoresSuma=VectorValSuma[i];
    }
    //Pone los botones en forma de premio
    public void BotonesPremio(){
        ApuestaPremioTV.setText(Porcentaje);
        ApuestaPremioTV.setVisibility(View.VISIBLE);
        //ApuestaPremioTV.setBackgroundResource(R.drawable.descarga);
    }
    //pone a los botones en forma de apuesta
    public void BotonesApuesta(){
        ApuestaPremioTV.setVisibility(View.VISIBLE);
        switch (QuienSoy)
        {case 0:
            ApuestaPremioTV.setBackgroundResource(R.drawable.uno);
            break;
        case 1:
            ApuestaPremioTV.setBackgroundResource(R.drawable.dos);
            break;
        case 2:
            ApuestaPremioTV.setBackgroundResource(R.drawable.cinco);
            break;
        case 3:
            ApuestaPremioTV.setBackgroundResource(R.drawable.diez);
            break;
        case 4:
            ApuestaPremioTV.setBackgroundResource(R.drawable.veinte);
            break;
        case 5:
            ApuestaPremioTV.setBackgroundResource(R.drawable.mas);
            break;
        }
    }
    public void Movimiento(int ini,int fin){
        ObjectAnimator animacion= ObjectAnimator.ofFloat(ApuestaPremioTV,View.TRANSLATION_Y,ini,fin);
        animacion.setDuration(1000);
        animacion.setInterpolator(new BounceInterpolator());
        animacion.start();
    }
    //desaparece los botones
    public void BotonesDesaparecer(){
        ApuestaPremioTV.setVisibility(View.INVISIBLE);
    }
    //me devuelve el valor numerico del icono

    public double ValorNumerico(){
        if(tablero.mesaJuego.verElEstadoDelJuego()==3){
            return valoresSuma;
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
        ApuestaPremioTV.setBackgroundResource(R.drawable.mas);
    }

    //Cambia a restar o sumar cuando se presiona ese boton
    void cambiarRestando() {
        if (Restando) {
            Restando = false;
            ApuestaPremioTV.setBackgroundResource(R.drawable.mas);

        } else {
            Restando = true;
            ApuestaPremioTV.setBackgroundResource(R.drawable.menos);
        }
    }

}
