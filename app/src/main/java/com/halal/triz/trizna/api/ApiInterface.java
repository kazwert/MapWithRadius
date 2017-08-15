package com.halal.triz.trizna.api;

import com.halal.triz.trizna.model.MapResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 03-Jun-17.
 */

public interface ApiInterface {
    @GET("getlocation.php")
    Call<MapResponse> getLocation();
}
