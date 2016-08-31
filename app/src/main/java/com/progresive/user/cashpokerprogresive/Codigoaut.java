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
                (TextView) findViewById(R.id.EncargadoTV),
                (TextView) findViewById(R.id.PremioCodigo),
                (TextView) findViewById(R.id.Jugador1Codigo)};

        switch (tablero.mesaJuego.ApuPreSeleccionado())
        {case 0:
                CodeTV[3].setBackgroundResource(R.drawable.premioescalerarealgrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(0)));
            break;
            case 1:
                CodeTV[3].setBackgroundResource(R.drawable.premioescaleracolorgrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(1)));
                break;
            case 2:
                CodeTV[3].setBackgroundResource(R.drawable.premiopokergrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(2)));
                break;
            case 3:
                CodeTV[3].setBackgroundResource(R.drawable.premiofullgrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(3)));
                break;
            case 4:
                CodeTV[3].setBackgroundResource(R.drawable.premiocolorgrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(4)));
                break;
            case 5:
                CodeTV[3].setBackgroundResource(R.drawable.premioescalerasuciagrande);
                CodeTV[3].setText(String.valueOf(CPPLogin.manip.verPorcentajePremio(5)));
                break;
        }
        switch (tablero.mesaJuego.JugadorSeleccionado())
        {   case 0:
                CodeTV[4].setText(String.valueOf(1));
            break;
            case 1:
                CodeTV[4].setText(String.valueOf(2));
                break;
            case 2:
                CodeTV[4].setText(String.valueOf(3));
                break;
            case 3:
                CodeTV[4].setText(String.valueOf(4));
                break;
            case 4:
                CodeTV[4].setText(String.valueOf(5));
                break;
            case 5:
                CodeTV[4].setText(String.valueOf(6));
                break;
            case 6:
                CodeTV[4].setText(String.valueOf(7));
                break;
        }

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











