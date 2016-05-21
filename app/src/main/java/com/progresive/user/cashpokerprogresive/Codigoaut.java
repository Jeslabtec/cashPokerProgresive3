package com.progresive.user.cashpokerprogresive;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



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


        TextView[] CodeTV = {(TextView) findViewById(R.id.num0),
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
                (TextView) findViewById(R.id.coding)};

        for (int i = 0; i < CodeTV.length; i++) {

            CodeTV[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    codisel(v);
                }

            });

        }

    }


    public void codisel(View v){
        switch (v.getId()) {
            case R.id.num0:
                break;
            case R.id.num1:
                break;
            case R.id.num2:
                break;
            case R.id.num3:
                break;
            case R.id.num4:
                break;
            case R.id.num5:
                break;
            case R.id.num6:
                break;
            case R.id.num7:
                break;
            case R.id.num8:
                break;
            case R.id.num9:
                break;
            case R.id.okTV:
                break;
        }
    }

}









