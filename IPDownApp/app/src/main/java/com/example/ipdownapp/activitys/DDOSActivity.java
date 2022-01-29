package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ipdownapp.R;

public class DDOSActivity extends AppCompatActivity {

    private EditText ipTargetDDOS;
    private EditText consolaDDOS;
    private Button botonAtack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddosactivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ipTargetDDOS = findViewById(R.id.editTextIPTargetDDOS);
        botonAtack = findViewById(R.id.botonAtack);
        consolaDDOS = findViewById(R.id.editTextMultilineConsola);
    }
}