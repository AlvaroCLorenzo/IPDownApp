package com.example.ipdownapp.handlers;

import android.view.View;

import com.example.ipdownapp.APITools.API;
import com.example.ipdownapp.APITools.FreeGeoIPService;
import com.example.ipdownapp.activitys.InfoActivity;
import com.example.ipdownapp.models.IPInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoHandler implements View.OnClickListener {

    private InfoActivity vista;

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
        consultarIp(ip);
    }

    private void consultarIp(String ip) {
        if(!validarIp(ip)){
            ip = transformarDominoIp(ip);
            System.out.println("IP obtenida: " + ip);
        }

        FreeGeoIPService servicio = API.getApi(MainHandler.URL_SERVICE).create(FreeGeoIPService.class);

        Call<IPInfo> peticionIP = servicio.infoIp(MainHandler.API_KEY, ip);

        peticionIP.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {

                System.out.println(response);

                if(response.body() != null){
                    actualizarVista(response.body());
                }else{
                    vista.mensajeErrorConexion();
                }
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

    private String transformarDominoIp(String dominio){
        String ip = "";
        try {

            ip = InetAddress.getByName(dominio).getHostAddress();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return ip;
    }

    //Patron Regex para validar IPV4 e IPV6
    private boolean validarIp(String ip) {

        final String IPv6Pattern = "(?ix)\\A(?:                                                  # Anchor address\n" +
                " (?:  # Mixed\n" +
                "  (?:[A-F0-9]{1,4}:){6}                                # Non-compressed\n" +
                " |(?=(?:[A-F0-9]{0,4}:){2,6}                           # Compressed with 2 to 6 colons\n" +
                "     (?:[0-9]{1,3}\\.){3}[0-9]{1,3}                     #    and 4 bytes\n" +
                "     \\z)                                               #    and anchored\n" +
                "  (([0-9A-F]{1,4}:){1,5}|:)((:[0-9A-F]{1,4}){1,5}:|:)  #    and at most 1 double colon\n" +
                " |::(?:[A-F0-9]{1,4}:){5}                              # Compressed with 7 colons and 5 numbers\n" +
                " )\n" +
                " (?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}  # 255.255.255.\n" +
                " (?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])           # 255\n" +
                "|     # Standard\n" +
                " (?:[A-F0-9]{1,4}:){7}[A-F0-9]{1,4}                    # Standard\n" +
                "|     # Compressed\n" +
                " (?=(?:[A-F0-9]{0,4}:){0,7}[A-F0-9]{0,4}               # Compressed with at most 7 colons\n" +
                "    \\z)                                                #    and anchored\n" +
                " (([0-9A-F]{1,4}:){1,7}|:)((:[0-9A-F]{1,4}){1,7}|:)    #    and at most 1 double colon\n" +
                "|(?:[A-F0-9]{1,4}:){7}:|:(:[A-F0-9]{1,4}){7}           # Compressed with 8 colons\n" +
                ")/[A-F0-9]{0,4}\\z                                                    # Anchor address";

        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";


        String IPv4Pattern = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        //Si la expresion regular de la ip cuadra con una IPV4 o IPV6 es true
        return (Pattern.matches(IPv6Pattern, ip) || Pattern.matches(IPv4Pattern, ip));
    }
}
