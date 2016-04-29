package com.progresive.user.cashpokerprogresive;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    Jugador[] jugadores=new Jugador[7];
    Dealer dealerJuego;

    public Mesa(int manip)
        {
            //Mesa NuevaMesa=new Mesa();
        for(int i=0;i<jugadores.length;i++) {
            jugadores[i] = new Jugador();
            }
        dealerJuego=new Dealer();
        }

    public void Juego()
    {
        boolean[] jugadoresActivos;
        switch (dealerJuego.verElEstadoDelJuego())
        {
            case 1://Fase de Playoff en esta etapa los jugadores pueden cargar sus apuestas o retirarse, es la etapa justo antes del juegos
                break;
            case 2:// Fase de Inicio del Juego es simple, en esta fase debe decrementarse en 1 las apuestas de los jugadores e iniciar el incremento del progresivo
                jugadoresActivos=dealerJuego.getJugadoresActivos();
                for (int i=0;i<jugadores.length;i++) {
                    if(jugadoresActivos[i]=true) {
                        jugadores[i].apostar();
                    }
                }

                break;
            case 3:// Fase de Pagos ser realizan los pagos y se actualiza la tabla Administrativa
                break;
            default:// por si acaso
                break;
        }
    }

}
