package com.example.ipdownapp.atack_tools;

import android.app.Activity;

/**
 * Interfaz que sirve para abstraer un objeto al que se le pueden mandar notificaciones de la consola Ddos.
 */

public interface Notificable {

    void notificar(String mensaje);
    Activity getActivity();

}
