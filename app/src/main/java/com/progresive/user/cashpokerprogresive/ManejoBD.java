package com.progresive.user.cashpokerprogresive;

import android.content.Intent;
import android.view.View;

/**
 * Clase para el manejo de la conexíon con la base de datos. La conexion se realizara a traves de
 * servicios API, similares a los servicios API de Facebook y Google utilizando php
 */
public class ManejoBD  {
    private String userreal="a"; // usuario de la aplicación para esta tablet
    private String pwreal="a";   // contraceña de la aplicaci{on para esta tablet
    private String[] ClavesDealer={"345678","123456"};
    private String[] ClavesSupervisor={"34567890","12345678"};
    private int DineroEnProgresivo = 1000000;
    private int ValorFicha=1000;
    private String[] PorcentajePremios={"1","2","4","8","16","32"};
/*
* Funcion login: revisa si las contraseñas suministradas son iguales a las reales
* */

    public boolean Login(String Usuario, String pass){
        return((Usuario.equals(userreal))&&(pass.equals(pwreal)));
    }

    public boolean VerificarClaveDealer(String pass){

        for (int i=0;i<ClavesDealer.length;i++) {
            if (pass.equals(ClavesDealer[i])) {
                return true;
            }
        }
        return false;
    }
    public boolean VerificarClaveSupervisor(String pass){

        for (int i=0;i<ClavesSupervisor.length;i++) {
            if (pass.equals(ClavesSupervisor[i])) {
                return true;
            }
        }
        return false;
    }
    public int verDineroProgresivo(){
        return DineroEnProgresivo;
    }
    public int verValorFicha(){
        return ValorFicha;
    }
    public String verPorcentajePremio(int i){
        return PorcentajePremios[i];
    }
}



