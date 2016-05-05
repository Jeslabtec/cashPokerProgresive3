package com.progresive.user.cashpokerprogresive;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class tablero extends AppCompatActivity {

    static Mesa mesaJuego=new Mesa();
    static TextView[] jugadortv=new TextView[7];
    static TextView[] pA=new TextView[6];
    static TextView jugar;
    static TextView apostar;
    static TextView retirar;
    static TextView pagar;
    static AlertDialog alert1;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Putamadre");
        builder1.setCancelable(true);

        builder1.setPositiveButton("hola", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
        builder1.setNegativeButton( "Jodete puta, no me saludes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alert1 = builder1.create();










        // definiciones de las variables del tablero
        //1. Vari ables de lostv de los jugadores
        jugadortv[0]= (TextView) findViewById(R.id.tvJugador1);
        jugadortv[1]= (TextView) findViewById(R.id.tvJugador2);
        jugadortv[2]= (TextView) findViewById(R.id.tvJugador3);
        jugadortv[3]= (TextView) findViewById(R.id.tvJugador4);
        jugadortv[4]= (TextView) findViewById(R.id.tvJugador5);
        jugadortv[5]= (TextView) findViewById(R.id.tvJugador6);
        jugadortv[6]= (TextView) findViewById(R.id.tvJugador7);

        //2. variables del premio y tipo de apuesta


        pA[0]= (TextView) findViewById(R.id.tvPremioApuesta1);
        pA[1]= (TextView) findViewById(R.id.tvPremioApuesta2);
        pA[2]= (TextView) findViewById(R.id.tvPremioApuesta3);
        pA[3]= (TextView) findViewById(R.id.tvPremioApuesta4);
        pA[4]= (TextView) findViewById(R.id.tvPremioApuesta5);
        pA[5]= (TextView) findViewById(R.id.tvPremioApuesta6);

        //3. variables de los botones de control
        /*nota: el retiro momentaneo se dara haciendo click sostenido sobre el jugador*/
        jugar= (TextView) findViewById(R.id.tvJugar);
        apostar= (TextView) findViewById(R.id.tvApostar);
        retirar= (TextView) findViewById(R.id.tvRetiroTotal);
        pagar= (TextView) findViewById(R.id.tvPagar);


       // https://www.iconfinder.com/        Descargar los iconos
        pagar.setOnClickListener(new lTVClickControlesJuego());
        jugar.setOnClickListener(new lTVClickControlesJuego());
        retirar.setOnClickListener(new lTVClickControlesJuego());
        apostar.setOnClickListener(new lTVClickControlesJuego());



        // enlace de cada uno de los botones de la interfaz grafica con su respectivo litener

        for (int i=0;i<pA.length;i++) {
            pA[i].setOnClickListener(new lTVClickBtnApeustasPremios());
        }
        for (int i=0;i<jugadortv.length;i++) {
            jugadortv[i].setOnClickListener(new lTVClickJugadores());
        }






    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
