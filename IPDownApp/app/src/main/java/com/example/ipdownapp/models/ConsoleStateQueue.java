package com.example.ipdownapp.models;

import java.util.ArrayList;

public class ConsoleStateQueue {

    boolean escribiendoMensajes;

    private ArrayList<String> mensajes;

    boolean escribiendoPeticionesExitosas;

    private long peticionesExitosas;

    boolean escribiendoPeticionesErroneas;

    private long peticionesErroneas;

    public ConsoleStateQueue() {

        escribiendoMensajes = false;
        mensajes = new ArrayList<String>();

        escribiendoPeticionesExitosas = false;
        peticionesExitosas = 0;

        escribiendoPeticionesErroneas = false;
        peticionesErroneas = 0;

    }


    public synchronized long getPeticionesExitosas(){
        return peticionesExitosas;
    }

    public synchronized long getPeticionesErroneas(){
        return peticionesErroneas;
    }

    public synchronized ArrayList<String> getMensajes(){
        return new ArrayList<String>(mensajes);
    }


    public synchronized void addMensaje(String mensaje){

        while(escribiendoMensajes){
            esperar();
        }

        escribiendoMensajes = true;

        mensajes.add(mensaje);

        escribiendoMensajes = false;

        notifyAll();


    }

    public synchronized void limpiar(){

        escribiendoPeticionesExitosas = true;
        escribiendoPeticionesErroneas = true;

        peticionesExitosas = 0;
        peticionesErroneas = 0;
        mensajes.clear();

        escribiendoPeticionesExitosas = false;
        escribiendoPeticionesErroneas = false;

        notifyAll();
    }


    public synchronized void incPeticionesExitosas(){

        while(escribiendoPeticionesExitosas){
            esperar();
        }

        escribiendoPeticionesExitosas = true;

        ++peticionesExitosas;

        escribiendoPeticionesExitosas = false;

        notifyAll();

    }


    public synchronized void incPeticionesErroneas(){

        while(escribiendoPeticionesErroneas){
            esperar();
        }

        escribiendoPeticionesErroneas = true;

        ++peticionesErroneas;

        escribiendoPeticionesErroneas = false;

        notifyAll();

    }

    private synchronized void esperar(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
