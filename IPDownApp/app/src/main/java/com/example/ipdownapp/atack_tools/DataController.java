package com.example.ipdownapp.atack_tools;

import com.example.ipdownapp.models.ConsoleStateQueue;

import java.util.ArrayList;

public class DataController extends Thread{

    private final long DELAY_ACTUALIZACION = 1000;

    private Notificable notificable;
    private ConsoleStateQueue colaDatos;

    public boolean activo;

    public DataController(Notificable notificable, ConsoleStateQueue colaDatos) {

        this.notificable = notificable;
        this.colaDatos = colaDatos;
        this.activo = true;

    }

    @Override
    public void run() {

        StringBuffer mensaje = new StringBuffer();
        ArrayList<String> colaMensajes;

        while(activo){

            //se conformael mensaje
            mensaje.append("Succesfull request: " + colaDatos.getPeticionesExitosas() + "\n");
            mensaje.append("Failed request: " + colaDatos.getPeticionesErroneas() + "\n");

            colaMensajes = colaDatos.getMensajes();

            for (String i:colaMensajes){

                mensaje.append(i + "\n");

            }

            //se notifica
            notificar(mensaje.toString());

            //se limpia el buffer
            mensaje.delete(0,mensaje.length());

            //se limpia la cola de datos
            colaDatos.limpiar();

            esperar();
        }

    }

    private void esperar() {

        try {
            Thread.sleep(DELAY_ACTUALIZACION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void notificar(String mensaje){

        /**
         * Hay que usar el metodo runOnUiThread del activity para poder ejecutar cambios
         * en los elementos de la Interfaz gráfica desde otro hilo que no sea el principal
         * para eso hay que mandarle un runnable que son instrucciones que se ponen en la
         * cola de llamadas del hilo principal.
         */
        notificable.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notificable.notificar(mensaje);
            }
        });


    }
}
