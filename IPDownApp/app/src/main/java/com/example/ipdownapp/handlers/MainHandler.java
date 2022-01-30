package com.example.ipdownapp.handlers;

import android.view.View;

import com.example.ipdownapp.APITools.API;
import com.example.ipdownapp.APITools.FreeGeoIPService;
import com.example.ipdownapp.R;
import com.example.ipdownapp.activitys.MainActivity;
import com.example.ipdownapp.models.IPInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainHandler implements View.OnClickListener {

    private final String URL_SERVICE = "https://api.freegeoip.app/json/";
    private final String API_KEY = "fce938f0-8109-11ec-b2e9-6595f9a9e24d";


    private MainActivity view;
    private IPInfo ipInfo = null;
    private String publicAddress = "";
    private String privateAddress = "";


    public MainHandler(MainActivity view) {
        this.view = view;
        this.view.setPublicIP(consultarPublicIp());
        this.view.setPrivateIP(consultarPrivateIp());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnInfo:

                view.goInfo();

                break;

            case R.id.btnDDOS:

                view.goDDOS();

                break;
        }

    }

    private String consultarPublicIp() {
        FreeGeoIPService servicio = API.getApi(URL_SERVICE).create(FreeGeoIPService.class);
        Call<IPInfo> peticionIP = servicio.infoIpPropia(API_KEY);

        peticionIP.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {
                ipInfo = response.body();
                publicAddress = ipInfo.getIp();
            }

            @Override
            public void onFailure(Call<IPInfo> call, Throwable t) {
                view.mensajeErrorConexion();
            }
        });

        return publicAddress;
    }

    private String consultarPrivateIp() {
        List<InetAddress> addressess;
        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addressess = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addressess){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        privateAddress = addr.getHostAddress().toUpperCase(new Locale("es", "SP"));
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Exception at getting private IP address.");
        }
        return privateAddress;
    }
}
