package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipdownapp.handlers.MainHandler;
import com.example.ipdownapp.R;

public class MainActivity extends AppCompatActivity {

    private MainHandler handler;

    private Button btnInfo;
    private Button btnDDOS;
    private TextView publicIP;
    private TextView privateIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icono_app_ipdownapp_launcher_round);

        confElementos();
    }

    private void confElementos() {
        btnInfo = findViewById(R.id.btnInfo);
        btnDDOS = findViewById(R.id.btnDDOS);
        publicIP = findViewById(R.id.textViewPublicIPResult);
        privateIP = findViewById(R.id.textViewPrivateIPResult);

        this.handler = new MainHandler(this);
        aniadirEscuchadores();
    }

    private void aniadirEscuchadores(){
        btnInfo.setOnClickListener(handler);
        btnDDOS.setOnClickListener(handler);
    }

    public void goInfo(){
        Intent pasador = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(pasador);
    }


    public void goDDOS(){
        Intent pasador = new Intent(MainActivity.this, DDOSActivity.class);
        startActivity(pasador);
    }

    public void mensajeErrorConexion(){
        Toast.makeText(this, "Connection error.", Toast.LENGTH_SHORT).show();
    }

    public void setPublicIP(String publicIP){
        this.publicIP.setText(publicIP);
    }

    public void setPrivateIP(String privateIP){
        this.privateIP.setText(privateIP);
    }
}