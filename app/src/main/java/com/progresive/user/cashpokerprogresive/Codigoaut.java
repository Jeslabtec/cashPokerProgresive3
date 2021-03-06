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
    public TextView CodingTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codigoaut);


        final TextView[] CodeTV = {(TextView) findViewById(R.id.num0),
                (TextView) findViewById(R.id.num1),
                (TextView) findViewById(R.id.num2),
                (TextView) findViewById(R.id.num3),
                (TextView) findViewById(R.id.num4),
                (TextView) findViewById(R.id.num5),
                (TextView) findViewById(R.id.num6),
                (TextView) findViewById(R.id.num7),
                (TextView) findViewById(R.id.num8),
                (TextView) findViewById(R.id.num9),
                (TextView) findViewById(R.id.okTV),
                (TextView) findViewById(R.id.coding),
                (TextView) findViewById(R.id.BorrarTV),
                (TextView) findViewById(R.id.EncargadoTV)};
        if(tablero.mesaJuego.necesariosupervisor) {
            CodeTV[13].setText("Supervisor");
        }else{
            CodeTV[13].setText("Dealer");
        }
        for (int i = 0; i < CodeTV.length; i++) {

            CodeTV[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    codisel(v,CodeTV);
                }

            });

        }

    }
//
//Listener Del tablero de codigo
    public void codisel(View v, TextView[] CodeTV){
        int Limite=8; //Limite de cantidad de numeros para los codigos
        switch (v.getId()) {
            case R.id.num0:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "0");
                }
                break;
            case R.id.num1:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "1");
                }
                break;
            case R.id.num2:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "2");
                }
                break;
            case R.id.num3:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "3");
                }
                break;
            case R.id.num4:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "4");
                }
                break;
            case R.id.num5:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "5");
                }
                break;
            case R.id.num6:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "6");
                }
                break;
            case R.id.num7:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "7");
                }
                break;
            case R.id.num8:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "8");
                }
                break;
            case R.id.num9:
                if(CodeTV[11].getText().length()<Limite) {
                    CodeTV[11].setText(CodeTV[11].getText() + "9");
                };
                break;
            case R.id.BorrarTV:
                if(CodeTV[11].getText().length()>0) {
                    CodeTV[11].setText(CodeTV[11].getText().subSequence(0, CodeTV[11].getText().length() - 1));
                }
                break;
            case R.id.okTV:
                if(tablero.mesaJuego.necesariosupervisor) {
                    try {
                        if (CPPLogin.manip.VerificarClave((String) CodeTV[11].getText(),"supervisor")) {
                            int pago=tablero.mesaJuego.AccionesConfirmarPago();
                            CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet,"salida",pago,Integer.parseInt((String)CodeTV[11].getText()));
                            finish();
                        }else{
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
                        if (CPPLogin.manip.VerificarClave((String) CodeTV[11].getText(),"dealer")) {
                            int pago=tablero.mesaJuego.AccionesConfirmarPago();
                            CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet,"salida",pago,Integer.parseInt((String)CodeTV[11].getText()));
                            finish();
                        } else {
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
                break;
        }
    }

}









