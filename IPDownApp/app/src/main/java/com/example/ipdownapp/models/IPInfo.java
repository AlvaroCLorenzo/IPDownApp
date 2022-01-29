package com.example.ipdownapp.models;

import com.google.gson.annotations.SerializedName;

public class IPInfo {

    @SerializedName(value = "ip")
    private String ip;

    @SerializedName(value = "country_code")
    private String codigoPais;

    @SerializedName(value = "country_name")
    private String nombrePais;

    @SerializedName(value = "region_code")
    private String codigoRegion;

    @SerializedName(value = "region_name")
    private String nombreRegion;

    @SerializedName(value = "city")
    private String ciudad;

    @SerializedName(value = "zip_code")
    private String codigoPostal;

    @SerializedName(value = "time_zone")
    private String zonaHoraria;

    @SerializedName(value = "latitude")
    private String latitud;

    @SerializedName(value = "longitude")
    private String longitud;

    @SerializedName(value = "metro_code")
    private String metro_code;

    public IPInfo(String ip, String codigoPais, String nombrePais, String codigoRegion, String nombreRegion, String ciudad, String codigoPostal, String zonaHoraria, String latitud, String longitud, String metro_code) {
        this.ip = ip;
        this.codigoPais = codigoPais;
        this.nombrePais = nombrePais;
        this.codigoRegion = codigoRegion;
        this.nombreRegion = nombreRegion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.zonaHoraria = zonaHoraria;
        this.latitud = latitud;
        this.longitud = longitud;
        this.metro_code = metro_code;
    }

    public String getIp() {
        return ip;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getCodigoRegion() {
        return codigoRegion;
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

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getMetro_code() {
        return metro_code;
    }

    @Override
    public String toString() {
        return "IPInfo{" +
                "ip='" + ip + '\'' +
                ", codigoPais='" + codigoPais + '\'' +
                ", nombrePais='" + nombrePais + '\'' +
                ", codigoRegion='" + codigoRegion + '\'' +
                ", nombreRegion='" + nombreRegion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", zonaHoraria='" + zonaHoraria + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", metro_code='" + metro_code + '\'' +
                '}';
    }
}
