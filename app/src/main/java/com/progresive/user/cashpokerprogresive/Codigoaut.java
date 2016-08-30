package com.progresive.user.cashpokerprogresive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;


/**
 * Created by USER on 18/05/2016.
 */
public class Codigoaut extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codigoaut);


        final TextView[] CodeTV = {
                (TextView) findViewById(R.id.okTV),
                (TextView) findViewById(R.id.coding),
                (TextView) findViewById(R.id.EncargadoTV)};

        if(tablero.mesaJuego.necesariosupervisor) {
            CodeTV[2].setText(R.string.Supervisor);
        }else{
            CodeTV[2].setText(R.string.Dealer);
        }
         CodeTV[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    codisel(v,CodeTV);
                }

            });
    }
//
//Listener Del tablero de codigo
    public void codisel(View v, TextView[] CodeTV){

                if(tablero.mesaJuego.necesariosupervisor) {
                    try {
                        if (CPPLogin.manip.VerificarClave(String.valueOf(CodeTV[1].getText()),"supervisor")) {
                            int pago=tablero.mesaJuego.AccionesConfirmarPago();
                            CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet,"salida",pago);
                            CodeTV[1].setText("");
                            finish();
                        }else{
                            CodeTV[1].setText("");
                            finish();
                    }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        if (CPPLogin.manip.VerificarClave(String.valueOf(CodeTV[1].getText()),"dealer")) {
                            int pago=tablero.mesaJuego.AccionesConfirmarPago();
                            CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet,"salida",pago);
                            CodeTV[1].setText("");
                            finish();
                        } else {
                            CodeTV[1].setText("");
                            finish();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }
    }











