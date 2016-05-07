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

<<<<<<< HEAD
    static Mesa mesaJuego;
    static AlertDialog msgConfPago;
=======
    static Mesa mesaJuego=new Mesa();
    static AlertDialog alert1;
    static AlertDialog msgConfRetiro;
>>>>>>> origin/master
    static int eleccion=-1;
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

        //--------------------------------------------------------------------------------------------//
        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(this);
        creaMensajes.setMessage("Putamadre");
        creaMensajes.setCancelable(true);

<<<<<<< HEAD

        // sección de Alert Dialgos, en esta sección se colocará el valor de cada Alert dialog para la confirmacion de  el pago de premios

        AlertDialog.Builder creaMensajes = new AlertDialog.Builder(this);
        creaMensajes.setMessage("Desea Confirmar El pago");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
=======
        creaMensajes.setPositiveButton("hola", new DialogInterface.OnClickListener() {
>>>>>>> origin/master
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.mesaJuego.jugador[eleccion].cargarapuesta(5);
                tablero.mesaJuego.jugador[eleccion].jugadortv.setText(Integer.toString(tablero.mesaJuego.jugador[eleccion].verapuesta()));
                tablero.eleccion=-1;
                dialog.cancel();
            }
        });
<<<<<<< HEAD

        creaMensajes.setNegativeButton( "Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                tablero.eleccion=-1;
                dialog.cancel();
            }
        });
        msgConfPago = creaMensajes.create();

        TextView[] datos={(TextView) findViewById(R.id.tvJugador1),(TextView) findViewById(R.id.tvJugador2),
                (TextView) findViewById(R.id.tvJugador3),(TextView) findViewById(R.id.tvJugador4),
                (TextView) findViewById(R.id.tvJugador5),(TextView) findViewById(R.id.tvJugador6),
                (TextView) findViewById(R.id.tvJugador7),(TextView) findViewById(R.id.tvPremioApuesta1),
                (TextView) findViewById(R.id.tvPremioApuesta2),(TextView) findViewById(R.id.tvPremioApuesta3),
                (TextView) findViewById(R.id.tvPremioApuesta4),(TextView) findViewById(R.id.tvPremioApuesta5),
                (TextView) findViewById(R.id.tvPremioApuesta6),(TextView) findViewById(R.id.tvPagar),
                (TextView) findViewById(R.id.tvJugar),(TextView) findViewById(R.id.tvApostar),
                (TextView) findViewById(R.id.tvRetiroTotal)};

        mesaJuego=new Mesa(datos);
=======
        creaMensajes.setNegativeButton("Jodete puta, no me saludes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
>>>>>>> origin/master

        alert1 = creaMensajes.create();
        //-----------------------------------------------------------------------------------------------------//
        //Mensaje de confirmacion de retiro//
        creaMensajes.setMessage("Seguro qué desea retirarse?");
        creaMensajes.setCancelable(true);
        creaMensajes.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

<<<<<<< HEAD

       // https://www.iconfinder.com/        Descargar los iconos
        }
=======
                tablero.mesaJuego.jugadores[eleccion].reiniciarApuesta();
                dialog.cancel();
                eleccion=-1;
            }
        });
        creaMensajes.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                eleccion=-1;
                dialog.cancel();
            }
        });
        msgConfRetiro= creaMensajes.create();
       //-------------------------------------------------------------------------------------------------//
        // definiciones de las variables del tablero
        //1. Vari ables de lostv de los jugadores
        mesaJuego.jugadores[0]= new Jugador((TextView) findViewById(R.id.tvJugador1));
        mesaJuego.jugadores[1]= new Jugador((TextView) findViewById(R.id.tvJugador2));
        mesaJuego.jugadores[2]= new Jugador((TextView) findViewById(R.id.tvJugador3));
        mesaJuego.jugadores[3]= new Jugador((TextView) findViewById(R.id.tvJugador4));
        mesaJuego.jugadores[4]= new Jugador((TextView) findViewById(R.id.tvJugador5));
        mesaJuego.jugadores[5]= new Jugador((TextView) findViewById(R.id.tvJugador6));
        mesaJuego.jugadores[6]= new Jugador((TextView) findViewById(R.id.tvJugador7));

>>>>>>> origin/master

        //2. variables del premio y tipo de apuesta

<<<<<<< HEAD













=======
        mesaJuego.dealerJuego= new Dealer(new TextView[]{(TextView) findViewById(R.id.tvPremioApuesta1),
    (TextView) findViewById(R.id.tvPremioApuesta2),(TextView) findViewById(R.id.tvPremioApuesta3),
                (TextView) findViewById(R.id.tvPremioApuesta4),
      (TextView) findViewById(R.id.tvPremioApuesta5),
        (TextView) findViewById(R.id.tvPremioApuesta6),

       (TextView) findViewById(R.id.tvJugar),
        (TextView) findViewById(R.id.tvApostar),
       (TextView) findViewById(R.id.tvRetiroTotal),
   (TextView) findViewById(R.id.tvPagar)});


       // https://www.iconfinder.com/        Descargar los iconos


        // enlace de cada uno de los botones de la interfaz grafica con su respectivo litener
        pagar.setOnClickListener(new lTVClickControlesJuego());
        jugar.setOnClickListener(new lTVClickControlesJuego());
        retirar.setOnClickListener(new lTVClickControlesJuego());
        apostar.setOnClickListener(new lTVClickControlesJuego());
        
        for (int i=0;i<pA.length;i++) {
            pA[i].setOnClickListener(new lTVClickBtnApeustasPremios());
        }
        for (int i=0;i<jugadortv.length;i++) {
            mesaJuego.jugadores[i].tvJugador.setOnClickListener(new lTVClickJugadores());
        }
    }
>>>>>>> origin/master

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
