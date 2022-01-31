package com.example.ipdownapp.APITools;

import com.example.ipdownapp.models.IPInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FreeGeoIPService {

    @GET("ipgeo")
    Call<IPInfo> infoIp(@Query("apiKey") String key, @Query("ip") String ip);

    @GET("ipgeo")
    Call<IPInfo> infoIpPropia(@Query("apiKey") String key);

}
