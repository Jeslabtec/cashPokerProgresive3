package com.progresive.user.cashpokerprogresive;



import android.os.Handler;
import android.widget.TextView;
import org.json.JSONException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by JuanEsteban on 28/04/2016.
 */
public class Mesa {
    //Objetos que representa c/u de los jugadores
    Jugador[] jugador = new Jugador[7];
    //Objetos de Los botones de apuestas premios
    ClaseApuestaPremio[] ApuestaPremio = new ClaseApuestaPremio[6];
    //Textview que me dice en que fase esta el juego
    TextView AvisoTV;
    //OBJETOS TEXTVIEW DE LOS CONTROLES DEL JUEGO
    ControlesJuego pagarTV;
    ControlesJuego jugarTV;
    ControlesJuego apostarTV;
    ControlesJuego retirarseTV;
    //CLASE QUE CONTROLA EL PROGRESIVO
    ClaseDelProgresivo ProgresivoTV;
    //CLASE QUE CONTROLA LOS MENSAJES DE ALERTA
    MensajesAlerta mensaje;
//Variables necesarias para el sonido
    administradorDeSonido sonido;
    //SONIDOS QUE SE GUARDAN EN EL SOUNDPOOL
    int clic;
    int aviso;
    int winner;
    //variable que dice si se necesita el supervisor o no ES PUBLICA POR QUE ES LLAMADA EN CODIGOAUT
    public boolean necesariosupervisor = false;
    //VARIABLE QUE INDICA QUE BOTON DE APUESTAPREMIO ESTA AHORA SELECCIONADO, SI NO HAY NINGUNO SELECCIONADO EL VALOR ES -1
    private int ApuPreSeleccionado = -1;
    // por defecto se inicia en la etapa 3, acreditar
    //VARIABLE QUE INDICA EN QUE ESTADO DEL JUEGO SE ESTA, HAY 4 ESTADOS
    //1=PAGAR
    //2=JUGAR
    //3=ACREDITAR
    //4=RETIRAR
    private int EstadoJuego = 3;

    // constructor de la clase Mesa:  el programa
    public Mesa(TextView[] v,administradorDeSonido w) {
//Creacion de los objetos jugadores que son 7
        for (int i = 0; i < jugador.length; i++) {
            jugador[i] = new Jugador(v[i], v[i + 19], v[i + 26]);
        }
        //Creacion de los objetos ApuestaPremio que son 6
        for (int i = 0; i < ApuestaPremio.length; i++) {
            ApuestaPremio[i] = new ClaseApuestaPremio(v[i + 7], i);
        }
        //Creacion de los 4 objetos de control
        pagarTV = new ControlesJuego(v[13], 1);
        jugarTV = new ControlesJuego(v[14], 2);
        apostarTV = new ControlesJuego(v[15], 3);
        retirarseTV = new ControlesJuego(v[16], 4);
       //conficguracion del sonido
        sonido=w;
        clic = sonido.load(R.raw.clic);
        aviso=sonido.load(R.raw.aviso);
        winner=sonido.load(R.raw.winner);
        //ASIGNACION DEL TEXTVIEW DE AVISO A SU OBJETO CONTROLADOR
        AvisoTV = v[17];

        //Creacion del objeto progresivo
        ProgresivoTV = new ClaseDelProgresivo(v[18]);
        //Objeto que contiene los mensajes de alerta
        mensaje = new MensajesAlerta();
        //FUNCION QUE PERMITE UBICAR TODOS LOS TEXTVIEW EN SU PRIMER ESTADO
        cambiarBotones();
    }

