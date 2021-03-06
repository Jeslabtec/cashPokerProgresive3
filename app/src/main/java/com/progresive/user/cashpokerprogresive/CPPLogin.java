package com.progresive.user.cashpokerprogresive;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 *  Pantalla de inicio basada en ingresar Usuario y contraseña para el manejo de base de datos
 */
public class CPPLogin extends AppCompatActivity {
    static ManejoBD manip;  // Atributo de la clase creado para manejar tod lo concerniente a
    // coneccion de la BD
    static AppCompatActivity ContextoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpplogin);
        final EditText Usuario = (EditText) findViewById(R.id.edtUsuario);
        final EditText Pw = (EditText) findViewById(R.id.edtPassword);
        final Button lg = (Button) findViewById(R.id.btnLogin);

        manip=new ManejoBD();
        ContextoLogin=this;



        lg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(manip.Login(Usuario.getText().toString(),Pw.getText().toString())){
                        Intent pTablero=new Intent(CPPLogin.this,tablero.class);
                        startActivity(pTablero);
                        finish();
                    }
                    else{
                        Toast.makeText(CPPLogin.this,"No puede Cargarse el pantallazo inicial",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CPPLogin.this,e.getMessage(),Toast.LENGTH_SHORT);
                }
            }
        });
    }
}

