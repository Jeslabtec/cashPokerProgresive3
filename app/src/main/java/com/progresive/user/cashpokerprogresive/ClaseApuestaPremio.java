package com.progresive.user.cashpokerprogresive;

import android.widget.TextView;

/**
 * Created by user on 02/06/2016.
 */
public class ClaseApuestaPremio {

    private TextView ApuestaPremioTV;
    private String

    public  ClaseApuestaPremio(TextView v,int i){
        ApuestaPremioTV=v;
        ApuestaPremioTV.setOnClickListener(new lTVClickBtnApeustasPremios());

    }

}