    //---------------------------------------------------------------------------------
    //ANIMACION QUE PERMITE UBICAR LOS BOTONES DE PREMIO EN SU POSICION CORRECTA, COMPRENDE MOVIMIENTO EN X Y Y
    private void animaciondesplazamientoPremio() {
        float Y1 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist1);
        float Y2 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist2);
        float Y3 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist3);
        float Y4 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist4);
        float Y5 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist5);
        float Y6 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist6);

        float X1 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_1);
        float X2 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_2);
        float X3 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_3);
        float X4 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_4);
        float X5 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_5);
        float X6 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_6);
        ApuestaPremio[0].Movimientopremio(-X1, -Y1);
        ApuestaPremio[1].Movimientopremio(-X2, -Y2);
        ApuestaPremio[2].Movimientopremio(-X3, -Y3);
        ApuestaPremio[3].Movimientopremio(-X4, -Y4);
        ApuestaPremio[4].Movimientopremio(-X5, -Y5);
        ApuestaPremio[5].Movimientopremio(-X6, -Y6);

   /*   ApuestaPremio[0].Movimientopremio(-2*X1, -2*Y1);
        ApuestaPremio[1].Movimientopremio(-2*X2, -2*Y2);
        ApuestaPremio[2].Movimientopremio(-2*X3, -2*Y3);
        ApuestaPremio[3].Movimientopremio(-2*X4, -2*Y4);
        ApuestaPremio[4].Movimientopremio(-2*X5, -2*Y5);
        ApuestaPremio[5].Movimientopremio(-2*X6, -2*Y6);*/
    }
    //ANIMACION QUE PERMITE UBICAR LOS BOTONES DE APUESTA EN SU POSICION CORRECTA, COMPRENDE MOVIMIENTO EN X Y Y
    private void animaciondesplazamientoApuesta() {
        float Y1 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist1);
        float Y2 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist2);
        float Y3 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist3);
        float Y4 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist4);
        float Y5 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist5);
        float Y6 = tablero.dato.getResources().getDimension(R.dimen.ApuPreDist6);

        float X1 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_1);
        float X2 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_2);
        float X3 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_3);
        float X4 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_4);
        float X5 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_5);
        float X6 = tablero.dato.getResources().getDimension(R.dimen.Dis_separaApuPre_6);

   /*   ApuestaPremio[0].Movimientoapuesta(-2*X1, -2*Y1);
        ApuestaPremio[1].Movimientoapuesta(-2*X2, -2*Y2);
        ApuestaPremio[2].Movimientoapuesta(-2*X3, -2*Y3);
        ApuestaPremio[3].Movimientoapuesta(-2*X4, -2*Y4);
        ApuestaPremio[4].Movimientoapuesta(-2*X5, -2*Y5);
        ApuestaPremio[5].Movimientoapuesta(-2*X6, -2*Y6);*/
        
        ApuestaPremio[0].Movimientoapuesta(-X1, -Y1);
        ApuestaPremio[1].Movimientoapuesta(-X2, -Y2);
        ApuestaPremio[2].Movimientoapuesta(-X3, -Y3);
        ApuestaPremio[3].Movimientoapuesta(-X4, -Y4);
        ApuestaPremio[4].Movimientoapuesta(-X5, -Y5);
        ApuestaPremio[5].Movimientoapuesta(-X6, -Y6);
    }
    //ESTA FUNCION ES LLAMADA DESDE BOTONES DE JUEGO Y ASIGNA A CADA JUGADOR LA
    // CARACTERISTICA QUE DEBE TENER DEPENDIENDO DE SU CANTIIDAD APOSTADA
    public void restringirJugadoresjuego() {
        for (int i = 0; i < jugador.length; i++) {
            if (jugador[i].verapuesta() == 0) {
                jugador[i].SwitchAvisoApuestaAcabada(false);
                jugador[i].Bloquear();
                //CUANDO UN JUGADOR SOLO TIENE UN CREDITO EL JUEGO DEBE AVISAR AL DEALER
            }else if (jugador[i].verapuesta() == 1) {
                //PERMITE RESETEAR EL SONIDO DEL AVISO SONORO
                jugador[i].ResetearAvisoApuestaAcabada();
                //PERMITE PRENDER EL AVISO DE APUESTA ACABADA
                jugador[i].SwitchAvisoApuestaAcabada(true);
                //PRENDE EL AVISO APUESTA ACABADA
                jugador[i].avisoApuestaAcabada();
            }
        }
    }
    //FUNCION QUE ES LLAMADA CUANDO SE PRESIONA RETIRAR, SIMPLEMENTE BLOQUEA LOS JUGADORES SIN CREDITO
    public void restringirJugadoresretiro() {
        for (int i = 0; i < jugador.length; i++) {
            if (jugador[i].verapuesta() == 0) {
                jugador[i].Bloquear();
            }
        }
    }
