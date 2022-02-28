package com.example.ipdownapp.handlers;

import com.example.ipdownapp.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.example.ipdownapp.activitys.DDOSActivity;
import com.example.ipdownapp.atack_tools.Attacker;
import com.example.ipdownapp.atack_tools.Notificable;
import com.example.ipdownapp.atack_tools.RequestMethods;

public class DDOSHandler implements View.OnClickListener, Notificable {

    private DDOSActivity vista;
    private Attacker attacker;

    private final String HTTP_VALIDO = "http://";
    private final String HTTPS_VALIDO = "https://";

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
            attacker = null;
        }
    }

    private void goDDOS() {
        String ipTarget = vista.getTextIpTargetDDOS();
        String numThreads = vista.getTextNumberThread();

        RequestMethods metodoElegido = null;

        if(vista.isGET()){
            metodoElegido = RequestMethods.GET;
        }else if(vista.isPOST()){
            metodoElegido = RequestMethods.POST;
        }

        //Evitamos que el usuario no introduzca alguno de los campos.
        if(ipTarget != null  && numThreads !=null && metodoElegido != null){

            if(validarProtocoloHTTP(ipTarget)){

                //si no existe el atacker lo crea
                if(attacker == null){
                    try{
                        attacker = new Attacker(ipTarget, Integer.valueOf(numThreads), metodoElegido , this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if(!attacker.isAttack()){
                    attacker.iniciarAtaque();
                }

            }else{
                vista.mensajeErrorProtocolo();
            }

        }else{
            vista.mensajeErrorCampos();
        }
    }

    private boolean validarProtocoloHTTP(String ipTarget) {

        String cadenaHTTP = ipTarget.substring(0,7);
        String cadenaHTTPS = ipTarget.substring(0,8);

        return (cadenaHTTP.equals(HTTP_VALIDO) || cadenaHTTPS.equals(HTTPS_VALIDO));

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
