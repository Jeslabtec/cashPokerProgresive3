package com.progresive.user.cashpokerprogresive;


import android.widget.TextView;

/**
 * Created by user on 02/06/2016.
 */

public class ControlesJuego {
    private TextView ControlTV;
    private boolean Seleccionado=false;

    public ControlesJuego(TextView v) {
        ControlTV = v;
        ControlTV.setOnClickListener(new lTVClickControlesJuego());
    }

    public void Bloquear() {
        ControlTV.setBackgroundColor(0xffE0E0E0);
        ControlTV.setTextColor(0xff000000);
        ControlTV.setEnabled(false);
        Seleccionado=false;
    }
    public void Habilitar(){
        ControlTV.setBackgroundColor(0x00000000);
        ControlTV.setTextColor(0xff000000);
        ControlTV.setEnabled(true);
        Seleccionado=false;
    }
    public void Seleccionar(){
        ControlTV.setBackgroundColor(0xff000000);
        ControlTV.setTextColor(0xffffffff);
        ControlTV.setEnabled(false);
        Seleccionado=true;
    }
}