//FUNCION QUE BLOQUEA UN JUGADOR ESPECÍFICO
    public void restringirJugador(int i) {
        jugador[i].Bloquear();
    }

    //Funcion que habilita a los jugadores en la fase de apuesta, LOS HABILITA A TODOS
    public void habilitarJugadores() {
        {
            for (int i = 0; i < jugador.length; i++) {
                jugador[i].Habilitar();
            }
        }
    }

    //Funcion que pregunta si hay alguien jugando si lo hay responde con true SINO FALSE

    public boolean hayAlguienJugando() {
        for (int i = 0; i < jugador.length; i++) {
            if (jugador[i].verapuesta()>0 && jugador[i].verSiPausado()) {
                return true;
            }
        }
        return false;
    }

    //Funcion que me dice cuantos jugadores hay en mesa PARA SABER CUANTO DINERO ENTRO AL JUEGO
    public int cuantosJugando() {
        int jugadores = 0;
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            //ESTA FUNCION SOLO FUNCIONA CUANDO YA SE HA BLOQUEADO LOS  JUGADORES QUE TIENEN 0 CREDITOS
            if (tablero.mesaJuego.jugador[i].jugadortv.isEnabled() && tablero.mesaJuego.jugador[i].verSiPausado()) {
                jugadores++;
            }
        }
        return jugadores;
    }

    //Funcion que permite saber que jugadoR esta seleccionado
    // SI NO HAY NINGUN JUGADOR SELECCIONADO ENTONCES RETORNA UN -1
    public int JugadorSeleccionado() {
        for (int i = 0; i < jugador.length; i++) {
            if (jugador[i].EstoySeleccionado()) {
                return i;
            }
        }
        return -1;
    }

    //funcion que permite seleccionar un jugador
    //Y HABILITAR LOS DEMAS, SE UTILIZA EN FASES COMO ACREDITAR PAGAR Y RETIRAR
    public void SeleccionarJugador(int j) {
        for (int i = 0; i < jugador.length; i++) {
            if (i == j) {
                jugador[i].Seleccionar();
            } else if (jugador[i].jugadortv.isEnabled()) {
                jugador[i].Habilitar();
            }
        }
    }
//FUNCION QUE SIRVE PARA SABER QUE BOTON DE APUESTA PREMIO SELECCIONADO
    //ESTO PERMITE SABER QUE CREDITO VOY AA AGREGAR O QUE PREMIO VOY A PAGAR
    public int ApuPreSeleccionado() {
        return ApuPreSeleccionado;
    }
//FUNCION QUE SIRVE PARA SELECCIONAR UN BOTON DE APUESTA PREMIO
    public void SeleccionarApuPre(int i) {
        ApuPreSeleccionado = i;
    }

    //Funcion que devuelve el estado del juego
    public int verElEstadoDelJuego() {
        return (EstadoJuego);
    }
    //*************************************************************************************************************************
    //Funcion que cambia el estado de juego
    public void cambiarElEstadoDelJuego(int NuevoEstado) {
        EstadoJuego = NuevoEstado;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------
    //Funcion para iniciar el juego
    public void PonerAJugar() {
        //1. LUEGO DE INHHABILITAR LOS JUGADORES SIN CREDITO CARGO LA SUPERAPUESTA
        //DE LOS QUE SI TIENEN CREDITO Y LUEGO LES QUITO DE A 1 CREDITO CON APOSTEMOS
        for (int i = 0; i < tablero.mesaJuego.jugador.length; i++) {
            if (tablero.mesaJuego.jugador[i].verapuesta() > 0) {
                tablero.mesaJuego.jugador[i].cargarSuperApuesta();
                tablero.mesaJuego.jugador[i].apostemos();
            }
        }
        //CONDICIONAL QUE PREGUNTA SI HAY BONUS EN LA ESTA PARTIDA
        if(jugadaActual==jugadasBonus){
            unGanadorBonus=true;
        //CONDICIONAL QUE ASEGURA QUE JUGADA ACTUAL NO SOBREASE JUGADA BONUS
        }else if(jugadaActual>jugadasBonus){
            jugadaActual=0;
        }
        //DE ACUERDO AL NUMERO DE JUGADORES EN LA PARTIDA ACTUAL SE SETEA UN AUMENTO DEL PREMIO EN EL
        //PROGRESIVO
        ProgresivoTV.setAumentoPremio();
        //INICIO DE LA FUNCION TEMPORIZADA DEL ESTADO DE JUEGO, ENTRE OTRAS COSAS PERMITE QUE SE VEA LENTAMENTE
        //EL AUMENTO DEL PROGRESIVO
        progresivoLoco();
        //VARIABLE QUE AL IGUALARSE CON BONUS HABILITA CUALQUIERA DE LOS DOS BONUS
        jugadaActual++;
    }

    //Bonus************************************************************************************************
    //variable que dice en que jugada va a haber un ganadoR
    private int jugadasBonus = getBinomial(4, 0.5);
    //conteo de las jugadas que se reinicia cuando hay un ganador
    private int jugadaActual = 0;    //
    //PERMITE CONTAR LAS ITERACIONES EN CADA BONUS
    private int iteracionesBonus = -1;
    //INDICA EL TIEMPO QUE VA A DEMORARSE EL TEMPORIZADOR DEL BONUS EN MILISEGUNDOS
    private int tiempoBonus = 500;
    //INDICA CUAL FUE EL JUGADOR QUE GANO EL BONUS INDIVIDUAL
    private int ganadorBonus = (int) Math.floor(Math.random() * 7);
    private int pagoBonus;
    Timer t1 = new Timer();
    final Handler handler1 = new Handler();
    private int bonus1,bonus2;
    private boolean Bonusactive=true;
    private boolean unGanadorBonus=false;
    public boolean GanaronBonus=false;
//Parte estadistica
    public int getBinomial(int n, double p) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (Math.random() < p)
                x++;
        }
        return x;
    }
