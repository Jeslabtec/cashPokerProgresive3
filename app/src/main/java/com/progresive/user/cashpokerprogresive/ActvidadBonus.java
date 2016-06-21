package com.progresive.user.cashpokerprogresive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActvidadBonus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvidad_bonus);
        ImageView TipoDeBonus= (ImageView) findViewById(R.id.ImagenBonus);
        TipoDeBonus.setOnLongClickListener(new longclickBonus());
    }

    public class longclickBonus  implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            clicLargoFunciones();
            return false;
        }
    }
    public void clicLargoFunciones(){
        Intent orden=new Intent(ActvidadBonus.this,tablero.class);
        tablero.dato.startActivity(orden);
        finish();
    }
}

