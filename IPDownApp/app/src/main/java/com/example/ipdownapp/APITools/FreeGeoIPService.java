package com.example.ipdownapp.APITools;

import com.example.ipdownapp.models.IPInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FreeGeoIPService {

    @GET("{ip}")
    Call<IPInfo> infoIp(@Path("ip") String ip, @Query("apikey") String key);

    @GET("")
    Call<IPInfo> infoIpPropia(@Query("apikey") String key);

}