//Timer que ejecuta las acciones visuales en el momento del bonus
    private void BonusTimer() {
        t1.schedule(new TimerTask() {
            public void run() {
                handler1.post(new Runnable() {
                    public void run() {
                        if(bonus1>0) {
                            SeleccionarJugadorBonus();
                        }else{
                            Bonustodos();
                        }
                        reproducirSonido(2);
                    }
                });
            }
        }, tiempoBonus);
    }
//Permite rotar la ubicacion de cada jugador
    private void BonusCambio() {
        for (int i = 0; i < jugador.length; i++) {
            jugador[i].bonusScreen(false);
        }
        SeleccionarJugadorBonus();    }
    //Sirve ara ir pasando el jugador hasta que llegue al ganador
    public void SeleccionarJugadorBonus() {
        if (iteracionesBonus == -1) {
            jugador[iteracionesBonus + 1].bonusScreen(true);
        } else if (iteracionesBonus >= 0 && iteracionesBonus < 6) {
            jugador[iteracionesBonus].bonusScreen(false);
            jugador[iteracionesBonus + 1].bonusScreen(true);
        } else if (iteracionesBonus >= 6 && iteracionesBonus < 12) {
            jugador[12 - iteracionesBonus].bonusScreen(false);
            jugador[11 - iteracionesBonus].bonusScreen(true);
        } else if (iteracionesBonus >= 12) {
            jugador[iteracionesBonus - 12].bonusScreen(false);
            jugador[iteracionesBonus - 11].bonusScreen(true);
        }
        if (iteracionesBonus < 11 + ganadorBonus) {
            iteracionesBonus++;
            tiempoBonus += 20;
            BonusTimer();
        } else {
            iteracionesBonus = -1;
            tiempoBonus = 500;
            pagarBonus();
        }
    }
