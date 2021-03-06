package com.progresive.user.cashpokerprogresive;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class tablero extends AppCompatActivity {

    static AppCompatActivity dato;
    static Mesa mesaJuego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        dato=this;
        TextView[] datos = {(TextView) findViewById(R.id.tvJugador1),
                (TextView) findViewById(R.id.tvJugador2),
                (TextView) findViewById(R.id.tvJugador3),
                (TextView) findViewById(R.id.tvJugador4),
                (TextView) findViewById(R.id.tvJugador5),
                (TextView) findViewById(R.id.tvJugador6),
                (TextView) findViewById(R.id.tvJugador7),
                (TextView) findViewById(R.id.tvPremioApuesta1),
                (TextView) findViewById(R.id.tvPremioApuesta2),
                (TextView) findViewById(R.id.tvPremioApuesta3),
                (TextView) findViewById(R.id.tvPremioApuesta4),
                (TextView) findViewById(R.id.tvPremioApuesta5),
                (TextView) findViewById(R.id.tvPremioApuesta6),
                (TextView) findViewById(R.id.tvPagar),
                (TextView) findViewById(R.id.tvJugar),
                (TextView) findViewById(R.id.tvApostar),
                (TextView) findViewById(R.id.tvRetiroTotal),
                (TextView) findViewById(R.id.TextviewAviso),
                (TextView) findViewById(R.id.TVprogresivo)};

        mesaJuego = new Mesa(datos);
        // sección de Alert Dialgos, en esta sección se colocará el valor de cada Alert dialog para la confirmacion de  el pago de premios
       //-----------------------------------------------------------------------------------------------------//
        //Mensaje de confirmacion de retiro//


        // https://www.iconfinder.com/        Descargar los iconos
    }

    @Override
    protected void onPause(){
        try {
            CPPLogin.manip.GuardarTabla(CPPLogin.manip.idTablet,Integer.parseInt((String) this.mesaJuego.ProgresivoTV.ProgresivoTV.getText()));
            super.onPause();
        } catch (ExecutionException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy(){
        try {
            CPPLogin.manip.GuardarTabla(CPPLogin.manip.idTablet,Integer.parseInt((String) this.mesaJuego.ProgresivoTV.ProgresivoTV.getText()));
            super.onDestroy();
        } catch (ExecutionException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(tablero.dato,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


}
