package com.progresive.user.cashpokerprogresive;

import android.content.Intent;
import android.view.View;

/**
 * Created by USER on 05/06/2016.
 */
public class longclickconfiguracion  implements View.OnLongClickListener{

    @Override
    public boolean onLongClick(View v) {
        Intent orden=new Intent(tablero.dato,Configuracion.class);
        tablero.dato.startActivity(orden);
        return false;
    }
}