//Funcion que paga a un jugador el bonus
    private void pagarBonus() {
        if (jugador[ganadorBonus].verSiPausado() && jugador[ganadorBonus].jugadortv.isEnabled()) {
            pagarConEstilo();
            try {
                CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet, "salida", pagoBonus);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            EstadoBonusOff();
        }
    }
    private void Bonustodos(){
        for(int i=0;i<jugador.length;i++){
            jugador[i].bonusScreen(Bonusactive);
        }
        Bonusactive=!Bonusactive;
        if(iteracionesBonus<pagoBonus-2) {
            iteracionesBonus++;
            for(int i=0;i<jugador.length;i++){
                if (jugador[i].verSiPausado() && jugador[i].jugadortv.isEnabled()) {
                    ProgresivoTV.PagarProgresivo(1);
                    jugador[i].cargarapuesta(1);
                    jugador[i].cargarSuperApuesta();
                }
            }
            BonusTimer();
        }else{
            iteracionesBonus=-1;
            PagarBonusTodos();
            Bonusactive=true;
        }
    }
    private void PagarBonusTodos(){
        int contganadores=0;
        for(int i=0;i<jugador.length;i++){
            if (jugador[i].verSiPausado() && jugador[i].jugadortv.isEnabled()) {
                ProgresivoTV.PagarProgresivo(1);
                jugador[i].cargarapuesta(1);
                jugador[i].cargarSuperApuesta();
                contganadores++;
            }
        }
        EstadoBonusOff();
        try {
            CPPLogin.manip.EnviarMovimiento(CPPLogin.manip.idTablet,"salida",pagoBonus*contganadores);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        };
    }
    private void EstadoBonusOff(){
        for(int i=0;i<jugador.length;i++){
            if (!jugador[i].verSiPausado()) {
                jugador[i].ponerPausado();
                jugador[i].ponerPausado();
            }else if(jugador[i].jugadortv.isEnabled()) {
                jugador[i].Habilitar();
                if(jugador[i].verapuesta()==0){
                    jugador[i].SwitchAvisoApuestaAcabada(true);
                    jugador[i].avisoApuestaAcabada();
                }
            }else{
                jugador[i].Bloquear();
            }
        }
        GanaronBonus=false;
        retirarseTV.Bloquear();
        pagarTV.Habilitar();
        jugarTV.Seleccionar();
        apostarTV.Habilitar();
        AvisoTV.setBackgroundResource(R.drawable.avisojugar);
        AvisoTV.setText(R.string.Jugar);
        animaciondesplazamientoPremio();
    }
    private void EstadoBonusOn() {
        retirarseTV.Bloquear();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Bloquear();
        AvisoTV.setBackgroundResource(R.drawable.avisobonus);
        if(bonus1>0) {
            AvisoTV.setText("+"+String.valueOf(pagoBonus)+" UNICO");
        }else{
            AvisoTV.setText("+"+String.valueOf(pagoBonus)+" TODOS");
        }


    }


    //Timer***********************************************************************************************
    //Funcion que se realiza iterativamente durante toda la fase de juego
    final Handler handler = new Handler();
    Timer t = new Timer();
    private int iteracionesProgresivoLoco=0;

    public void progresivoLoco() {
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (iteracionesProgresivoLoco<50) {
                            iteracionesProgresivoLoco++;
                            ProgresivoTV.aumentoAleatorio();
                            progresivoLoco();
                        }else{
                            if (unGanadorBonus) {
                                GanaronBonus=true;
                                unGanadorBonus=false;
                                jugadaActual = 0;
                                jugadasBonus = 3;
                                //bonus1=getBinomial(16,0.0625);
                                //bonus2=getBinomial(160,0.1875);
                                bonus1=(bonus1==0)?(1):(0);
                                if (bonus1>0) {
                                    ganadorBonus = (int) Math.floor(Math.random() * 7);
                                    pagoBonus= getBinomial(5,0.2)+5;
                                    tiempoBonus=500;
                                    EstadoBonusOn();
                                    BonusCambio();
                                }
                                else{
                                    pagoBonus=getBinomial(8,0.0909)+2;
                                    EstadoBonusOn();
                                    tiempoBonus=Math.round(6500/pagoBonus);
                                    Bonustodos();
                                }

                            }else{
                                retirarseTV.Habilitar();
                                pagarTV.Habilitar();
                                jugarTV.Seleccionar();
                                apostarTV.Habilitar();

                            }
                            iteracionesProgresivoLoco=0;
                        }

                    }
                });
            }

        }, 100);
    }



    //*********************************************************************************************************************
    //Que pasa con los textview cuando se une cualquiera de los controles//
    private void BotonesdeApuesta() {
        ApuestaPremio[5].ponerSumando();
        retirarseTV.Habilitar();
        pagarTV.Bloquear();
        jugarTV.Habilitar();
        apostarTV.Seleccionar();

        for (int i = 0; i < ApuestaPremio.length; i++) {
            ApuestaPremio[i].BotonesApuesta();
        }

        AvisoTV.setBackgroundResource(R.drawable.avisoacreditar);
        AvisoTV.setText(R.string.Acreditar);
        habilitarJugadores();
        animaciondesplazamientoApuesta();
    }

    //----------------------------------------------------------------------------------------//
    private void BotonesdePago() {
        retirarseTV.Bloquear();
        pagarTV.Seleccionar();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i = 0; i < ApuestaPremio.length; i++) {
            ApuestaPremio[i].BotonesPremio();
        }
        AvisoTV.setBackgroundResource(R.drawable.avisopagar);
        AvisoTV.setText(R.string.Pagar);
        animaciondesplazamientoPremio();
    }

    //--------------------------------------------------------------------------------------------------//
    private void BotonesdeJuego() {
        retirarseTV.Bloquear();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Bloquear();

        for (int i = 0; i < ApuestaPremio.length; i++) {
            ApuestaPremio[i].BotonesPremio();
        }

        SeleccionarJugador(-1);
        restringirJugadoresjuego();
        AvisoTV.setBackgroundResource(R.drawable.avisojugar);
        AvisoTV.setText(R.string.Jugar);
        animaciondesplazamientoPremio();
    }

    //---------------------------------------------------------------------------------------------------------------//
    private void BotonesdeRetiro() {
        retirarseTV.Seleccionar();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Habilitar();

        for (int i = 0; i < ApuestaPremio.length; i++) {
            ApuestaPremio[i].BotonesDesaparecer();
        }
        SeleccionarJugador(-1);
        restringirJugadoresretiro();
        AvisoTV.setBackgroundResource(R.drawable.avisoretirarse);
        AvisoTV.setText(R.string.Retirar);
        animaciondesplazamientoPremio();
    }
    //-------------------------------------------------------------------------------------------------------------------//
    // metodos
    //dependiendo del estado del juego se habilitaran o desabilitaran algunos botones

    public void cambiarBotones() {
        switch (EstadoJuego) {//----------------------------------------------------------------------------------------------
            case 1:
                BotonesdePago();
                break;
            //-------------------------------------------------------------------------------------------------------------------//
            case 2:
                BotonesdeJuego();
                break;
            case 3:
                BotonesdeApuesta();
                break;
            case 4:
                BotonesdeRetiro();
                break;
        }
    }

    //Acciones que permiten confirmar el pago, es valida cuando el codigo ingresado en codigoaut pertenece a un dealer o supervisor
    public int AccionesConfirmarPago() {

        double Progresivo = (int) ProgresivoTV.ValorDelProgresivo();
        double Premio = ApuestaPremio[ApuPreSeleccionado()].ValorNumerico();
        double pago;
        if (ApuPreSeleccionado < 2) {
            pago = Math.floor((double) (Progresivo * (Premio / 100)) / CPPLogin.manip.verValorFicha());

        } else {
            pago = Premio;
        }
        DineroPagoConEstilo=(int)pago;
        retirarseTV.Bloquear();
        pagarTV.Bloquear();
        jugarTV.Bloquear();
        apostarTV.Bloquear();
        pagarConEstilograndes();
        return (int) pago;
    }


