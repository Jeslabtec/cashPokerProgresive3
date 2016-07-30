package com.progresive.user.cashpokerprogresive;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 02/06/2016.
 */

public class ControlesJuego {
    private TextView ControlTV;
    private boolean Seleccionado=false;

    private int quienSoy;

    public ControlesJuego(TextView v,int i) {
        ControlTV = v;
        ControlTV.setOnClickListener(new lTVClickControlesJuego());
        quienSoy=i;
    }

    public void Bloquear() {
        switch (quienSoy) {
            case 1:
                ControlTV.setBackgroundResource(R.drawable.pagargris);
                break;
            case 2:
                ControlTV.setBackgroundResource(R.drawable.jugargris);
                break;
            case 3:
                ControlTV.setBackgroundResource(R.drawable.apostargris);
                break;
            case 4:
                ControlTV.setBackgroundResource(R.drawable.retirarsegris);
                break;
        }
        ControlTV.setEnabled(false);
        Seleccionado=false;
    }
    public void Habilitar(){
    switch (quienSoy) {
        case 1:
            ControlTV.setBackgroundResource(R.drawable.pagar);
            break;
        case 2:
            ControlTV.setBackgroundResource(R.drawable.jugar);
            break;
        case 3:
            ControlTV.setBackgroundResource(R.drawable.apostar);
            break;
        case 4:
            ControlTV.setBackgroundResource(R.drawable.retirarse);
            break;
    }
        ControlTV.setEnabled(true);
        Seleccionado=false;
    }

    public void Seleccionar(){

       /* ObjectAnimator Agrandador= ObjectAnimator.ofFloat(ControlTV, View.SCALE_X,1.5f);
        ObjectAnimator Agrandador2=ObjectAnimator.ofFloat(ControlTV, View.SCALE_Y,1.5f);
        AnimatorSet conjunto=new AnimatorSet();
        conjunto.playSequentially(Agrandador,Agrandador2);*/
        switch (quienSoy) {
            case 1:
                ControlTV.setBackgroundResource(R.drawable.pagar);
                break;
            case 2:
                ControlTV.setBackgroundResource(R.drawable.jugar);
                break;
            case 3:
                ControlTV.setBackgroundResource(R.drawable.apostar);
                break;
            case 4:
                ControlTV.setBackgroundResource(R.drawable.retirarse);
                break;
        }
        ControlTV.setEnabled(false);
        Seleccionado=true;
    }
}