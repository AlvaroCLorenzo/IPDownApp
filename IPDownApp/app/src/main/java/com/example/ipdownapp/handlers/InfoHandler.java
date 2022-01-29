package com.example.ipdownapp.handlers;

import com.example.ipdownapp.activitys.InfoActivity;

public class InfoHandler {

    private InfoActivity vista;

    private final String URL_SERVICE = "https://api.freegeoip.app/json/";
    private final String API_KEY = "fce938f0-8109-11ec-b2e9-6595f9a9e24d";

    public InfoHandler(InfoActivity vista) {
        this.vista = vista;



    }
}
