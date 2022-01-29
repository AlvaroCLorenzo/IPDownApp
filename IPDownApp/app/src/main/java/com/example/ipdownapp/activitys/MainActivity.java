package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.ipdownapp.handlers.MainHandler;
import com.example.ipdownapp.R;

public class MainActivity extends AppCompatActivity {


    private MainHandler handler;

    private Button btnInfo;
    private Button btnDDOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icono_app_ipdownapp_launcher_round);

        this.handler = new MainHandler(this);

        confElementos();

    }

    private void confElementos() {

        btnInfo = findViewById(R.id.btnInfo);

        btnDDOS = findViewById(R.id.btnDDOS);

        btnInfo.setOnClickListener(handler);

        btnDDOS.setOnClickListener(handler);

    }


    public void goInfo(){

        Intent pasador = new Intent(MainActivity.this, InfoActivity.class);

        startActivity(pasador);



    }


    public void goDDOS(){



    }
}