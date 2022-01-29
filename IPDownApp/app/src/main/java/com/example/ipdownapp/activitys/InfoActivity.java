package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ipdownapp.R;
import com.example.ipdownapp.handlers.InfoHandler;

public class InfoActivity extends AppCompatActivity{

    private InfoHandler controlador;

    private final String mapURL = "https://www.google.com/maps/search/?api=1&query=36.26577,-92.54324";
    private WebView navegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        controlador = new InfoHandler(this);

        navegador = findViewById(R.id.navegador);

        navegador.getSettings().setJavaScriptEnabled(true);

        // Setting on Touch Listener for handling the touch inside ScrollView
        navegador.setOnTouchListener((v, event) -> {
            // Disallow the touch request for parent scroll on touch of child view
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        navegador.setWebViewClient(new WebViewClient());

        navegador.loadUrl(mapURL);

    }



}