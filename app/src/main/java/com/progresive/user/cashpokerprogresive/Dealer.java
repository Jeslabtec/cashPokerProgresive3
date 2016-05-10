package com.progresive.user.cashpokerprogresive;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Dealer {


    public TextView pagarTV;
    public TextView jugarTV;
    public TextView apostarTV;
    public TextView retirarTV;
    public TextView confirmarTV;
    public TextView[] apuestaPremio=new TextView[6];





    private boolean confirmacion=false;
    private int estadoJuego=3; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    private int cantidadASubir=0;






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
      confirmarTV=v[10];
      confirmarTV.setOnClickListener(new lTVClickControlesJuego());
    }






    int verElEstadoDelJuego()
    {
        return(estadoJuego);
    }
    void cambiarElEstadoDelJuego(int NuevoEstado)
    {
        estadoJuego=NuevoEstado;
    }
    int verCantidadASubir()
    {
        return(cantidadASubir);
    }
    void cambiarCantidadASubir(int FichaASumar)
    {
        cantidadASubir+=FichaASumar;
    }
    void reiniciarCantidadASubir(){cantidadASubir=0;}
    boolean verSiConfirmacion()
    {
        return(confirmacion);
    }
    void cambiarConfirmacion()    {
        confirmacion=!(confirmacion);
    }




    // mensajes a ser mostrados para las confirmaciones en los juegos hay que cambiar parametros para ser lo mas universales posibles

    AlertDialog msgConfirmarPago(final int elecciones){
        AlertDialog msgConfPago;
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Confirma el pago de este premio?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.mesaJuego.jugador[elecciones].cargarapuesta(Integer.parseInt((String)tablero.mesaJuego.dealerJuego.apuestaPremio[5].getText()));
                tablero.mesaJuego.jugador[elecciones].jugadortv.setText(Integer.toString(tablero.mesaJuego.jugador[elecciones].verapuesta()));
                dialog.cancel();
            }
        });
        creaMensajes.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        msgConfPago = creaMensajes.create();
        return(msgConfPago);
    }
//------------------------------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarRetiro(final int eleccion){
        AlertDialog msgConfRetiro;
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("¿Seguro qué desea retirarse?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            tablero.mesaJuego.dealerJuego.msgConfirmarDinero(eleccion).show();
                dialog.cancel();
                //Mensaje con
            }
        });
        creaMensajes.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        msgConfRetiro = creaMensajes.create();
        return msgConfRetiro;
    }
    //-----------------------------------------------------------------------------------------------------------//
    AlertDialog msgConfirmarDinero(final int eleccion){
        AlertDialog msgConfDinero;
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(tablero.dato);
        creaMensajes.setMessage("Pagar al Jugador "+tablero.mesaJuego.jugador[eleccion].jugadortv.getText()+" fichas");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tablero.mesaJuego.jugador[eleccion].reiniciarApuesta();
                tablero.mesaJuego.jugador[eleccion].jugadortv.setText(R.string.cero);
                dialog.cancel();
            }
        });
        msgConfDinero = creaMensajes.create();
        return msgConfDinero;
    }
//---------------------------------------------------------------------------------------------------------//

}


    // Métodos de paso de informacion

