package com.example.ipdownapp.handlers;

import android.view.View;

import com.example.ipdownapp.activitys.DDOSActivity;
import com.example.ipdownapp.atack_tools.Attacker;

public class DDOSHandler implements View.OnClickListener  {

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
            attacker = new Attacker(url, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
