package com.example.ipdownapp.handlers;

import com.example.ipdownapp.R;

import android.annotation.SuppressLint;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.botonAtack:
                goDDOS();
                break;

            case R.id.botonStop:
                stopDdos();
                break;


        }


    }

    private void stopDdos() {

        if(attacker != null && attacker.isAttack()){
            attacker.pararAtaque();
        }

    }

    private void goDDOS() {

        String url = vista.getTextIpTargetDDOS();

        int numThreads = Integer.valueOf(vista.getTextNumberThread());

            //si no existe el atacker lo crea
            if(attacker == null){
                try{
                    attacker = new Attacker(url, numThreads, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(!attacker.isAttack()){
                attacker.iniciarAtaque();
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