//pago con estilo---------------------------------------------------------------------------------------------------------------------------
    final Handler handler3 = new Handler();
    Timer t2 = new Timer();
    private int DineroPagoConEstilo=0;
    private int conteoPagoestilo=0;
    private int cuantosubir=0;
    private int sobrante=0;


    private void pagarConEstilograndes(){
        t1.schedule(new TimerTask() {
            public void run() {
                handler3.post(new Runnable() {
                    public void run() {
                        if (conteoPagoestilo == 0) {
                            cuantosubir = DineroPagoConEstilo / 20;
                            sobrante = DineroPagoConEstilo % 20;
                        }
                        if (conteoPagoestilo < 20) {
                            conteoPagoestilo++;
                            ProgresivoTV.PagarProgresivo(cuantosubir);
                            jugador[JugadorSeleccionado()].cargarapuesta(cuantosubir);
                            reproducirSonido(1);
                            pagarConEstilograndes();

                        }else if(conteoPagoestilo>=20 && conteoPagoestilo<20+sobrante){
                            conteoPagoestilo++;
                            ProgresivoTV.PagarProgresivo(1);
                            jugador[JugadorSeleccionado()].cargarapuesta(1);
                            reproducirSonido(1);
                            pagarConEstilograndes();

                        }else {
                            jugador[JugadorSeleccionado()].cargarSuperApuesta();
                            restringirJugador(JugadorSeleccionado());
                            cambiarBotones();
                            conteoPagoestilo=0;
                            DineroPagoConEstilo=0;
                            cuantosubir=0;
                            sobrante=0;
                        }
                    }
                });
            }
        }, 100);
    }
    private void pagarConEstilo(){
        t1.schedule(new TimerTask() {
            public void run() {
                handler3.post(new Runnable() {
                    public void run() {
                         if(conteoPagoestilo<pagoBonus){
                            conteoPagoestilo++;
                            ProgresivoTV.PagarProgresivo(1);
                            jugador[ganadorBonus].cargarapuesta(1);
                            reproducirSonido(1);
                            pagarConEstilo();

                        }else {
                             jugador[ganadorBonus].cargarSuperApuesta();
                             conteoPagoestilo=0;
                             EstadoBonusOff();
                        }
                    }
                });
            }
        }, 100);
    }


    //Admini sonido

    public void reproducirSonido(int position)
    {
        //Obtenemos el id del sonido
        switch (position){
            case 1:
                sonido.play(clic);
                break;
            case 2:
                sonido.play(aviso);
                break;
            case 3:
                sonido.play(winner);
            default:
                break;
        }

    }

}
