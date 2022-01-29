package com.example.ipdownapp.handlers;

import android.view.View;

import com.example.ipdownapp.APITools.API;
import com.example.ipdownapp.APITools.FreeGeoIPService;
import com.example.ipdownapp.activitys.InfoActivity;
import com.example.ipdownapp.models.IPInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoHandler implements View.OnClickListener {

    private InfoActivity vista;

    private final String URL_SERVICE = "https://api.freegeoip.app/json/";
    private final String API_KEY = "fce938f0-8109-11ec-b2e9-6595f9a9e24d";

    private final String GOOGLE_MAPS_URL_REQUEST = "https://www.google.com/maps/search/?api=1&query=";

    public InfoHandler(InfoActivity vista) {
        this.vista = vista;
    }

    @Override
    public void onClick(View v) {
        buscarIp();
    }

    private void buscarIp() {

        String ip = vista.getIpTarget();

        if(validarIp(ip)){
            consultarIp(ip);
        }else{
            vista.mensajeErrorFormatoIp();
        }

    }

    private void consultarIp(String ip) {

        FreeGeoIPService servicio = API.getApi(URL_SERVICE).create(FreeGeoIPService.class);

        Call<IPInfo> peticionIP = servicio.infoIp(ip, API_KEY);

        peticionIP.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {
                actualizarVista(response.body());
            }

            @Override
            public void onFailure(Call<IPInfo> call, Throwable t) {
                vista.mensajeErrorConexion();
            }
        });

    }

    private void actualizarVista(IPInfo info){

        vista.setLabIp(info.getIp());
        vista.setLabCity(info.getCiudad());
        vista.setLabCountry(info.getNombrePais());
        vista.setLabRegion(info.getNombreRegion());
        vista.setLabZipCode(info.getCodigoPostal());

        String urlMapa = GOOGLE_MAPS_URL_REQUEST + info.getLatitud() + "," + info.getLongitud();

        vista.actualizarURLMapa(urlMapa);

    }

    private boolean validarIp(String ip) {

        boolean correcto = true;

        String[] trozos = ip.split("\\.");

        //tiene que tener 4 trozos
        correcto = (trozos.length == 4);

        if(correcto){

            int num;

            //se recorren todos los trozos de la ip
            for (int i=0; i<trozos.length && correcto; i++){

                try{
                    //se intenta convertir todos los trozos a numeros enteros
                    num = Integer.valueOf(trozos[i]);

                    //si se logra tranformar se evalua si es un numero menor o igual a 255
                    correcto = (num <= 255);


                }catch(NumberFormatException ex){
                    //ai no se ha transformado correctamente en un número entero se niega
                    correcto = false;

                }

            }

        }

        return correcto;

    }
}
