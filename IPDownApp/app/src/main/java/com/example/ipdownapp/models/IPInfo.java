package com.example.ipdownapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPInfo {

    @SerializedName(value = "ip")
    private String ip;

    @SerializedName(value = "country_name")
    private String nombrePais;

    @SerializedName(value = "state_prov")
    private String nombreRegion;

    @SerializedName(value = "city")
    private String ciudad;

    @SerializedName(value = "zipcode")
    private String codigoPostal;

    @SerializedName(value = "latitude")
    private String latitud;

    @SerializedName(value = "longitude")
    private String longitud;

    public IPInfo(String ip, String nombrePais, String nombreRegion, String ciudad, String codigoPostal, String latitud, String longitud) {
        this.ip = ip;
        this.nombrePais = nombrePais;
        this.nombreRegion = nombreRegion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIp() {
        return ip;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "IPInfo{" +
                "ip='" + ip + '\'' +
                ", nombrePais='" + nombrePais + '\'' +
                ", nombreRegion='" + nombreRegion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}
