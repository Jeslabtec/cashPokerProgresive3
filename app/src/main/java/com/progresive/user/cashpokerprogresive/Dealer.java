package com.progresive.user.cashpokerprogresive;

import android.widget.TextView;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Dealer {
    //atributos
    public TextView[] PremioApuestaTV;
    public TextView pagarTV;
    public TextView jugarTV;
    public TextView apostarTV;
    public TextView retirarTV;
    // atributos de control del juego
    private int estadoJuego=1; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
<<<<<<< HEAD
    public TextView[] apuestaPremio=new TextView[6];
    public TextView pagar;
    public TextView jugar;
    public TextView apostar;
    public TextView retirar;

    // atributos de paso de informacion
    // atributos Administrativos: no se si crearlos aquí o en el tipo de datos mesa

    public Dealer(TextView[] v)    {
      for (int i=0;i<apuestaPremio.length;i++){
          apuestaPremio[i]=v[i];
          apuestaPremio[i].setOnClickListener(new lTVClickBtnApeustasPremios());
      }
      pagar=v[6];
      pagar.setOnClickListener(new lTVClickControlesJuego());
      jugar=v[7];
      jugar.setOnClickListener(new lTVClickControlesJuego());
      apostar=v[8];
      apostar.setOnClickListener(new lTVClickControlesJuego());
      retirar=v[9];
      retirar.setOnClickListener(new lTVClickControlesJuego());
    }


=======
    // atributos de paso de informacion
    // Métodos get y set de atributos de control
    public Dealer(TextView[] v){
        for (int i=0;i<6;i++){
           PremioApuestaTV[i]=v[i]    ;
        }
        pagarTV=v[7];
        jugarTV=v[8];
        apostarTV=v[9];
        retirarTV=v[10];
    }
>>>>>>> origin/master


    int verElEstadoDelJuego()
    {
        return(estadoJuego);
    }
    void cambiarElEstadoDelJuego(int NuevoEstado)
    {
        estadoJuego=NuevoEstado;
    }
<<<<<<< HEAD
=======



    // Métodos de paso de informacion
>>>>>>> origin/master
}
