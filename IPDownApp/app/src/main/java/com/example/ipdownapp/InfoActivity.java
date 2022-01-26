package com.example.ipdownapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoActivity extends AppCompatActivity{


    private final String mapURL = "https://www.google.com/maps/search/?api=1&query=36.26577,-92.54324";
    private WebView navegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        navegador = findViewById(R.id.navegador);

        navegador.getSettings().setJavaScriptEnabled(true);

        navegador.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        navegador.setWebViewClient(new WebViewClient());

        navegador.loadUrl(mapURL);

    }



}