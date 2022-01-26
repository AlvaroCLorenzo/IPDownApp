package com.example.ipdownapp;

import android.view.View;

public class MainHandler implements View.OnClickListener {


    private MainActivity view;

    public MainHandler(MainActivity view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnInfo:

                view.goInfo();

                break;

            case R.id.btnDDOS:

                view.goDDOS();

                break;

        }

    }
}
