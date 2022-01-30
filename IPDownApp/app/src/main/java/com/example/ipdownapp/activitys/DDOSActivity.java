package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ipdownapp.R;
import com.example.ipdownapp.handlers.DDOSHandler;

public class DDOSActivity extends AppCompatActivity {

    private final String ERROR_CAMPOS = "ERROR: Complete all the items before atack";

    private EditText ipTargetDDOS;
    private EditText numberThreads;
    private EditText consolaDDOS;
    private Button botonAtack;
    private Button botonStop;
    private DDOSHandler controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddosactivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ipTargetDDOS = findViewById(R.id.editTextIPTargetDDOS);
        numberThreads = findViewById(R.id.numberThreads);
        botonAtack = findViewById(R.id.botonAtack);
        botonStop = findViewById(R.id.botonStop);
        consolaDDOS = findViewById(R.id.editTextMultilineConsola);

        controller = new DDOSHandler(this);
        botonAtack.setOnClickListener(controller);
        botonStop.setOnClickListener(controller);


    }

    public String getTextIpTargetDDOS() {
        String ip = ipTargetDDOS.getText().toString();
        return ip;
    }

    public void setIpTargetDDOS(String ipTargetDDOS) {
        this.ipTargetDDOS.setText(ipTargetDDOS);
    }

    public String getTextConsolaDDOS() {
        return consolaDDOS.getText().toString();
    }


    public String getTextNumberThread(){
        return this.numberThreads.getText().toString();
    }

    public synchronized void setConsolaDDOS(String consolaDDOS) {
        this.consolaDDOS.append(consolaDDOS + "\n");
    }

    public void mensajeErrorCampos() {
        Toast.makeText(this, ERROR_CAMPOS, Toast.LENGTH_SHORT).show();
    }
}