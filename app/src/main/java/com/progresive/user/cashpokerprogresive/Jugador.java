package com.progresive.user.cashpokerprogresive;

/**
 * Created by JuanEsteban on 25/04/2016.
 */
public class Jugador {

    // atributos

    private int Apuesta=0;
    private boolean Enmesa=false;
    private boolean Enjuego=false;

    // metodos
    public void apostar(){
        Apuesta--;
    }

    public void cargarapuesta(int fichas){
        Apuesta+=fichas;
    }

    public boolean verSiPausado()
    {
        return(Enmesa);
    }

    public boolean verSiEnJuego()
    {
        return(Enjuego);
    }

    public void ponerPausado()
    {
        Enmesa=!(Enmesa);
    }

    public void ponerEnJuego()
    {
        Enjuego=!(Enjuego);
    }

    public int verapuesta()
    {
        return Apuesta;
    }
}
