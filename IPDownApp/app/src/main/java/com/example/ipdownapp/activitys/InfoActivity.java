package com.example.ipdownapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipdownapp.R;
import com.example.ipdownapp.handlers.InfoHandler;

public class InfoActivity extends AppCompatActivity{

    private Button btnBuscar;

    private EditText inIpTarget;

    private TextView labIp;
    private TextView labCountry;
    private TextView labRegion;
    private TextView labCity;
    private TextView labZipCode;

    private InfoHandler controlador;


    private WebView navegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controlador = new InfoHandler(this);

        confComponentes();


    }

    private void confComponentes() {
        //web view
        navegador = findViewById(R.id.navegador);
        navegador.getSettings().setJavaScriptEnabled(true);

        // Setting on Touch Listener for handling the touch inside ScrollView
        navegador.setOnTouchListener((v, event) -> {
            // Disallow the touch request for parent scroll on touch of child view
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        navegador.setWebViewClient(new WebViewClient());

        //boton
        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(controlador);

        //labels
        labIp = findViewById(R.id.textViewIPResult);
        labCity = findViewById(R.id.textViewCityResult);
        labCountry = findViewById(R.id.textViewCountryResult);
        labRegion = findViewById(R.id.textViewRegionResult);
        labZipCode = findViewById(R.id.textViewZipCodeResult);

        //input
        inIpTarget = findViewById(R.id.editTextIPTarget);

    }

    public String getIpTarget(){
        return inIpTarget.getText().toString();
    }

    public void mensajeErrorFormatoIp(){
        Toast.makeText(this, "The format of the Ip v4 entered is not correct.", Toast.LENGTH_SHORT).show();
    }

    public void mensajeErrorConexion(){
        Toast.makeText(this, "Connection error.", Toast.LENGTH_SHORT).show();
    }

    public void setLabIp(String ip) {
        this.labIp.setText(ip);
    }

    public void setLabCountry(String country) {
        this.labCountry.setText(country);
    }

    public void setLabRegion(String region) {
        this.labRegion.setText(region);
    }

    public void setLabCity(String city) {
        this.labCity.setText(city);
    }

    public void setLabZipCode(String zipCode) {
        this.labZipCode.setText(zipCode);
    }

    public void actualizarURLMapa(String url){
        navegador.loadUrl(url);
    }
}