package com.example.ipdownapp.handlers;

import android.app.Activity;
import android.view.View;

import com.example.ipdownapp.activitys.DDOSActivity;
import com.example.ipdownapp.atack_tools.Attacker;
import com.example.ipdownapp.atack_tools.Notificable;

public class DDOSHandler implements View.OnClickListener, Notificable {

    private DDOSActivity vista;
    private Attacker attacker;

    public DDOSHandler(DDOSActivity vista) {
        this.vista = vista;
    }

    @Override
    public void onClick(View v) {
        goDDOS();
    }

    private void goDDOS() {
        String url = vista.getTextIpTargetDDOS();
        try {
            attacker = new Attacker(url, 0, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void notificar(String mensaje) {
        vista.setConsolaDDOS(mensaje);
    }

    @Override
    public Activity getActivity() {
        return vista;
    }
}
