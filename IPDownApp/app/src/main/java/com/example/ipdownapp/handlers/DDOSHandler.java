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
        String ipTarget = vista.getTextIpTargetDDOS();
        String numThreads = vista.getTextNumberThread();
        //Evitamos que el usuario no introduzca alguno de los campos.
        if(ipTarget.equals(null) && numThreads.equals(null)){
            //si no existe el atacker lo crea
            if(attacker == null){
                try{
                    attacker = new Attacker(ipTarget, Integer.valueOf(numThreads), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(!attacker.isAttack()){
                attacker.iniciarAtaque();
            }
        }else{
            vista.mensajeErrorCampos();
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
