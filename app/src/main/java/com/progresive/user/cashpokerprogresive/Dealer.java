package com.progresive.user.cashpokerprogresive;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Dealer {
    //atributos

    // atributos de control del juego
    private int estadoJuego=1; // variable de contol que dira si el juego a iniciado=2, si esta en fase de pago=1 o si esta en la fase de apuestas=3, o en fase de retiros
    private boolean[] jugadoresActivos={false,false,false,false,false,false,false};

    // atributos de paso de informacion
    private int aputestaAAgregar=0;

    // atributos Administrativos: no se si crearlos aquí o en el tipo de datos mesa




    // métodos

    // Métodos get y set de atributos de control
    int verElEstadoDelJuego()
    {
        return(estadoJuego);
    }
    boolean[] getJugadoresActivos()
    {
        return(jugadoresActivos);
    }
    void cambiarElEstadoDelJuego(int NuevoEstado)
    {
        estadoJuego=NuevoEstado;
    }
    void toogleJugadorActivo(int numeroJugador)
    {
        jugadoresActivos[numeroJugador]=!jugadoresActivos[numeroJugador];
    }

    // Métodos de paso de informacion
    int ponerFicha()
    {
        return(aputestaAAgregar);
    }
    void tomarFicha(int valor)
    {
        aputestaAAgregar=valor;
    }


}
