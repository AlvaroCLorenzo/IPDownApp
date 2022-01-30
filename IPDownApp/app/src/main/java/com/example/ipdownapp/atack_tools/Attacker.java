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

public class Attacker {

    private int num_threadsDDOS = 4;

    private DataController controladorConsola;

    private ConsoleStateQueue colaDatos;

    private ArrayList<DdosThread> hilosDdos;

    public Attacker(String url_request, int num_threads, Notificable notificable) throws Exception {

        //se crea el recurso compartido entre los hilos
        colaDatos = new ConsoleStateQueue();

        //se crea el hilo que acualizará la consola
        controladorConsola = new DataController(notificable,colaDatos);

        hilosDdos = new ArrayList<DdosThread>();

        controladorConsola.start();

        for (int i = 0; i < this.num_threadsDDOS; i++) {

            DdosThread thread = new DdosThread(colaDatos);

            thread.setRequestURL(url_request);

            hilosDdos.add(thread);

            thread.start();

        }
    }

}
