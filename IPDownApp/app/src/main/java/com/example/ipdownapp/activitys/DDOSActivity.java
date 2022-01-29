package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ipdownapp.R;
import com.example.ipdownapp.handlers.DDOSHandler;

public class DDOSActivity extends AppCompatActivity {

    private EditText ipTargetDDOS;
    private EditText consolaDDOS;
    private Button botonAtack;
    private DDOSHandler controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddosactivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ipTargetDDOS = findViewById(R.id.editTextIPTargetDDOS);
        botonAtack = findViewById(R.id.botonAtack);
        consolaDDOS = findViewById(R.id.editTextMultilineConsola);

        controller = new DDOSHandler(this);
        botonAtack.setOnClickListener(controller);
    }

    public String getTextIpTargetDDOS() {
        return ipTargetDDOS.getText().toString();
    }

    public void setIpTargetDDOS(String ipTargetDDOS) {
        this.ipTargetDDOS.setText(ipTargetDDOS);
    }

    public String getTextConsolaDDOS() {
        return consolaDDOS.getText().toString();
    }

    public synchronized void setConsolaDDOS(String consolaDDOS) {
        this.consolaDDOS.append(consolaDDOS + "\n");
    }
}