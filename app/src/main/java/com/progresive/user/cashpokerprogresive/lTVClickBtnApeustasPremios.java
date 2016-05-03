package com.progresive.user.cashpokerprogresive;

import android.view.View;

/**
 * Created by JuanEsteban on 02/05/2016.
 */
public class lTVClickBtnApeustasPremios implements View.OnClickListener {

    public Dealer dealer;

    public lTVClickBtnApeustasPremios(Dealer dealerJuego)
    {
    dealer=dealerJuego;
    }


    @Override
    public void onClick(View v)
    {
        switch (dealer.verElEstadoDelJuego())
        {
            case 1:

            break;
            case 2:

            break;
            case 3:

            break;
            case 4:

            break;
        }
    }
}
