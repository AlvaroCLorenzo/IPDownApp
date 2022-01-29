package com.example.ipdownapp.handlers;

import android.view.View;

import com.example.ipdownapp.R;
import com.example.ipdownapp.activitys.MainActivity;

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
