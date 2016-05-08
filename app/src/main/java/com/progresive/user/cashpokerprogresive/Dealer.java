package com.progresive.user.cashpokerprogresive;


import android.widget.TextView;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Dealer {


    public TextView pagarTV;
    public TextView jugarTV;
    public TextView apostarTV;
    public TextView retirarTV;
    private int estadoJuego=1; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    public TextView[] apuestaPremio=new TextView[6];

    // atributos de paso de informacion
    // atributos Administrativos: no se si crearlos aquí o en el tipo de datos mesa

    public Dealer(TextView[] v)    {
      for (int i=0;i<apuestaPremio.length;i++){
          apuestaPremio[i]=v[i];
          apuestaPremio[i].setOnClickListener(new lTVClickBtnApeustasPremios());
      }
      pagarTV=v[6];
      pagarTV.setOnClickListener(new lTVClickControlesJuego());
      jugarTV=v[7];
      jugarTV.setOnClickListener(new lTVClickControlesJuego());
      apostarTV=v[8];
      apostarTV.setOnClickListener(new lTVClickControlesJuego());
      retirarTV=v[9];
      retirarTV.setOnClickListener(new lTVClickControlesJuego());
    }






    int verElEstadoDelJuego()
    {
        return(estadoJuego);
    }
    void cambiarElEstadoDelJuego(int NuevoEstado)
    {
        estadoJuego=NuevoEstado;
    }



    // Métodos de paso de informacion
}
